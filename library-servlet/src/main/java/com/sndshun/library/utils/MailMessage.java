package com.sndshun.library.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @Author mr.sun
 * @Creation 2021 2021/11/22 9:03
 * @Desc:
 */
public class MailMessage {
    //发送者
    private MailSender mailSender;
    //发送信息
    private SimpleMailMessage simpleMailMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /**
     *  邮箱发送
     * @param userCode 接收者的邮箱
     * @param activation 发送的激活码
     */
    public void placeOrder(String userCode,String activation) {
        //simpleMailMessage 只能发送text格式的邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage(this.simpleMailMessage);
        //设置接收者
        simpleMailMessage.setTo(userCode);
        //设置邮件内容
        simpleMailMessage.setText(activation);
        //发送者 发送配置好的邮件
        this.mailSender.send(simpleMailMessage);
    }
}
