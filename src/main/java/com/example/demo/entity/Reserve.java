package com.example.demo.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reserve {
    @Id
    //@GeneratedValue
    private int reserveId;
    private int userId;//add
    private int gymId;
    private int fieldId;
    //private String gymName;
    //private String fieldName;
    private String date;
    private String startTime;
    private String endTime;
    //private String userName;

    public Reserve() {
    }

    public Reserve(int userId, int gymId, int fieldId, String date, String startTime, String endTime) {
        this.userId = userId;
        this.gymId = gymId;
        this.fieldId = fieldId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getReserveId() {
        return reserveId;
    }

    public int getUserId() {
        return userId;
    }

    public int getGymId() {
        return gymId;
    }

    public int getFieldId() {
        return fieldId;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
