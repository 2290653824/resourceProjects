package com.zj.resourceprojects.controller;


import com.zj.resourceprojects.entity.User;
import com.zj.resourceprojects.service.UserService;
import com.zj.resourceprojects.util.Consts;
import com.zj.resourceprojects.util.CryptographyUtil;
import com.zj.resourceprojects.util.MailUtil;
import com.zj.resourceprojects.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
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


    @Autowired
    private MailSender mailSender;

    @PostMapping("/login")
    @ResponseBody
    public Map<String ,Object> login(User user, HttpSession session){
//        HashMap<String, Object> map = new HashMap<>();
//
//        if(user==null){
//            map.put("success",false);
//            map.put("message","表单数据不存在");
//            return map;
//        }
//
//        User getUsername = userService.findUserByUsername(user.getUsername());
//        if(getUsername==null){
//            map.put("success",false);
//            map.put("message","该用户名未注册");
//            return map;
//        }
//
//        String password = CryptographyUtil.md5(user.getPassword(),CryptographyUtil.SALT);
//        if(!password.equals(getUsername.getPassword())){
//            map.put("success",false);
//            map.put("message","密码错误");
//            return map;
//        }
//
//
//        //登录成功，更新登陆时间
//        Date date = new Date();
//        getUsername.setLatelyLoginTime(date);
//        userService.updateById(getUsername);
//        map.put("success",true);
//        map.put("message","登陆成功");
//        return map;

        Map<String,Object> map = new HashMap<>();
        if(StringUtil.isEmpty(user.getUsername())){
            map.put("success",false);
            map.put("errorInfo","请输入用户名！");
        }else if(StringUtil.isEmpty(user.getPassword())){
            map.put("success",false);
            map.put("errorInfo","请输入密码！");
        }else{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),CryptographyUtil.md5(user.getPassword(),CryptographyUtil.SALT));
            try {
                subject.login(token);       //登录验证
                String userName = (String) SecurityUtils.getSubject().getPrincipal();
                User currentUser = userService.findUserByUsername(userName);
                if (currentUser.isOff()) {
                    map.put("success", false);
                    map.put("errorInfo", "该用户已封禁，请联系管理员！");
                    subject.logout();
                } else {
                    currentUser.setLatelyLoginTime(new Date());
                    userService.updateById(currentUser);
                    session.setAttribute(Consts.CURRENT_USER,currentUser);
                    map.put("success", true);
                    map.put("message","登陆成功");
                }
            }catch (Exception e){
                e.printStackTrace();
                map.put("success", false);
                map.put("errorInfo", "用户名或密码错误！");
            }
        }
        return map;


    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public Map<String,Object> register(User user){
        HashMap<String, Object> map = new HashMap<>();
        if(!validateUser(user)){
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


    /**
     * 根据发送过来的email地址，我们随机生成一个6位数的验证码发送给指定的邮箱
     * @param email
     * @return
     */
    @PostMapping("/sendEmail")
    @ResponseBody
    public Map<String,Object> sendEmail(String email,HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        if(StringUtil.isEmpty(email)){
            map.put("success",false);
            map.put("message","邮箱不能为空");
            return map;
        }
        User currentUser = userService.findUserByEmail(email);
        if(currentUser==null){
            map.put("success",false);
            map.put("message","该邮箱未进行注册");
            return map;
        }

        String code = StringUtil.genSixRandom();
        MailUtil.sendMessageToMail(mailSender,email,code);
        //验证码存到session
        //其实这里我觉得不应该将验证码存放到session中。像这里的存放方法，MAIL_CODE_NAME不具有全局唯一性，
        //当有多个用户进行注册的时候，这里就会被覆盖，而且验证码通常有半个小时过期的特性，这里也没有。
        //其实不对，我又想起了，session具有唯一性，一个浏览器与服务器产生一个唯一的session，所以这里MAIL_CODE_NAME,对于
        //session来说是唯一的，因为浏览器只能是单用户，所以就不存在并发问题了

        //所以，我建议 1.如果坚持使用session，为保证多用户都能够进行操作的前提下，使用id_MAil区分用户(我理解错了)
        //2. 使用redis存储验证码的信息 TODO
        session.setAttribute(Consts.MAIL_CODE_NAME,code);
        session.setAttribute(Consts.USER_ID_NAME,currentUser.getUserId());

        map.put("success",true);
        map.put("message","发送验证码成功");
        return map;

    }


    /**
     * 验证码的判断
     */
    @ResponseBody
    @PostMapping("/checkYzm")
    public Map<String,Object> checkYzm(String yzm, HttpSession session){
        HashMap<String, Object> map = new HashMap<>();
        if(StringUtil.isEmpty(yzm)){
            map.put("success",false);
            map.put("message","验证码不能为空");
            return map;
        }

        /**
         * 这里也是使用session，我准备使用redis进行二次开发
         * TODO
         */
        String code = (String)session.getAttribute(Consts.MAIL_CODE_NAME);
        Integer id=(Integer)session.getAttribute(Consts.USER_ID_NAME);
        if(!yzm.equals(code)){
            map.put("success",false);
            map.put("message","验证码错误");
            return map;
        }

        User userById = userService.getUserById(id);
        userById.setPassword(CryptographyUtil.md5(Consts.PASSWORD,CryptographyUtil.SALT));
        userService.updateById(userById);
        map.put("success",true);
        map.put("message","验证成功");
        return map;
    }

    /**
     * 验证user的必填字段是否有默认值
     * TODO
     * @param user
     * @return
     */
    public boolean validateUser(User user){

        return true;
    }


    @RequestMapping("getById/{id}")
    @ResponseBody
    public User getById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }


}
