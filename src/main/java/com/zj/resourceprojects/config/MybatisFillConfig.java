package com.zj.resourceprojects.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
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

}
