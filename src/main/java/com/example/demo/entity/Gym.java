package com.example.demo.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gym {
    @Id
    //@GeneratedValue
    private int gymId;
    private String gymName;
    private String startTime;
    private String endTime;


    public Gym() {
    }


 /*   public Gym(int gymId, String gymName, String startTime, String endTime) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.startTime = startTime;
        this.endTime = endTime;
    }*/

    public Gym(String gymName, String startTime, String endTime) {
        this.gymName = gymName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getGymName() {
        return this.gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public int getGymId() {
        return this.gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
