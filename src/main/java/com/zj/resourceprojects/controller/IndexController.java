package com.zj.resourceprojects.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根路径及其他路径的处理
 */
@Controller
public class IndexController {


    /**
     * 注意这里与前后端分离项目的区别，这里就不能使用responseBody来返回对象了。
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
