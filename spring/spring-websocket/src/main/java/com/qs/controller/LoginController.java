package com.qs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.qs.constant.Constant;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author : qshomewy
 * @description : 简单登录
 */
@Controller
public class LoginController {

    @PostMapping("login")
    public String login(String username, HttpSession session){
        session.setAttribute(Constant.USER_NAME,username);
        return "chat";
    }
}
