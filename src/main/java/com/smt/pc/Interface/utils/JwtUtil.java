package com.smt.pc.Interface.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * JwtUtil
 *
 * @author LIJIKAI
 * @date 18 /4/7
 */
public class JwtUtil {
    /**
     * The constant base64EncodedSecretKey.
     */
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";//私钥
    /**
     * The constant TOKEN_EXP.
     */
    final static long TOKEN_EXP = 1000 * 60;//过期时间,测试使用60秒

    /**
     * Gets token.
     *
     * @param userName the user name
     * @return the token
     */
    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }


    /**
     * Check token.
     *
     * @param token the token
     * @throws ServletException the servlet exception
     * @Date:17-12-12 下午6 :21
     * @Author:root
     * @Desc:检查token,只要不正确就会抛出异常
     */
    public static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

}
