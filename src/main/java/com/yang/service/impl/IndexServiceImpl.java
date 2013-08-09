package com.yang.service.impl;

import com.yang.service.IndexService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: jianyuanyang
 * Date: 13-7-18
 * Time: 下午5:42
 */
@Service
public class IndexServiceImpl implements IndexService{

    @Resource
    private MailSender mailSender;

    @Override
    public void sendEmail(String emailContent) {
        System.out.println("s---"+System.currentTimeMillis());
        sendMail("jianyuan.yang@b5m.com",
                "690492623@qq.com",
                "发送邮件主题测试",
                emailContent);
        System.out.println("e---"+System.currentTimeMillis());
    }



    public void sendMail(String from, String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
