package com.example.demo.service;

import com.example.demo.entity.Gym;
import java.util.List;

public interface GymService {
    List<Gym> queryAvailableGym(String weekday, String startTime, String endTime);
}
