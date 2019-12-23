package com.example.demo.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int courseId;

    private String courseName;
    private String weekday;
    private String startTime;
    private String endTime;
    //private String gymName;
    private int gymId;
    private String teacherName;

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }



    public String getWeekday() {
        return weekday;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getGymId() {
        return gymId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
