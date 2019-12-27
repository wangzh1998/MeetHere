package com.example.demo.dao;

import com.example.demo.vo.CourseAndGym;
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
class FieldDaoTest {
    @Autowired
    FieldDao fieldDao ;
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

    @ParameterizedTest(name = "Happy {index} test: gym_id = {0},field_id = {1},gym_name = {2},field_name = {3}")
    @MethodSource("should_return_one_record_when_find_by_useful_gym_id_and_field_id_provider")
    void should_return_one_record_when_find_by_useful_gym_id_and_field_id(int gym_id,int field_id,
                                                                          String expected_gym_name,String expected_field_name){

        GymAndField gymAndField = fieldDao.queryFieldById(gym_id,field_id);
        assertNotNull(gymAndField);
        assertAll(
                ()->assertEquals(expected_gym_name,gymAndField.getGymName()),
                ()->assertEquals(expected_field_name,gymAndField.getFieldName())
                );

    }
    static Stream<Arguments> should_return_one_record_when_find_by_useful_gym_id_and_field_id_provider(){
        return Stream.of(
                Arguments.arguments(1,1 ,"中北大学生活动中心羽毛球馆","1号场地"),
                Arguments.arguments(1,2 ,"中北大学生活动中心羽毛球馆","2号场地"),
                Arguments.arguments(1,5 ,"中北大学生活动中心羽毛球馆","5号场地"),
                Arguments.arguments(1,7 ,"中北大学生活动中心羽毛球馆","7号场地"),
                Arguments.arguments(1,8 ,"中北大学生活动中心羽毛球馆","8号场地"),
                Arguments.arguments(2,1 ,"中北体育馆羽毛球馆","1号场地"),
                Arguments.arguments(2,2 ,"中北体育馆羽毛球馆","2号场地"),
                Arguments.arguments(2,6 ,"中北体育馆羽毛球馆","6号场地"),
                Arguments.arguments(2,11,"中北体育馆羽毛球馆","11号场地"),
                Arguments.arguments(2,12,"中北体育馆羽毛球馆","12号场地")
        );
    }

    @ParameterizedTest(name = "Corner {index} test: gym_id = {0} is invalid or field_id = {1} is invalid,result should be null")
    @MethodSource("should_return_0_record_when_someone_parameter_is_not_exist_provider")
    void should_return_0_record_when_someone_parameter_is_not_exist(int gym_id,int field_id){
        assertNull(fieldDao.queryFieldById(gym_id,field_id));
    }
    static Stream<Arguments> should_return_0_record_when_someone_parameter_is_not_exist_provider(){
        return Stream.of(
                Arguments.arguments(-1,1 ),
                Arguments.arguments(0,1 ),
                Arguments.arguments(1,-1 ),
                Arguments.arguments(1,0 ),
                Arguments.arguments(1,9 ),
                Arguments.arguments(1,10 ),
                Arguments.arguments(2,-1),
                Arguments.arguments(2,0 ),
                Arguments.arguments(2,13 )
        );
    }


    @ParameterizedTest(name = "Happy {index} test: 查找可用场馆数：此处只检测结果数目，不进行具体比对")
    @MethodSource("should_return_available_fields_num_provider")
    void should_return_available_fields(String date,String weekday,
                                        String start_time,String end_time,
                                        int expected_available_field_num){
        List<GymAndField> resultList = fieldDao.queryAvailableField(date,weekday,start_time,end_time);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(expected_available_field_num,resultList.size())
        );

    }
    static Stream<Arguments> should_return_available_fields_num_provider(){
        return Stream.of(
                Arguments.arguments("2019-12-23","周一","08:00","10:00",19),
                Arguments.arguments("2019-12-24","周二","10:00","11:00",19),
                Arguments.arguments("2019-12-25","周三","08:00","10:00",20),
                Arguments.arguments("2019-12-26","周四","08:00","10:00",20),
                Arguments.arguments("2019-12-27","周五","08:00","10:00",20),
                Arguments.arguments("2019-12-28","周六","08:00","10:00",20),
                Arguments.arguments("2019-12-29","周日","08:00","10:00",20)
        );
    }

    @DisplayName("选择一条查询\"2019-12-23\",\"周一\",\"08:00\",\"10:00\",19，逐条比对查询出来的可用场馆信息")
    @Test
    void should_return_available_fields(){
        List<GymAndField> resultList = fieldDao.queryAvailableField("2019-12-23","周一","08:00","10:00");
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(19,resultList.size())
        );
        assertAll(
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(0).getGymName()),
                ()->assertEquals("1号场地",resultList.get(0).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(1).getGymName()),
                ()->assertEquals("2号场地",resultList.get(1).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(2).getGymName()),
                ()->assertEquals("3号场地",resultList.get(2).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(3).getGymName()),
                ()->assertEquals("4号场地",resultList.get(3).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(4).getGymName()),
                ()->assertEquals("5号场地",resultList.get(4).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(5).getGymName()),
                ()->assertEquals("6号场地",resultList.get(5).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(6).getGymName()),
                ()->assertEquals("7号场地",resultList.get(6).getFieldName()),
                ()->assertEquals("中北大学生活动中心羽毛球馆",resultList.get(7).getGymName()),
                ()->assertEquals("8号场地",resultList.get(7).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(8).getGymName()),
                ()->assertEquals("2号场地",resultList.get(8).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(9).getGymName()),
                ()->assertEquals("3号场地",resultList.get(9).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(10).getGymName()),
                ()->assertEquals("4号场地",resultList.get(10).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(11).getGymName()),
                ()->assertEquals("5号场地",resultList.get(11).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(12).getGymName()),
                ()->assertEquals("6号场地",resultList.get(12).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(13).getGymName()),
                ()->assertEquals("7号场地",resultList.get(13).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(14).getGymName()),
                ()->assertEquals("8号场地",resultList.get(14).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(15).getGymName()),
                ()->assertEquals("9号场地",resultList.get(15).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(16).getGymName()),
                ()->assertEquals("10号场地",resultList.get(16).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(17).getGymName()),
                ()->assertEquals("11号场地",resultList.get(17).getFieldName()),
                ()->assertEquals("中北体育馆羽毛球馆",resultList.get(18).getGymName()),
                ()->assertEquals("12号场地",resultList.get(18).getFieldName())
        );

    }

    @DisplayName("Corner case : no available field when both gyms be used at the given time")
    void should_return_nothing_when_no_available_field(){
        List<GymAndField> resultList = fieldDao.queryAvailableField("2019-12-24","周二","08:00","10:00");
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }



}