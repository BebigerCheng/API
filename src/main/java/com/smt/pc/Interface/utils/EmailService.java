package com.smt.pc.Interface.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * EmailService
 * 邮件发送
 *
 * @author LIJIKAI
 * @date 18/4/3
 */

@Component
public class EmailService {


    private static JavaMailSender mailSender;

    private static String emailRe;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        EmailService.mailSender = mailSender;
    }

    @Value("${email.receive}")
    public void setEmailRe(String emailRe) {
        EmailService.emailRe = emailRe;
    }

    /**
     * 修改application.properties的用户，才能发送。
     */
    public static void sendSimpleEmail(String title, String content) {


        String[] mails;
        if (StringUtils.hasText(emailRe)) {
            mails = emailRe.split(";");
        } else {
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom("ops@piao360.net");
        //接收者
        message.setTo(mails);
        //邮件主题.
        message.setSubject(title);
        //邮件内容.
        message.setText(content);
        //发送邮件
        mailSender.send(message);

    }


}
