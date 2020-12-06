package com.ngu.springboot_shiro.controller;

import com.ngu.springboot_shiro.domain.Users;
import com.ngu.springboot_shiro.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            System.out.println("登录成功");
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping ("/logout")
    public String logout(){
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/register")
    public String register(Users users){
        try {
            usersService.register(users);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }
}
