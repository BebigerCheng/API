package com.smt.pc.Interface.controller;

import com.alibaba.fastjson.JSONObject;
import com.smt.pc.Interface.domain.UserDO;
import com.smt.pc.Interface.result.ResultDO;
import com.smt.pc.Interface.service.UserService;
import com.smt.pc.Interface.utils.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 用户表
 *
 * @author lijikai
 * @email
 * @date 2018 -03-23 13:50:12
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private final UserService userService;

    private final RedisTemplate redisTemplate;

    /**
     * Instantiates a new User controller.
     *
     * @param userService   the user service
     * @param redisTemplate the redis template
     */
    @Autowired
    public UserController(UserService userService, RedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }


    /**
     * List page utils.
     *
     * @param params the params
     * @return the page utils
     */
    @ApiOperation(value = "条件分页查询", notes = "params:实体类参数 offset：当前页 limit:每页显示条数", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {

        List<UserDO> userList = new ArrayList<>();
        int total = 0;
        try {
            //查询列表数据
            Query query = new Query(params);
            userList = userService.list(query);
            total = userService.count(query);
        } catch (Exception e) {

            EmailService.sendSimpleEmail("条件分页查询错误,userId%s" + params.get("userId"), getFullStackTrace(e));
        }
        return new PageUtils(userList, total);
    }


    /**
     * Edit string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Integer id, Model model) {
        UserDO user = userService.get(id);
        model.addAttribute("user", user);
        return "system/user/edit";
    }

    /**
     * 保存
     *
     * @param user the user
     * @return the r
     */
    @ApiOperation(value = "保存", notes = "", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @PostMapping("/save")
    public R save(@RequestBody UserDO user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        if (userService.save(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     *
     * @param user the user
     * @return the r
     */
    @ApiOperation(value = "更新", notes = "", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @ResponseBody
    @RequestMapping("/update")
    public R update(UserDO user) {
        userService.update(user);
        return R.ok();
    }

    /**
     * 删除
     *
     * @param id the id
     * @return the r
     */
    @ApiOperation(value = "删除", notes = "根据用户Id删除用户", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        if (userService.remove(id) > 0) {


            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     *
     * @param ids the ids
     * @return the r
     */
    @ApiOperation(value = "批量删除", notes = "参数为需要删除的id的数组", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        userService.batchRemove(ids);
        return R.ok();
    }


    /**
     * 退出
     *
     * @param request the request
     * @return the r
     */
    @GetMapping("/deleteCookie")
    @ResponseBody
    public R deleteCookie(HttpServletRequest request) {

        String authHeader = request.getHeader("authorization");

        if (null == authHeader) {

            return R.error("无参数");
        }
        //放入黑名单与token一样，一小时过期
        redisTemplate.opsForValue().set(authHeader, authHeader, 3600, TimeUnit.SECONDS);

        return R.ok();
    }

    /**
     * 用户注册
     *
     * @param user the user
     * @param code the code
     * @return the r
     */
    @ApiOperation(value = "用户注册", notes = "用户信息及验证码code", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @PostMapping("/register/{code}")
    @ResponseBody
    public R register(@RequestBody UserDO user, @PathVariable String code) {
        //判断验证码是否正确 1- 正确   2- 不正确 3- 失效
        int result = userService.checkCode(user, code);
        if (2 == result) {
            return R.error("验证码不正确！");
        } else if (3 == result) {
            return R.error("验证码已经过期，请重新获取！");
        }
        //判断用户名是否占用 0 - 未占用   1- 占用
        int status = userService.findUserName(user.getUserName());
        if (status == 1) {
            return R.error("用户名被占用！");
        }
        //判断手机号码是否占用 0 - 未占用   1- 占用
        int status2 = userService.findPhoneNumber(user.getPhoneNumber());
        if (status2 == 1) {
            return R.error("手机号被占用！");
        }


        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        //暂时默认为1 买卖方
        user.setUserType(1);
        //默认启用
        user.setIsactive(1 + "");
        //对密码进行加密
        String newPassWord = Md5Utils.getMD5Code(user.getPassWord());
        user.setPassWord(newPassWord);
        userService.save(user);
        return R.ok();
    }


    /**
     * 用户登录
     *
     * @param user    the user
     * @param session the session
     * @return the r
     */
    @ApiOperation(value = "登录", notes = "根据用户名密码登录", httpMethod = "POST")
    @PostMapping("/login")
    @ResponseBody
    public ResultDO login(@RequestBody UserDO user, HttpSession session) {

        ResultDO resultDO = new ResultDO<>();
//        String imgCode = redisTemplate.opsForValue().get("sid").toString();
//
//        if (null == imgCode || !"code".equalsIgnoreCase(imgCode)) {
//            return R.error(-4001, "验证码错误！");
//        }

        //用户登录，null- 用户不存在  password error- 密码错误  userDo- 登录成功
        String newPassWord = Md5Utils.getMD5Code(user.getPassWord());
        user.setPassWord(newPassWord);
        user = userService.login(user);
        if (user != null) {
            if (user.getPassWord() != "error") {
                //1个小时
                Date tokenExpired = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
                // Create Twt token
                String jwtToken = Jwts.builder().setSubject(user.getPhoneNumber()).claim("roles", "member").setIssuedAt(new Date()).setExpiration(tokenExpired)
                        .signWith(SignatureAlgorithm.HS256, "smtsaddsa3231").compact();
                JSONObject object = new JSONObject();
                object.put("token", jwtToken);
                object.put("userId", user.getId());
                object.put("phoneNumber", user.getPhoneNumber());
                resultDO.setSuccess(true);

                resultDO.setSuccess(true);
                resultDO.setErrMsg("登录成功！");
                resultDO.setData(object);
            } else {
                resultDO.setSuccess(false);
                resultDO.setErrMsg("用户密码错误！");
            }
        } else {
            resultDO.setSuccess(false);
            resultDO.setErrMsg("用户名不存在！");
        }
        return resultDO;

    }

}
