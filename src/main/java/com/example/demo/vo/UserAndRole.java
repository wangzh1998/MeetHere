package com.example.demo.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAndRole implements UserDetails {
    private int userId;
    private String userName;
    private String password;
    private String name;
    //private String depr;//add
    //private Role role;
    private int roleId = -1;
    private String roleName;

    public UserAndRole() {
    }

    public UserAndRole(int userId, String userName, String password,
                       String name, int roleId, String roleName) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList();
        if (this.roleId == -1 || this.roleName==null) {//this.role == null
            System.out.println("UserAndRole:role error");
        } else {
            auths.add(new SimpleGrantedAuthority(this.roleName));
        }

        return auths;
    }


    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}
