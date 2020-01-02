package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ConfigControllerTest {

   /* @Autowired
    MockMvc mockMvc;
    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    UserService userService;

    @Test
    void bCryptPasswordEncoder() {

    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void return0() throws Exception{
        mockMvc.perform(post("/login/return0"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void return1() throws Exception{
        mockMvc.perform(post("/login/return1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void signIn() throws Exception{
        mockMvc.perform(post("/login/signIn"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"TEACHER"})
    void signOut() throws Exception{
        mockMvc.perform(post("/login/signOut"))
                .andExpect(status().isOk());
    }

    @Test
    void getPassword() throws Exception{
        mockMvc.perform(post("/login/getPassword"))
                .andExpect(status().isOk());
    }*/
}