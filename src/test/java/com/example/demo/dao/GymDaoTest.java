package com.example.demo.dao;

import com.example.demo.entity.Gym;
import com.example.demo.vo.GymAndField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
class GymDaoTest {

    @Autowired
    GymDao gymDao ;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public  void  setUp(){
        DataSupplier dataSupplier = new DataSupplier();
        dataSupplier.loadGym(entityManager);
        dataSupplier.loadField(entityManager);
        dataSupplier.loadReserve(entityManager);
        dataSupplier.loadCourse(entityManager);
    }

    @AfterEach
    public void cleanUp(){
        entityManager.clear();
    }

    @ParameterizedTest(name = "{index} test : weekday = {0},start_time ={1},end_Time ={2},available_gym = {3}")
    @MethodSource("return_only_1_gym_when_query_available_gym_provider")
    void return_only_1_gym_when_query_available_gym(String weekday,String start_time,String end_time,int expected_gym_id) {
        List<Gym> resultList = gymDao.queryAvailableGym(weekday,start_time,end_time);
        assertNotNull(resultList);
        assertEquals(1,resultList.size(),"查询出的可用场馆应为1个");
        assertEquals(expected_gym_id,resultList.get(0).getGymId());
    }
    static Stream<Arguments> return_only_1_gym_when_query_available_gym_provider(){
        return Stream.of(
                Arguments.arguments("周一","08:00","11:00", 2),
                Arguments.arguments("周一","07:00","09:30", 2),
                Arguments.arguments("周五","08:00","11:00", 1)
               );
    }

    @ParameterizedTest(name = "{index} test : weekday = {0},start_time ={1},end_Time ={2},available_gym_1 = {3},available_gym_2 = {4}")
    @MethodSource("return_both_2_gym_when_query_available_gym_provider")
    void return_2_gym_when_query_available_gym(String weekday,String start_time,String end_time,int expected_gym_id_1,int expected_gym_id_2) {
        List<Gym> resultList = gymDao.queryAvailableGym(weekday,start_time,end_time);
        assertNotNull(resultList);
        assertEquals(2,resultList.size(),"查询出的可用场馆应为2个");
        assertAll(
                ()->assertEquals(expected_gym_id_1,resultList.get(0).getGymId()),
                ()->assertEquals(expected_gym_id_2,resultList.get(1).getGymId())
        );
    }
    static Stream<Arguments> return_both_2_gym_when_query_available_gym_provider(){
        return Stream.of(
                Arguments.arguments("周一","08:00","10:00", 1,2),
                Arguments.arguments("周二","10:00","11:00", 1,2),
                Arguments.arguments("周三","08:00","10:00", 1,2),
                Arguments.arguments("周四","08:00","10:00", 1,2),
                Arguments.arguments("周六","08:00","10:00", 1,2),
                Arguments.arguments("周日","08:00","10:00", 1,2)
        );
    }

    @ParameterizedTest(name = "Corner {index} test : weekday = {0},start_time ={1},end_Time ={2},no available gym")
    @MethodSource("return_0_gym_when_query_available_gym_provider")
    void return_0_gym_when_query_available_gym(String weekday,String start_time,String end_time) {
        List<Gym> resultList = gymDao.queryAvailableGym(weekday,start_time,end_time);
        assertNotNull(resultList);
        assertEquals(0,resultList.size());
    }
    static Stream<Arguments> return_0_gym_when_query_available_gym_provider(){
        return Stream.of(
                Arguments.arguments("周二","08:00","09:30"),
                Arguments.arguments("周一","06:00","07:00"),
                Arguments.arguments("周一","21:00","23:00"),
                Arguments.arguments("周一","22:00","23:00")
        );
    }

    @Test
    @DisplayName("Corner {index} test : weekday ='周'(invalid argument), both gym available")
    void return_both_2_gym_when_query_available_gym() {
        List<Gym> resultList = gymDao.queryAvailableGym("周","08:00","10:00");
        assertNotNull(resultList);
        assertEquals(2,resultList.size(),"当weedday参数不正确时，所有gym都在该weekday可用，所以会返回所有场馆");
    }
}