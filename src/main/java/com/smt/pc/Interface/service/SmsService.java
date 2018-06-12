package com.smt.pc.Interface.service;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * 短信验证
 *
 * @author chengliang
 * @email
 * @date 2018 -03-27
 */
public interface SmsService {

    SendSmsResponse checkCode(String phoneNumber);
}
