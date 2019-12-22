package com.example.demo.vo;

public class ReserveAndUserAndGymAndField {
    private int reserveId;
    private int userId;
    private String userName;//
    private int gymId;
    private String gymName;//
    private int fieldId;
    private String fieldName;//
    private String date;
    private String startTime;
    private String endTime;

    public ReserveAndUserAndGymAndField() {
    }


    public ReserveAndUserAndGymAndField(int reserveId, int userId, String userName, int gymId, String gymName, int fieldId,
                                        String fieldName, String date, String startTime, String endTime) {
        this.reserveId = reserveId;
        this.userId = userId;
        this.userName = userName;
        this.gymId = gymId;
        this.gymName = gymName;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    public int getReserveId() {
        return reserveId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getGymId() {
        return gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public int getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
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
}
