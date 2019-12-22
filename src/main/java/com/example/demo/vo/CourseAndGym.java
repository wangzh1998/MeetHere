package com.example.demo.vo;

import org.springframework.stereotype.Repository;


public class CourseAndGym {
    private int courseId;
    private String courseName;
    private String weekday;
    private String startTime;
    private String endTime;
    private int gymId;
    private String gymName;
    private String teacherName;

    public CourseAndGym() {
    }

    public CourseAndGym(int courseId, String courseName, String weekday, String startTime,
                        String endTime, int gymId, String gymName, String teacherName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
        this.gymId = gymId;
        this.gymName = gymName;
        this.teacherName = teacherName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
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

    public String getGymName() {
        return gymName;
    }

    public int getGymId() {
        return gymId;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
