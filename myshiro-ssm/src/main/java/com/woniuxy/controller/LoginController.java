package com.woniuxy.controller;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("goLogin.html")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("login.html")
    public String login(String userName, String password, HttpServletRequest request){
        User user=userService.getLogin(userName,password);
        if(null==user){
            request.setAttribute("error","用户名或密码有误！");
            return "login";
        }else{
            return "redirect:index.html";
        }
    }
}
