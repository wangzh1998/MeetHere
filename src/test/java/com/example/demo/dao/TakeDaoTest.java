package com.example.demo.dao;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
class TakeDaoTest {
    @Autowired
    TakeDao takeDao ;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public  void  setUp(){
        DataSupplier dataSupplier = new DataSupplier();
        dataSupplier.loadTake(entityManager);
    }

    @AfterEach
    public  void cleanUp(){
        entityManager.clear();
    }

    @ParameterizedTest(name = "The {index} test: course_id = {0},deleted_relative_take_num:{1}")
    @MethodSource("should_delete_all_relative_take_when_delete_by_course_id_provider")
    void should_delete_all_relative_take_when_delete_by_useful_course_id(int course_id,int expected_deleted_num) {
        assertEquals(expected_deleted_num,takeDao.deleteByCourseId(course_id));

    }
    static Stream<Arguments> should_delete_all_relative_take_when_delete_by_course_id_provider(){
        return  Stream.of(
                Arguments.arguments(1,1),
                Arguments.arguments(2,2),
                Arguments.arguments(3,1)
        );
    }

    void should_delete_0_relative_take_when_delete_by_course_id_that_has_not_take(int course_id) {
        assertEquals(0,takeDao.deleteByCourseId(4));

    }

    @ParameterizedTest(name = "The {index} test: invalid paramer :  {0} is not course_id}")
    @ValueSource(ints = {-1,0,5,6})
    void should_delete_0_relative_take_when_parameter_is_not_course_id(int course_id) {
        assertEquals(0,takeDao.deleteByCourseId(course_id));
    }





    @ParameterizedTest(name = "The {index} test: student_id = {0} and course_id = {1},deleted_relative_take_num:{1}")
    @MethodSource("should_delete_one_when_delete_by_useful_user_id_and_course_id_provider")
    void should_delete_one_when_delete_by_useful_user_id_and_course_id(int student_id,int course_id) {
        assertEquals(1,takeDao.deleteByUserIdAndCourseId(student_id,course_id));
    }
    static Stream<Arguments> should_delete_one_when_delete_by_useful_user_id_and_course_id_provider(){
        return  Stream.of(
                Arguments.arguments(4,1),
                Arguments.arguments(4,2),
                Arguments.arguments(5,2),
                Arguments.arguments(6,3)
        );
    }

    @ParameterizedTest(name = "The {index} test: parameter : {0} is teacher_id not student_id")
    @MethodSource("should_delete_0_when_first_parameter_is_teacher_id_provider")
    void should_delete_0_when_first_parameter_is_teacher_id(int student_id,int course_id){
        assertEquals(0,takeDao.deleteByUserIdAndCourseId(student_id,course_id));
    }

    static Stream<Arguments> should_delete_0_when_first_parameter_is_teacher_id_provider(){
        return Stream.of(
                Arguments.arguments(1,1),
                Arguments.arguments(2,2),
                Arguments.arguments(3,3),
                Arguments.arguments(2,4)
        );
    }

    @ParameterizedTest(name = "The {index} test: student id {0} has not take course {1}")
    @MethodSource("should_delete_0_when_student_has_not_take_this_course_provider")
    void should_delete_0_when_student_has_not_take_this_course(int student_id,int course_id) {
        assertEquals(0,takeDao.deleteByUserIdAndCourseId(student_id,course_id));
    }

    static Stream<Arguments> should_delete_0_when_student_has_not_take_this_course_provider(){
        return Stream.of(
                Arguments.arguments(4,3),
                Arguments.arguments(4,4),
                Arguments.arguments(5,1)
        );
    }

    @ParameterizedTest(name = "The {index} test:  id {0} is not student  or course {1} not exist")
    @MethodSource("should_delete_0_when_first_parameter_is_not_student_or_second_parameter_is_not_course_provider")
    void should_delete_0_when_first_parameter_is_not_student_or_second_parameter_is_not_course(int student_id,int course_id) {
        assertEquals(0,takeDao.deleteByUserIdAndCourseId(student_id,course_id));
    }

    static Stream<Arguments> should_delete_0_when_first_parameter_is_not_student_or_second_parameter_is_not_course_provider(){
        return Stream.of(
                Arguments.arguments(4,5),
                Arguments.arguments(7,1),
                Arguments.arguments(8,1)
        );
    }


}