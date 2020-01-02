package com.example.demo.controller;

import com.example.demo.service.AnnounceService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @MockBean
    AnnounceService announceService;

    @Autowired
    MockMvc mockMvc;


    @Test
    @WithMockUser(username="admin",roles = {"ADMIN"})
    void queryAllCourse_admin_ok() throws Exception{
        mockMvc.perform(post("/admin/add/announce")
        .param("time","2019-12-29")
        .param("content","这是一条控制层测试通知"))
                .andExpect(status().isOk());
        verify(announceService, times(1)).addAnnounce(any());
    }
    @Test
    @WithMockUser(username="tourist",roles = {"TOURIST"})
    void queryAllCourse_tourist_forbidden() throws Exception{
        mockMvc.perform(post("/admin/add/announce")
                .param("time","2019-12-29")
                .param("content","这是一条控制层测试通知"))
                .andExpect(status().isForbidden());
        verify(announceService, times(0)).addAnnounce(any());
    }
    @Test
    @WithMockUser(username="teacher",roles = {"TEACHER"})
    void queryAllCourse_teacher_forbidden() throws Exception{
        mockMvc.perform(post("/admin/add/announce")
                .param("time","2019-12-29")
                .param("content","这是一条控制层测试通知"))
                .andExpect(status().isForbidden());
        verify(announceService, times(0)).addAnnounce(any());
    }
    @Test
    @WithMockUser(username="student",roles = {"STUDENT"})
    void queryAllCourse_student_forbidden() throws Exception{
        mockMvc.perform(post("/admin/add/announce")
                .param("time","2019-12-29")
                .param("content","这是一条控制层测试通知"))
                .andExpect(status().isForbidden());
        verify(announceService, times(0)).addAnnounce(any());
    }
}