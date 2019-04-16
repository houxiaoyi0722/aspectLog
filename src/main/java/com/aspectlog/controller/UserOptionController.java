package com.aspectlog.controller;

import com.aspectlog.Enumeration.LogType;
import com.aspectlog.annotation.Log;
import com.aspectlog.service.UserOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/operation")
public class UserOptionController {

    @Autowired
    UserOptionService userOptionService;

    @ResponseBody
    @RequestMapping("/test")
    @Log(type = LogType.LOGIN,contect = "登录")
    public String test(String aaa){
        String test = userOptionService.test(aaa);
        //int i = 1 / 0;
        return test;
    }

}
