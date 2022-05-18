package com.zj.resourceprojects;

import com.zj.resourceprojects.Mapper.UserMapper;
import com.zj.resourceprojects.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResourceProjectsApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 第一次测试
     */
    @Test
    void contextLoads() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

}
