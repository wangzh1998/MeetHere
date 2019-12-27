package com.example.demo.serviceImpl;

import com.example.demo.dao.AnnounceDao;
import com.example.demo.dao.FieldDao;
import com.example.demo.entity.Announce;
import com.example.demo.vo.GymAndField;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@SpringBootTest
@RunWith(SpringRunner.class)
class FieldServiceImplTest {

    @Mock
    private FieldDao fieldDao;
    @InjectMocks
    private FieldServiceImpl fieldService;
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    //和DAO层测试发生了重复
    @Test
    void queryAvailableField() {
        GymAndField r1 = new GymAndField(1,"大学生活动中心","7:00","9:00",1,"羽毛球场");
        GymAndField r2 = new GymAndField(2,"中北体育馆","10:00","12:00",2,"篮球场");
        List<GymAndField> gymAndFieldList = new ArrayList<>();
        gymAndFieldList.add(r1);
        gymAndFieldList.add(r2);
        when(fieldDao.queryAvailableField(anyString(),anyString(),anyString(),anyString())).thenReturn(gymAndFieldList);
        List<GymAndField> resultList = fieldService.queryAvailableField(anyString(),anyString(),anyString(),anyString());
        verify(fieldDao,times(1)).queryAvailableField(anyString(),anyString(),anyString(),anyString());

        assertAll(
                ()->assertEquals(2,resultList.size()),
                ()->assertEquals("羽毛球场",resultList.get(0).getFieldName()),
                ()->assertEquals("7:00",resultList.get(0).getStartTime()),
                ()->assertEquals("9:00",resultList.get(0).getEndTime()),
                ()->assertEquals("大学生活动中心",resultList.get(0).getGymName()),
                ()->assertEquals(1,resultList.get(0).getGymId()),
                ()->assertEquals(1,resultList.get(0).getFieldId()),
                ()->assertEquals("篮球场",resultList.get(1).getFieldName())
        );
    }
}