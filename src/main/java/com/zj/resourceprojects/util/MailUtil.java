package com.zj.resourceprojects.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailUtil {

    //注意这里一定要和Mail配置中的邮箱对应，不然无法成功发送
    public static final String FROM_EMAIL="2290653824@qq.com";

    /**
     * 发送邮件的方法
     * @param mailSender
     * @param toEmail
     * @param code
     */
    public static void sendMessageToMail(MailSender mailSender,String toEmail, String code){
        SimpleMailMessage message = new SimpleMailMessage();        //消息构造器
        message.setFrom(FROM_EMAIL);                        //发件人
        message.setTo(toEmail);                                       //收件人
        message.setSubject("Java资源分享网-用户找回密码");         //主题
        message.setText("您本次的验证码是：" +code);            //正文内容
        mailSender.send(message);
        System.out.println(code);
    }


}
