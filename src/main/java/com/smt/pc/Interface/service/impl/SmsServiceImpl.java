package com.smt.pc.Interface.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.smt.pc.Interface.domain.Sms;
import com.smt.pc.Interface.mapper.SmsDao;
import com.smt.pc.Interface.service.SmsService;
import com.smt.pc.Interface.utils.NumberUtil;
import com.smt.pc.Interface.utils.msg.MoblieMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 短信验证实现类
 *
 * @author chengliang
 * @email
 * @date 2018 -03-27
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    SmsDao smsDao;

    @Override
    public SendSmsResponse checkCode( String phoneNumber) {
        String code = NumberUtil.randomNumber()+"";
        SendSmsResponse response = MoblieMessageUtil.sendIdentifyingCode(phoneNumber, code);
        if ("OK".equals(response.getCode())){
            //发送成功，添加到数据库
            Sms sms= new Sms();
            sms.setUserId(-1);//此时用户未注册，可以插入-1表示
            sms.setPhoneNum(phoneNumber);
            sms.setIsEffective(1);// 1- 无效  2- 有效
            sms.setCheckCode(code);
            sms.setCreateTime(new Date());
            smsDao.save(sms);
        }
        System.out.println();
        return response;
    }
}
