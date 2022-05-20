package com.zj.resourceprojects.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MybatisFillConfig implements MetaObjectHandler {

    /**
     * 当字段上为INSERT时，会调用该方法
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "registrationData",()->new java.util.Date() , java.util.Date.class);
        this.strictInsertFill(metaObject, "latelyLoginTime",()->new java.util.Date() , java.util.Date.class);



    }

    /**
     * 当字段上有UPDATE时，会调用该方法
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

//        this.strictUpdateFill(metaObject, "updateTime", ()->new java.util.Date() , java.util.Date.class);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //分页插件
        return mybatisPlusInterceptor;
    }

}
