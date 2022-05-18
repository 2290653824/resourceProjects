package com.zj.resourceprojects.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zj.resourceprojects.entity.User;

public interface UserService  {

    public User getUserById(int id);


    /**
     * 根据用户的用户名查找用户实体
     * @param username
     * @return
     */
    public User findUserByUsername(String username);


    /**
     * 根据用户的邮箱查找用户
     * @param email
     * @return
     */
    public User findUserByEmail(String email);

    /**
     * 保存一个用户实体
     * @param user
     * @return
     */
    public int saveUser(User user);
}
