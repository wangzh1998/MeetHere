package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.vo.CourseAndGym;

import java.util.List;

public interface CourseService {
    void addCourse(Course course) throws Exception;

    void takeCourse(String name, int course_id) throws Exception;

    List<CourseAndGym> queryCourseByTeacher(String name);

    List<CourseAndGym> queryCourseByStudent(String name);

    void deleteCourseByTeacher(String username, int course_id) throws Exception;

    void deleteCourseByStudent(String name, int course_id) throws Exception;

    List<CourseAndGym> queryAllCourse();
}