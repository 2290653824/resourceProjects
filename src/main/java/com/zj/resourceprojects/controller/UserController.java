package com.zj.resourceprojects.controller;


import com.zj.resourceprojects.entity.User;
import com.zj.resourceprojects.service.UserService;
import com.zj.resourceprojects.util.CryptographyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 不用restCOntroller原因，因为在下面的方法不一定全是返回result对象，有些
 * 是直接根据template返回页面的
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @PostMapping("/register")
    public Map<String,Object> register(User user){
        HashMap<String, Object> map = new HashMap<>();
        if(validateUser(user)){
            map.put("success",false);
            map.put("message","用户字段信息不符合规范");
            return map;
        }else if(userService.findUserByUsername(user.getUsername())!=null){
            map.put("success",false);
            map.put("message","用户名已存在");
        }else if(userService.findUserByEmail(user.getEmail())!=null){
            map.put("success",false);
            map.put("message","该邮箱已注册过");
        }else{
            user.setPassword(CryptographyUtil.md5(user.getPassword(),CryptographyUtil.SALT));
            user.setHeadPortrait("jj.png");
            int i = userService.saveUser(user);
            if (i == 0) {
                map.put("success",false);
                map.put("message","用户注册失败");

            }else{
                map.put("success",true);
                map.put("message","用户注册成功");

            }
        }

        return map;
    }

    @RequestMapping("getById/{id}")
    @ResponseBody
    public User getById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    public boolean validateUser(User user){
        return false;
    }
}
