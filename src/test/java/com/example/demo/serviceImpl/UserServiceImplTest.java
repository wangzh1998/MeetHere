package com.example.demo.serviceImpl;

import com.example.demo.dao.UserDao;
import com.example.demo.vo.UserAndRole;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    @Mock
    UserDao userDao;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    void queryUser() {

        UserAndRole userAndRole = new UserAndRole();
        UserAndRole result = userService.queryUser(anyString());
        when(userDao.findUserAndRoleByUserName(anyString())).thenReturn(userAndRole);
        verify(userDao,times(1)).findUserAndRoleByUserName(anyString());
    }

}