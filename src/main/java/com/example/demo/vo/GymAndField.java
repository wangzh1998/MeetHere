package com.example.demo.vo;

import javax.persistence.Id;

public class GymAndField {
    private int gymId;
    private String gymName;
    private String startTime;
    private String endTime;
    private int fieldId;
    private String fieldName;

    public GymAndField() {
    }

    public GymAndField(int gymId, String gymName, String startTime, String endTime,
                       int fieldId, String fieldName) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getGymId() {
        return gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }
}
