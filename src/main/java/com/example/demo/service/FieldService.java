package com.example.demo.service;

import com.example.demo.entity.Field;
import com.example.demo.vo.GymAndField;

import java.util.List;

public interface FieldService {
    List<GymAndField> queryAvailableField(String date, String weekday, String startTime, String endTime);
}
