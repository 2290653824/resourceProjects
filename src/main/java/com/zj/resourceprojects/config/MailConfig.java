package com.zj.resourceprojects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * qq邮箱配置类
 */
@Configuration
public class MailConfig {

    private static final String USERNAME="2290653824@qq.com";
    private static final String PASSWORD="gvcdanelghiadhja";

    /**
     * 建议学习一下MailSender是什么
     * @return
     */
    @Bean
    public MailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");  //指定用来发送邮件服务器的主机名
        mailSender.setPort(587);    //默认端口，标准的SMTP端口
        mailSender.setUsername(USERNAME);  //配置自己的qq邮箱
        mailSender.setPassword(PASSWORD);   //配置自己的qq邮箱发送授权码
        return mailSender;
    }

}
