package com.example.demo.dao;

import com.example.demo.vo.CourseAndGym;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
class TeachDaoTest {

    @Autowired
    TeachDao teachDao ;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public  void  setUp(){
        DataSupplier dataSupplier = new DataSupplier();
        dataSupplier.loadTeach(entityManager);
    }

    @AfterEach
    public  void cleanUp(){
        entityManager.clear();
    }

    @ParameterizedTest(name = "Happy :{index} test: course :course_id {0} is teached by teacher teacher_id {1}")
    @MethodSource("should_return_only_1_teacher_id_when_given_course_id_provider")
    void should_return_only_1_teacher_id_when_given_course_id(int course_id,int expected_teacher_id){
            assertEquals(expected_teacher_id,teachDao.findUserIdByCourseId(course_id));
    }
    static Stream<Arguments> should_return_only_1_teacher_id_when_given_course_id_provider(){
        return  Stream.of(
                Arguments.arguments(1,1),
                Arguments.arguments(2,2),
                Arguments.arguments(3,3),
                Arguments.arguments(4,2)
        );
    }

    @ParameterizedTest(name = "Corner :{index} test: course :course_id {0} 不存在，查询结果为空，抛出异常")
    @ValueSource(ints = {-1,0,5})
    void should_throw_exception_when_given_invalid_course_id_that_result_is_empty(int course_id) throws Exception{
        assertThrows(RuntimeException.class,()->teachDao.findUserIdByCourseId(course_id),"查询结果为empty set,无法转换为int,抛出运行异常");
    }


    @ParameterizedTest(name = "Happy :{index} test: course :course_id {0} is deleted successfuly : 受影响的纪录条数应为1")
    @ValueSource(ints ={1,2,3,4})
    void should_return_1_deleted_when_given_valid_course_id(int course_id){
        assertEquals(1,teachDao.deleteByCourseId(course_id));
    }


    @ParameterizedTest(name = "Corner :{index} test: course :course_id {0} 不存在，deleted failed : 受影响的纪录条数应为0")
    @ValueSource(ints ={-1,0,5})
    void should_return_0_when_given_invalid_course_id(int course_id) throws Exception{
        assertEquals(0,teachDao.deleteByCourseId(course_id));

    }

}