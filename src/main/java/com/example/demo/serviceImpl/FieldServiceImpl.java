package com.example.demo.serviceImpl;

import com.example.demo.dao.FieldDao;
import com.example.demo.entity.Field;
import com.example.demo.service.FieldService;
import java.util.List;

import com.example.demo.vo.GymAndField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldDao fieldDao;

    public FieldServiceImpl() {
    }

    public List<GymAndField> queryAvailableField(String date, String weekday, String startTime, String endTime) {
        return this.fieldDao.queryAvailableField(date, weekday, startTime, endTime);
    }
}
