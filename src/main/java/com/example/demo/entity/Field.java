package com.example.demo.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(com.example.demo.entity.FieldCompositeId.class)
public class Field implements Serializable {
    @Id
    //@GeneratedValue
    private int fieldId;
    @Id
    private int gymId;
    //private String gymName;
    private String fieldName;

    public Field() {
    }

    public Field(int fieldId, int gymId, String fieldName) {
        this.fieldId = fieldId;
        this.gymId = gymId;
        this.fieldName = fieldName;
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
