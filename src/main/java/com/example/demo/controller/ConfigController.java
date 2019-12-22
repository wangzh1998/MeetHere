package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping({"/return0"})
    public int return0() {
        return 0;
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
