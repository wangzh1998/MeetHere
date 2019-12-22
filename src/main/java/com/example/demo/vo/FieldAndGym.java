package com.example.demo.vo;

import javax.persistence.Id;

public class FieldAndGym {

    private int gymId;
    private String gymName;
    private int fieldId;
    private String fieldName;

    public FieldAndGym() {
    }


    public FieldAndGym(int gymId, String gymName, int fieldId, String fieldName) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
    }

    public String getGymName() {
        return gymName;
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

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }
}
