package com.example.demo.dao;

import com.example.demo.entity.Teach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TeachDao extends JpaRepository<Teach,Integer> {
    //int addTeaches(int userId, int courseId);

    //int queryUserId(int courseId);
    @Query(value = "select user_id from teach where course_id = ?1",nativeQuery = true)
    int findUserIdByCourseId(int courseId) throws RuntimeException;


    //int deleteTeaches(int courseId);
    int deleteByCourseId(int courseId);
}
