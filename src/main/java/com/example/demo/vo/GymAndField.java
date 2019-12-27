package com.example.demo.vo;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@Immutable //虚拟视图
/*@Subselect("SELECT g.gym_id,g.gym_name,g.start_time,g.end_time,f.field_id,f.field_name " +
        "FROM gym g " +
        "LEFT JOIN field f " +
        "ON g.gym_id = f.gym_id")*/
@IdClass(com.example.demo.entity.FieldCompositeId.class)
public class GymAndField {
    @Id
    private int gymId;
    private String gymName;
    private String startTime;
    private String endTime;
    @Id
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
