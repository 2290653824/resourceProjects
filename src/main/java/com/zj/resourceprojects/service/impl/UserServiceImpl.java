package com.zj.resourceprojects.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zj.resourceprojects.Mapper.UserMapper;
import com.zj.resourceprojects.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zj.resourceprojects.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id){
        User user = userMapper.selectById(1);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));

        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("email", email));

        return user;
    }

    @Override
    public int saveUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }
}
