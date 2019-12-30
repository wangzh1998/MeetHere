package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping({"/login"})
public class ConfigController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public ConfigController() {
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //登陆成功：将reutrn 0 改为返回角色的认证信息
    @RequestMapping({"/return0"})
    public Collection<? extends GrantedAuthority> return0() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getAuthorities();
    }

    @RequestMapping({"/return1"})
    public int return1() {
        return 1;
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
