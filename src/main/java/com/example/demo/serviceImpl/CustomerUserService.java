package com.example.demo.serviceImpl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.vo.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    public CustomerUserService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not exist");
        } else {
            return user;
        }
    }
}
