package com.example.demo.controller;

import com.example.demo.service.AnnounceService;
import com.example.demo.service.CourseService;
import com.example.demo.service.ReserveService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @MockBean
    CourseService courseService;
    @MockBean
    AnnounceService announceService;
    @MockBean
    ReserveService reserveService;

    @Autowired
    MockMvc mockMvc;

    //MOCK用户的权限前面不能加ROLE,不能写成"ROLE_STUDENT"
    @Test
    @WithMockUser(username="student",roles = {"STUDENT"})
    void queryAllCourse_student_ok() throws Exception{
        mockMvc.perform(get("/student/query/allcourse"))
                .andExpect(status().isOk());
        verify(courseService, times(1)).queryAllCourse();
    }
    @Test
    @WithMockUser(username="tourist",roles = {"TOURIST"})
    void queryAllCourse_tourist_forbidden() throws Exception{
        mockMvc.perform(get("/student/query/allcourse"))
                .andExpect(status().isForbidden());
        verify(courseService, times(0)).queryAllCourse();
    }


    @Test
    @WithMockUser(username="student",roles = {"STUDENT"})
    void queryCourseByUId_student_ok() throws Exception{
        mockMvc.perform(get("/student/query/mycourse"))
                .andExpect(status().isOk());
        verify(courseService, times(1)).queryCourseByStudent(any());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"TOURIST"})
    void queryCourseByUId_tourist_forbidden() throws Exception{
        mockMvc.perform(get("/student/query/mycourse"))
                .andExpect(status().isForbidden());
        verify(courseService, times(0)).queryCourseByStudent(any());
    }


    @Test
    @WithMockUser(username="student",roles = {"STUDENT"})
    void joinCourse_student_ok() throws Exception{
        mockMvc.perform(post("/student/add/course")
                .param("courseid",String.valueOf(1)))
                .andExpect(status().isOk());
        verify(courseService, times(1)).takeCourse(any(),anyInt());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"TOURIST"})
    void joinCourse_tourist_forbidden() throws Exception{
        mockMvc.perform(post("/student/add/course")
                .param("courseid",String.valueOf(1)))
                .andExpect(status().isForbidden());
        verify(courseService, times(0)).takeCourse(any(),anyInt());
    }

    @Test
    @WithMockUser(username="student",roles = {"STUDENT"})
    void quitCourse_student_ok() throws Exception{
        mockMvc.perform(post("/student/delete/course")
                .param("courseid",String.valueOf(1)))
                .andExpect(status().isOk());
        verify(courseService, times(1)).deleteCourseByStudent(any(),anyInt());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"TOURIST"})
    void quitCourse_tourist_forbidden() throws Exception{
        mockMvc.perform(post("/student/delete/course")
                .param("courseid",String.valueOf(1)))
                .andExpect(status().isForbidden());
        verify(courseService, times(0)).deleteCourseByStudent(any(),anyInt());
    }


    /*@Test
    @WithMockUser(username="student",roles = {"ROLE_STUDENT"})
    void addReserve_student_ok() throws Exception{
        mockMvc.perform(get("/student/add/reserve")
                .param("gymid", String.valueOf(1))
                .param("fieldid", String.valueOf(1))
                .param("date", "2019-12-24")
                .param("starttime","10:00")
                .param("endtime","11:30"))
                .andExpect(status().isOk());
        verify(reserveService, times(1)).addReserve(any());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"ROLE_TOURIST"})
    void addReserve_tourist_forbidden() throws Exception{
        mockMvc.perform(get("/student/add/reserve")
                .param("gymid", String.valueOf(1))
                .param("fieldid", String.valueOf(1))
                .param("date", "2019-12-24")
                .param("starttime","10:00")
                .param("endtime","11:30"))
                .andExpect(status().isForbidden());
        verify(reserveService, times(0)).addReserve(any());
    }


    @Test
    @WithMockUser(username="student",roles = {"ROLE_STUDENT"})
    void deleteReserve_student_ok() throws Exception{
        mockMvc.perform(get("/student/delete/reserve")
                .param("reserveid", String.valueOf(1)))
                .andExpect(status().isOk());
        verify(reserveService, times(1)).deleteReserve(any());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"ROLE_TOURIST"})
    void deleteReserve_tourist_forbidden() throws Exception{
        mockMvc.perform(get("/student/delete/reserve")
                .param("reserveid", String.valueOf(1)))
                .andExpect(status().isForbidden());
        verify(reserveService, times(0)).deleteReserve(any());
    }

    @Test
    @WithMockUser(username="student",roles = {"ROLE_STUDENT"})
    void queryReserve_student_ok() throws Exception{
        mockMvc.perform(get("/student/query/reserve"))
                .andExpect(status().isOk());
        verify(reserveService, times(1)).queryReserveByUser(any());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"ROLE_TOURIST"})
    void queryReserve_tourist_forbidden() throws Exception{
        mockMvc.perform(get("/student/query/reserve"))
                .andExpect(status().isForbidden());
        verify(reserveService, times(0)).queryReserveByUser(any());
    }*/
}