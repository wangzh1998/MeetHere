package com.example.demo.dao;

import com.example.demo.entity.Teach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TeachDao extends JpaRepository<Teach,Integer> {
    //int addTeaches(int userId, int courseId);

    //int queryUserId(int courseId);
    int findUserIDByCourseId(int courseId);

    //int deleteTeaches(int courseId);
    int deleteByCourseId(int courseId);
}
