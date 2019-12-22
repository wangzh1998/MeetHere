package com.example.demo.dao;

import com.example.demo.entity.User;
import com.example.demo.vo.UserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserDao extends JpaRepository<User,Integer> {
    //User queryUserById(int uid);

    //User queryUserByUserName(String username);
    //User findUserByUserName(String username);

    //add
    @Query(value = "select new com.example.demo.vo.UserAndRole " +
            "(u.userId,u.userName,u.password,u.name,u.roleId,r.roleName) " +
            "from User u join Role r " +
            "on u.userName = ?1")
    UserAndRole findUserAndRoleByUserName(String username);
}