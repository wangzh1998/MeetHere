package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    //@GeneratedValue
    private int userId;
    private String userName;
    private String password;
    private String name;
    //private String depr;//add
    //private Role role;
    private int roleId = -1;

    public User() {
    }



    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String userName, String password, String name, int roleId) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }



    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public int getRoleId() {
        return roleId;
    }
}
