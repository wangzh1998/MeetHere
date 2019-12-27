package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

//new entity class
@Entity
@IdClass(com.example.demo.entity.Take.class)
public class Take implements Serializable {
    @Id
    private int userId;
    @Id
    private int courseId;

    public Take() {
    }



    public Take(int userId, int courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Take)) return false;
        Take take = (Take) o;
        return getUserId() == take.getUserId() &&
                getCourseId() == take.getCourseId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCourseId());
    }
}
