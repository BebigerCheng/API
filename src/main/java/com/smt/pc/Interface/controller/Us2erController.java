//package com.smt.pc.Interface.controller;
//
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import com.smt.pc.Interface.domain.User;
//import com.smt.pc.Interface.result.ResultDO;
//import com.smt.pc.Interface.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import sun.management.counter.StringCounter;
//
///**
// * @Package UserController
// * @Copyright: Copyright (c) 2016
// * Author lijikai
// * @date 2017/5/12 15:30
// * version V1.0.0
// */
//@RestController
//@RequestMapping("/user")
//public class Us2erController {
//
//    @Autowired
//    private UserService userService;
//
//    @ApiOperation(value = "查询单个用户",notes = "根据传入id查找用户",httpMethod = "GET")
//    @ApiResponse(code = 200,message = "Success")
//    @RequestMapping(value ="/get/{id}", method = RequestMethod.GET)
//    public ResultDO<User> get(@PathVariable("id")Integer id){
//        ResultDO<User> resultDO = new ResultDO<>();
//        resultDO.setSuccess(true);
//        resultDO.setData(userService.getUserById(id));
//        return resultDO;
//    }
//
////    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
////            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
////    })
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public ResultDO<Integer> add(User user){
//        ResultDO<Integer> resultDO = new ResultDO<>();
//        resultDO.setSuccess(true);
//        resultDO.setData(userService.save(user));
//        return resultDO;
//    }
//
//    @RequestMapping(value = "/get",method = RequestMethod.POST)
//    public ResultDO<Integer> get(User user){
//        ResultDO<Integer> resultDO = new ResultDO<>();
//        String userName = user.getName();
//        User reUser = new User();
//        reUser = userService.findByUserName(userName);
//        return resultDO;
//    }
//}
