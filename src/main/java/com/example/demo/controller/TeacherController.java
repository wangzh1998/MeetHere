package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Gym;
import com.example.demo.service.CourseService;
import com.example.demo.service.GymService;
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

/*
这里addCourse里面的gymname参数是无效的，删除
 */
@RestController
@EnableAspectJAutoProxy
@RequestMapping({"/teacher"})
public class TeacherController {
    @Autowired
    CourseService courseService;
    @Autowired
    GymService gymService;

    public TeacherController() {
    }

    @RequestMapping(
            value = {"/add/course"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=gbk"}
    )
    public String addCourse(@RequestParam(value = "coursename",required = true) String courseName,
                            @RequestParam(value = "weekday",required = true) String weekday,
                            @RequestParam(value = "starttime",required = true) String startTime,
                            @RequestParam(value = "endtime",required = true) String endTime,
                            @RequestParam(value = "gymid",required = true) int gymId
                            ) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Course course = new Course();
        course.setCourseName(courseName);
        course.setWeekday(weekday);
        course.setStartTime(startTime);
        course.setEndTime(endTime);
        course.setGymId(gymId);
        //course.setGymName(gymName);
        course.setTeacherName(username);

        try {
            this.courseService.addCourse(course);
            return "添加课程成功";
        } catch (Exception var11) {
            return var11.getMessage();
        }
    }

    @RequestMapping(
            value = {"/delete/course"},
            method = {RequestMethod.POST}
    )
    public String deleteCourse(@RequestParam(value = "courseid",required = true) int courseid) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        try {
            this.courseService.deleteCourseByTeacher(username, courseid);
            return "删除课程成功";
        } catch (Exception var5) {
            return var5.getMessage();
        }
    }

    @RequestMapping(
            value = {"/query/course"},
            method = {RequestMethod.GET}
    )
    public List<CourseAndGym> queryCourse() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<CourseAndGym> courselist = this.courseService.queryCourseByTeacher(username);
        return courselist;
    }

    @RequestMapping(
            value = {"/query/gym"},
            method = {RequestMethod.GET}
    )
    public List<Gym> queryAvailableGym(@RequestParam(value = "weekday",required = true) String weekday,
                                       @RequestParam(value = "starttime",required = true) String starttime,
                                       @RequestParam(value = "endtime",required = true) String endtime) {
        return this.gymService.queryAvailableGym(weekday, starttime, endtime);
    }
}
