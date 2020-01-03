package com.example.demo.dao;

import com.example.demo.entity.Course;
import com.example.demo.entity.Gym;
import com.example.demo.entity.Teach;
import com.example.demo.vo.CourseAndGym;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseDaoTest {
    @Autowired
    CourseDao courseDao;
    @Autowired
    private  EntityManager entityManager;

    @BeforeEach
    public  void  setUp(){
        DataSupplier dataSupplier = new DataSupplier();
        dataSupplier.loadGym(entityManager);
        dataSupplier.loadCourse(entityManager);
        dataSupplier.loadTeach(entityManager);
        dataSupplier.loadTake(entityManager);
        dataSupplier.loadAnnounce(entityManager);
        dataSupplier.loadRole(entityManager);
        dataSupplier.loadReserve(entityManager);
        dataSupplier.loadUser(entityManager);
        dataSupplier.loadField(entityManager);
    }

    @AfterEach
    public  void cleanUp(){
        entityManager.clear();
    }


    @ParameterizedTest(name="The [{index}] test : teacher_id is {0},course_name is {1},gym_name_is {2}")
    @MethodSource("should_return_courses_the_teacher_teach_provider")
    void should_return_only_course_the_teacher_teach(int teacher_id,
                                                 String expected_course_name,
                                                 String expected_course_gym_name) {
        List<CourseAndGym> resultList = courseDao.queryCourseByTeacher(teacher_id);
        assertAll(
                ()->assertNotNull(resultList,"查询结果不应为空"),
                ()->assertEquals(1,resultList.size(),"老师id为1或3时，查询结果应该只有一条"),
                ()->assertEquals(expected_course_name,resultList.get(0).getCourseName(),"查询出的课程名称应当与预期结果相当"),
                ()->assertEquals(expected_course_gym_name,resultList.get(0).getGymName(),"查询出的课程场馆应当与预期结果相当")
                );
    }
    static Stream<Arguments> should_return_courses_the_teacher_teach_provider(){
        return Stream.of(
                Arguments.arguments(1, "乒乓球","中北大学生活动中心羽毛球馆"),
                Arguments.arguments(3, "排球","中北体育馆羽毛球馆")
        );
    }

    //@DisplayName("This test : teacher_id is 2")
    @Test
    void should_return_2_courses_the_teacher_2_teach() {
        List<CourseAndGym> resultList = courseDao.queryCourseByTeacher(2);
        assertNotNull(resultList);
        assertEquals(2,resultList.size(),"查询出的课程应为2个");
        CourseAndGym course_1 = resultList.get(0);
        CourseAndGym course_2 = resultList.get(1);
        assertAll(
                ()->assertEquals("篮球",course_1.getCourseName(),"查询出的第一个课程应为篮球"),
                ()->assertEquals("排球",course_2.getCourseName(),"查询出的第二个课程应为排球"),
                ()->assertEquals("中北大学生活动中心羽毛球馆",course_1.getGymName(),"篮球场馆在大活"),
                ()->assertEquals("中北体育馆羽毛球馆",course_2.getGymName(),"排球场馆在体育馆")

        );
    }

    @ParameterizedTest(name="The [{index}] test : parameter is {0} not a teacher_id")
    @ValueSource(ints = {-1,0,4,8,9,10})
    void should_return_nothing_when_parameter_is_not_teacher_id(int teacher_id) {
        List<CourseAndGym> resultList = courseDao.queryCourseByTeacher(teacher_id);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }







    @ParameterizedTest(name="The [{index}] test : student_id is {0},expected_take_course_name is {1}")
    @MethodSource("should_return_course_the_student_take")
    void should_return_course_the_student_take(int student_id,String expected_take_course_name) {
        List<CourseAndGym> resultList = courseDao.queryCourseByStudent(student_id);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(1,resultList.size()),
                ()->assertEquals(expected_take_course_name,resultList.get(0).getCourseName())
        );
    }
    static Stream<Arguments> should_return_course_the_student_take(){
        return Stream.of(
                Arguments.arguments(5, "篮球"),
                Arguments.arguments(6, "排球")
                );
    }

    @Test
    void should_return_2__courses_the_student_4_take() {
        List<CourseAndGym> resultList = courseDao.queryCourseByStudent(4);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(2,resultList.size()),
                ()->assertEquals("乒乓球",resultList.get(0).getCourseName()),
                ()->assertEquals("篮球",resultList.get(1).getCourseName())

        );
    }

    void should_return_nothing_when_student_take_no_course(int student_id) {
        List<CourseAndGym> resultList = courseDao.queryCourseByStudent(7);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }

    @ParameterizedTest(name="The [{index}] test : student_id is {0},expected_take_course_name is {1}")
    @ValueSource(ints = {8,9,-1,0,1,3,10})
    void should_return_nothing_when_parameter_is_not_student_id(int student_id) {
        List<CourseAndGym> resultList = courseDao.queryCourseByStudent(student_id);
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(0,resultList.size())
        );
    }




    @Test
    void should_return_all_4_courses_when_query_all_course() {
        List<CourseAndGym> resultList = courseDao.queryAllCourse();
        assertAll(
                ()->assertNotNull(resultList),
                ()->assertEquals(4,resultList.size())
        );
        assertAll(
                ()->assertEquals(1,resultList.get(0).getCourseId()),
                ()->assertEquals("乒乓球",resultList.get(0).getCourseName()),
                ()->assertEquals(2,resultList.get(1).getCourseId()),
                ()->assertEquals("篮球",resultList.get(1).getCourseName()),
                ()->assertEquals(3,resultList.get(2).getCourseId()),
                ()->assertEquals("排球",resultList.get(2).getCourseName()),
                ()->assertEquals(4,resultList.get(3).getCourseId()),
                ()->assertEquals("排球",resultList.get(3).getCourseName())
                );
    }


    @Test
    void should_return_5_when_query_cur_id() {
        assertEquals(5,courseDao.queryCurId(),"当数据库中最大课程id为4时，当前课程id应为5");
    }
}