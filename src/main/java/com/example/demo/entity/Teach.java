package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

//new entity class
@Entity
@IdClass(com.example.demo.entity.Teach.class)
public class Teach implements Serializable {

   // @ForeignKey("com.example.demo.entity.User")
    @Id
    private int userId;
    @Id
    private int courseId;

    public Teach() {
    }

    public Teach(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCourseId() {
        return courseId;
    }
}
