package com.smt.pc.Interface.controller;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.smt.pc.Interface.service.SmsService;
import com.smt.pc.Interface.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 短信验证
 *
 * @author chengliang
 * @email
 * @date 2018 -03-27
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    private final
    SmsService smsService;

    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    /**
     * 发送验证码.
     *
     * @author chengliang
     * @return R
     */
    @ApiOperation(value = "发送验证码",notes = "传入手机号phoneNumber",httpMethod = "POST")
    @ApiResponse(code = 200,message = "Success")
    @ResponseBody
    @PostMapping("/checkCode")
    public R checkCode(@RequestBody Map map){
        SendSmsResponse response = smsService.checkCode(map.get("phoneNumber").toString());
        if ("OK".equals(response.getCode())){
            return R.ok();
        }else{
            return R.error("服务器忙，请稍后再试！");
        }
    }

}
