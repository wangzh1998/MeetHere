package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import java.util.List;

import com.example.demo.vo.CourseAndGym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy
@RequestMapping({"/student"})
public class StudentController {
    @Autowired
    CourseService courseService;

    public StudentController() {
    }

    @RequestMapping(
            value = {"/query/allcourse"},
            method = {RequestMethod.GET}
    )
    public List<CourseAndGym> queryAllCourse() {
        List<CourseAndGym> courselist = this.courseService.queryAllCourse();
        return courselist;
    }

    @RequestMapping(
            value = {"/query/mycourse"},
            method = {RequestMethod.GET}
    )
    public List<CourseAndGym> queryCourseByUId() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<CourseAndGym> courselist = this.courseService.queryCorseByStudent(username);
        return courselist;
    }

    @RequestMapping(
            value = {"/add/course"},
            method = {RequestMethod.POST}
    )
    public String joinCourse(@RequestParam(value = "courseid",required = true) int courseid) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        try {
            this.courseService.takeCourse(username, courseid);
            return "加入课程成功";
        } catch (Exception var5) {
            return var5.getMessage();
        }
    }

    @RequestMapping(
            value = {"/delete/course"},
            method = {RequestMethod.POST}
    )
    public String quitCourse(@RequestParam(value = "courseid",required = true) int courseid) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        try {
            this.courseService.deleteCourseByStudent(username, courseid);
            return "删除课程成功";
        } catch (Exception var5) {
            return var5.getMessage();
        }
    }
}
