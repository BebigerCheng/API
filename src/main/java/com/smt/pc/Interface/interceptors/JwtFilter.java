package com.smt.pc.Interface.interceptors;

import com.alibaba.fastjson.JSON;
import com.smt.pc.Interface.result.ResultDO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * JwtFilter
 *
 * @author LIJIKAI
 * @date 18 /4/7
 */
public class JwtFilter  extends GenericFilterBean {


    @Autowired
    private RedisTemplate redisTemplate;




    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        ResultDO resultInfo = new ResultDO();

        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        // Get authorization from Http request
        final String authHeader = request.getHeader("authorization");


        String authHeaderRedis;


        //从黑名单取出
        authHeaderRedis=redisTemplate.opsForValue().get(authHeader)+"";


        //有值则失败
        if(!"".equals(authHeaderRedis)){

            resultInfo.setErrCode(-4001);
            resultInfo.setErrMsg("用户授权认证没有通过!");
            returnError(request,response,resultInfo);
        }


        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        }

        // Except OPTIONS, other request should be checked by JWT
        else {

            // Check the authorization, check if the token is started by "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {


                resultInfo.setErrCode(-4001);
                resultInfo.setErrMsg("用户授权认证没有通过!客户端请求参数中无token信息");


//                throw new ServletException("Missing or invalid Authorization header");
            }

            // Then get the JWT token from authorization
            assert authHeader != null;
            final String token = authHeader.substring(7);

            try {
                // Use JWT parser to check if the signature is valid with the Key "secretkey"
                final Claims claims = Jwts.parser().setSigningKey("smtsaddsa3231").parseClaimsJws(token).getBody();

                // Add the claim to request header
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {

                resultInfo.setErrCode(-4001);
                resultInfo.setErrMsg("用户授权认证没有通过!客户端请求参数token信息无效");
                returnError(request,response,resultInfo);
//                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        }
    }


    /**
     * Return error.
     *
     * @param request  the request
     * @param response the response
     * @param resultDO the result do
     */
    public  void returnError(ServletRequest request, ServletResponse response,ResultDO resultDO){

        PrintWriter writer = null;
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(response.getOutputStream(),
                    "UTF-8");
            writer = new PrintWriter(osw, true);
            String jsonStr = JSON.toJSONString(resultDO);
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            osw.close();
        } catch (IOException e) {
            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
