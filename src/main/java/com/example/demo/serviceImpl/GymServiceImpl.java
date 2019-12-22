package com.example.demo.serviceImpl;

import com.example.demo.dao.GymDao;
import com.example.demo.entity.Gym;
import com.example.demo.service.GymService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymServiceImpl implements GymService {
    @Autowired
    GymDao gymDao;

    public GymServiceImpl() {
    }

    public List<Gym> queryAvailableGym(String weekday, String startTime, String endTime) {
        return this.gymDao.queryAvailableGym(weekday, startTime, endTime);
    }
}
