package com.example.demo.dao;

import com.example.demo.entity.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TakeDao extends JpaRepository<Take,Integer> {
    //int addTakes(int userId, int courseId);

    //int deleteTakesByTeacher(int courseId);
    int deleteByCourseId(int courseId);

    //int deleteTakesByStudent(int userId, int courseId);
    int deleteByUserIdAndCourseId(int userId, int courseId);
}
