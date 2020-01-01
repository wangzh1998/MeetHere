package com.example.demo.serviceImpl;

import com.example.demo.dao.ReserveDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Reserve;
import com.example.demo.vo.ReserveAndUserAndGymAndField;
import com.example.demo.vo.UserAndRole;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@RunWith(SpringRunner.class)
class ReserveServiceImplTest {

    @Mock
    ReserveDao reserveDao;
    @Mock
    UserDao userDao;
    @InjectMocks
    ReserveServiceImpl reserveService;

    private  ReserveAndUserAndGymAndField reserveInfo;

    @BeforeEach
    public void init() {
        reserveInfo = new ReserveAndUserAndGymAndField();
        reserveInfo.setUserId(3);
        reserveInfo.setUserName("张诗晨");
        reserveInfo.setDate("2020-1-1");
        reserveInfo.setFieldId(2);
        reserveInfo.setFieldName("2号场地");
        reserveInfo.setGymId(1);
        reserveInfo.setGymName("中北大学生活动中心羽毛球馆 ");
        reserveInfo.setStartTime("13:00");
        reserveInfo.setEndTime("15:00");
    }

    @Test
    void addReserve_save_test() {
        UserAndRole user = new UserAndRole();
        user.setName("张诗晨");
        user.setPassword("123456");
        user.setUserName("张诗晨");
        user.setRoleName("ROLE_STUDENT");
        user.setRoleId(3);
        user.setUserId(5);
        when(userDao.findUserAndRoleByUserName(reserveInfo.getUserName())).thenReturn(user);
        when(reserveDao.save(any())).thenReturn(null);
        try{
            reserveService.addReserve(reserveInfo);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }


    @Test
    void deleteReserve_delete_test() {
    }

    @Test
    void queryReserveByUser1() {

        List<ReserveAndUserAndGymAndField> reserveAndUserAndGymAndField = new ArrayList<>();
        when(reserveDao.queryReserveInfoByUser(anyString())).thenReturn(reserveAndUserAndGymAndField);
        List<ReserveAndUserAndGymAndField> result = reserveService.queryReserveByUser(anyString());
        verify(reserveDao,times(1)).queryReserveInfoByUser(anyString());
    }

    @Test
    void queryReserveByUser2() {

        List<ReserveAndUserAndGymAndField> reserveAndUserAndGymAndField = new ArrayList<>();
        when(reserveDao.queryReserveByUser(anyInt())).thenReturn(reserveAndUserAndGymAndField);
        List<ReserveAndUserAndGymAndField> result = reserveService.queryReserveByUser(anyInt());
        verify(reserveDao,times(1)).queryReserveByUser(anyInt());
    }
}