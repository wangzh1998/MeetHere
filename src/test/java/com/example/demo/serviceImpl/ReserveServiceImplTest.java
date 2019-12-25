package com.example.demo.serviceImpl;

import com.example.demo.dao.ReserveDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Reserve;
import com.example.demo.vo.ReserveAndUserAndGymAndField;
import com.example.demo.vo.UserAndRole;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@AutoConfigureTestDatabase(replace = NONE)
class ReserveServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ReserveDao reserveDao;

    @Mock
    ReserveDao reserveDao2;
    @InjectMocks
    ReserveServiceImpl reserveService;

    private Reserve reserve1;

    @BeforeEach
    public void init() {
        reserve1 = new Reserve();
        reserve1.setUserId(1);
        reserve1.setGymId(1);
        reserve1.setFieldId(1);
        reserve1.setDate("2019-12-25");
        reserve1.setStartTime("9:00");
        reserve1.setEndTime("11:00");
    }

    @AfterEach
    public void cleanup() {
        this.entityManager.clear();
    }

    @Test
    void addReserve_save_test() {
        this.entityManager.persist(reserve1);
        Reserve result = reserveDao.save(reserve1);

        assertEquals(4, result.getReserveId());
        assertEquals(1, result.getUserId());
        assertEquals("2019-12-25",result.getDate());
    }


    @Test
    void deleteReserve_delete_test() {
        this.entityManager.persist(reserve1);
        Reserve result = reserveDao.save(reserve1);
        reserveDao.deleteById(6);
        List<Reserve> n = reserveDao.findAll();
        assertEquals(0,n.size());
    }

    @Test
    void queryReserveByUser() {

        List<ReserveAndUserAndGymAndField> reserveAndUserAndGymAndField = new ArrayList<>();
        when(reserveDao2.queryReserveInfoByUser(anyString())).thenReturn(reserveAndUserAndGymAndField);
        List<ReserveAndUserAndGymAndField> result = reserveService.queryReserveByUser(anyString());
        verify(reserveDao2,times(1)).queryReserveInfoByUser(anyString());
    }
}