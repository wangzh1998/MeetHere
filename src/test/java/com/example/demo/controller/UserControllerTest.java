package com.example.demo.controller;

import com.example.demo.service.AnnounceService;
import com.example.demo.service.FieldService;
import com.example.demo.service.ReserveService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ReserveService reserveService;
    @MockBean
    FieldService fieldService;
    @MockBean
    UserService userService;
    @MockBean
    AnnounceService announceService;

    @Test
    @WithMockUser
    void addReserve() throws Exception {
        mockMvc.perform(post("/user/add/reserve")
                .param("gymid", String.valueOf(1))
                .param("fieldid", String.valueOf(1))
                .param("date", "2019-12-27")
                .param("starttime","10:00")
                .param("endtime","12:00"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        verify(reserveService, times(1)).addReserve(any());
    }

    @Test
    @WithMockUser
    void deleteReserve() throws Exception {
        mockMvc.perform(post("/user/delete/reserve")
                .param("reserveid", String.valueOf(1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        verify(reserveService, times(1)).deleteReserve(1);
    }

    @Test
    @WithMockUser
    void queryReserve() throws Exception {
        mockMvc.perform(get("/user/query/reserve"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        verify(reserveService, times(1)).queryReserveByUser(any());
    }

    @Test
    @WithMockUser
    void queryAnnounce() throws Exception {
        mockMvc.perform(get("/user/query/announce"))
                .andExpect(status().isOk());
        verify(announceService, times(1)).queryAnnounce();
    }

    @Test
    @WithMockUser
    void queryAvailableField() throws Exception {
        mockMvc.perform(get("/user/query/field")
                .param("date", "2019-12-27")
                .param("starttime","10:00")
                .param("endtime","12:00"))
                .andExpect(status().isOk());
        verify(fieldService, times(1)).queryAvailableField(any(),any(),any(),any());
    }


    @Test
    @WithMockUser
    void queryUser() throws Exception {
        mockMvc.perform(get("/user/query/user"))
                .andExpect(status().isOk());
        verify(userService, times(1)).queryUser(any());
    }
}