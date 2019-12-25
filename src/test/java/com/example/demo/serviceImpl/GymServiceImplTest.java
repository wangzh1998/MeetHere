package com.example.demo.serviceImpl;

import com.example.demo.dao.FieldDao;
import com.example.demo.dao.GymDao;
import com.example.demo.entity.Gym;
import com.example.demo.vo.GymAndField;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@RunWith(SpringRunner.class)
class GymServiceImplTest {

    @Mock
    private GymDao gymDao;
    @InjectMocks
    private GymServiceImpl gymService;
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    //和DAO层测试发生了重复
    @Test
    void queryAvailableGym() {

        Gym r1 = new Gym(1,"大学生活动中心","7:00","9:00");
        Gym r2 = new Gym(2,"中北体育馆","10:00","12:00");
        List<Gym> gymList = new ArrayList<>();
        gymList.add(r1);
        gymList.add(r2);
        when(gymDao.queryAvailableGym(anyString(),anyString(),anyString())).thenReturn(gymList);
        List<Gym> resultList = gymService.queryAvailableGym(anyString(),anyString(),anyString());
        verify(gymDao,times(1)).queryAvailableGym(anyString(),anyString(),anyString());

        assertAll(
                ()->assertEquals(2,resultList.size()),
                ()->assertEquals("7:00",resultList.get(0).getStartTime()),
                ()->assertEquals("9:00",resultList.get(0).getEndTime()),
                ()->assertEquals("大学生活动中心",resultList.get(0).getGymName()),
                ()->assertEquals(1,resultList.get(0).getGymId()),
                ()->assertEquals("中北体育馆",resultList.get(1).getGymName())
        );
    }
}