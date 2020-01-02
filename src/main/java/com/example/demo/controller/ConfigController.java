package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.serviceImpl.UserServiceImpl;
import com.example.demo.vo.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Controller
@RequestMapping({"/login"})
public class ConfigController {
    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public ConfigController() {
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @RequestMapping({"/return0"})
    public String return0() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        UserAndRole user = this.userService.queryUser(username);
        int roleid = user.getRoleId();
        System.out.println("登录成功，roleid:"+roleid);
        if(roleid == 1){
            return "redirect:/admin_main.html";
        }
        else if(roleid == 2){
            return "redirect:/student_main.html";
        }
        else if(roleid == 3){
            return "redirect:/teacher_main.html";
        }
        else return "redirect:/tourist_main.html";
    }

    @RequestMapping({"/return1"})
    public String return1() {
        return "redirect:/index.html";
    }

    @RequestMapping({"/signIn"})
    public String signIn() {
        return "SignIn";
    }

    @RequestMapping({"/signOut"})
    public String signOut() {
        return "SignOut";
    }

    @RequestMapping({"/getPassword"})
    public String getPassword(@RequestParam(value = "password",required = true) String password) {
        String tmp = this.bCryptPasswordEncoder.encode(password);
        return tmp;
    }
}
