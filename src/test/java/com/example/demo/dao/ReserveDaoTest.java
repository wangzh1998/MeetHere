package com.example.demo.dao;

import com.example.demo.vo.ReserveAndUserAndGymAndField;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
class ReserveDaoTest {
    @Autowired
    ReserveDao reserveDao ;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public  void  setUp(){
        DataSupplier dataSupplier = new DataSupplier();
        dataSupplier.loadGym(entityManager);
        dataSupplier.loadField(entityManager);
        dataSupplier.loadUser(entityManager);
        dataSupplier.loadReserve(entityManager);
    }

    @AfterEach
    public void cleanUp(){
        entityManager.clear();
    }


    @ParameterizedTest(name = "Happy : {index} test: user_name = {0} and gym_id ={1} and field_id ={2}")
    @MethodSource("should_return_1_reserve_when_user_has_only_one_reserve_provider")
    void should_return_1_reserve_when_user_has_only_one_reserve(String user_name,int expected_gym_id,int expected_field_id){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveInfoByUser(user_name);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(1,resultList.size())
        );
        ReserveAndUserAndGymAndField result = resultList.get(0);
        assertAll(
                ()->assertNotNull(result),
                ()->assertEquals(expected_gym_id,result.getGymId()),
                ()->assertEquals(expected_field_id,result.getFieldId())
        );
    }
    static Stream<Arguments>should_return_1_reserve_when_user_has_only_one_reserve_provider(){
        return Stream.of(
                Arguments.arguments("潘国平",2,1),
                Arguments.arguments("李雪莹",1,1)
        );
    }

    @DisplayName("Happy test: 王昭辉有两条预约：(2,1)(2,2)")
    @Test
    void should_return_2_reserve_when_user_has_2_reserve(){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveInfoByUser("王昭辉");
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(2,resultList.size())
        );
        ReserveAndUserAndGymAndField result_1 = resultList.get(0);
        ReserveAndUserAndGymAndField result_2 = resultList.get(1);
        assertAll(
                ()->assertNotNull(result_1),
                ()->assertEquals(2,result_1.getGymId()),
                ()->assertEquals(1,result_1.getFieldId()),
                ()->assertNotNull(result_2),
                ()->assertEquals(2,result_2.getGymId()),
                ()->assertEquals(2,result_2.getFieldId())
        );
    }

    @ParameterizedTest(name = "Happy : {index} test: user_name = {0} 用户名有效，但是没有预约纪录")
    @ValueSource(strings = {"钟晖","阎健","张诗晨","林梦思"})
    void should_return_nothing_when_user_has_only_one_reserve(String user_name){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveInfoByUser(user_name);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }

    @ParameterizedTest(name = "Corner : {index} test: user_name = {0} 参数异常or用户名不存在")
    @ValueSource(strings = {"DefaultUser","小蓝","李雪",""," "})
    void should_return_nothing_when_user_is_invalid(String user_name){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveInfoByUser(user_name);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }

    @DisplayName("Corner:用户名参数为null，返回nothing")
    void should_return_nothing_when_use_namer_is_null(){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveInfoByUser(null);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }


    @ParameterizedTest(name = "Happy : {index} test: user_id = {0} and gym_id ={1} and field_id ={2}")
    @MethodSource("should_return_1_reserve_when_user_has_only_one_reserve_provider_by_id")
    void should_return_1_reserve_when_user_has_only_one_reserve_by_id(int user_id,int expected_gym_id,int expected_field_id){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveByUser(user_id);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(1,resultList.size())
        );
        ReserveAndUserAndGymAndField result = resultList.get(0);
        assertAll(
                ()->assertNotNull(result),
                ()->assertEquals(expected_gym_id,result.getGymId()),
                ()->assertEquals(expected_field_id,result.getFieldId())
        );
    }
    static Stream<Arguments>should_return_1_reserve_when_user_has_only_one_reserve_provider_by_id(){
        return Stream.of(
                Arguments.arguments(3,2,1),
                Arguments.arguments(7,1,1)
        );
    }

    @DisplayName("Happy test: user_id = 5 王昭辉有两条预约：(2,1)(2,2)")
    @Test
    void should_return_2_reserve_when_user_has_2_reserve_by_id(){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveByUser(5);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(2,resultList.size())
        );
        ReserveAndUserAndGymAndField result_1 = resultList.get(0);
        ReserveAndUserAndGymAndField result_2 = resultList.get(1);
        assertAll(
                ()->assertNotNull(result_1),
                ()->assertEquals(2,result_1.getGymId()),
                ()->assertEquals(1,result_1.getFieldId()),
                ()->assertNotNull(result_2),
                ()->assertEquals(2,result_2.getGymId()),
                ()->assertEquals(2,result_2.getFieldId())
        );
    }

    @ParameterizedTest(name = "Happy : {index} test: user_id = {0} 用户id有效，但是没有预约纪录")
    @ValueSource(ints = {1,2,4,6,8,9})
    void should_return_nothing_when_user_has_only_one_reserve_by_id(int user_id ){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveByUser(user_id);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }

    @ParameterizedTest(name = "Corner : {index} test: user_id = {0} 参数异常or用户id不存在")
    @ValueSource(ints = {-1,0,10})
    void should_return_nothing_when_user_id_is_invalid(int user_id){
        List<ReserveAndUserAndGymAndField> resultList = reserveDao.queryReserveByUser(user_id);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }


}