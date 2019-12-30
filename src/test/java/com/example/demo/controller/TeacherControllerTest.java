package com.example.demo.controller;

import com.example.demo.service.CourseService;
import com.example.demo.service.GymService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CourseService courseService;
    @MockBean
    GymService gymService;

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void addCourse() throws Exception {
        mockMvc.perform(post("/teacher/add/course")
                .param("coursename", "乒乓球")
                .param("weekday","周二")
                .param("starttime","10:00")
                .param("endtime","11:30")
                .param("gymid", String.valueOf(1)))
                .andExpect(status().isOk());
        verify(courseService, times(1)).addCourse(any());
    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void deleteCourse() throws Exception {
        mockMvc.perform(post("/teacher/delete/course")
                .param("courseid", String.valueOf(1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        verify(courseService, times(1)).deleteCourseByTeacher(anyString(),anyInt());
    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void queryCourse() throws Exception {
        mockMvc.perform(get("/teacher/query/course"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        verify(courseService, times(1)).queryCourseByTeacher(anyString());
    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void queryAvailableGym() throws Exception {
        mockMvc.perform(get("/teacher/query/gym")
                .param("weekday", "周五")
                .param("starttime","10:00")
                .param("endtime","12:00"))
                .andExpect(status().isOk());
        verify(gymService, times(1)).queryAvailableGym("周五","10:00","12:00");
    }
}