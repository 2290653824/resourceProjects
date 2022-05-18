package com.zj.resourceprojects.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("user")
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;


    private String openId;

    private String nickname;

    private String username;

    private String password;

    private String email;

    private String headPortrait;

    private String sex;

    private Integer points;

    private boolean isVip;

    private Integer vipGrade;

    private  boolean isOff;

    private String roleName;


    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //JsonFomat 将前台的西方格式时间在json时使用格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registrationData;


    @TableField(fill=FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date latelyLoginTime;


    //在查询时，默认会把该字段作为数据库的字段，如果这里不写该注解，就会报错
    @TableField(exist = false)
    private Integer messageCount;



}
