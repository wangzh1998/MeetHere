package com.example.demo.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Announce {
    @Id
    //@GeneratedValue
    private int announceId;
    private String time;
    private String content;

    public Announce() {
    }

    public Announce(String time, String content) {
        //this.announceId =announceId;
        this.time = time;
        this.content = content;
    }

    public int getAnnounceId() {
        return this.announceId;
    }

    public void setAnnounceId(int id) {
        this.announceId = id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
