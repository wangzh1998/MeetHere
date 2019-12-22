package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.UserAndRole;

public interface UserService {
    UserAndRole queryUser(String username);
}
