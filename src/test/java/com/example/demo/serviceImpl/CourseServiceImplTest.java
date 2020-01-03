package com.example.demo.serviceImpl;

import com.example.demo.dao.CourseDao;
import com.example.demo.dao.TakeDao;
import com.example.demo.dao.TeachDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Course;
import com.example.demo.vo.CourseAndGym;
import com.example.demo.vo.ReserveAndUserAndGymAndField;
import com.example.demo.vo.UserAndRole;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class CourseServiceImplTest {

    @Mock
    CourseDao courseDao;
    @Mock
    TakeDao takeDao;
    @Mock
    UserDao userDao;
    @Mock
    TeachDao teachDao;
    @InjectMocks
    CourseServiceImpl courseService;

    private Course course= new Course();

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void init() {
        course.setCourseId(1);
        course.setCourseName("羽毛球");
        course.setStartTime("10:00");
        course.setEndTime("11:40");
        course.setWeekday("Thursday");
        course.setGymId(1);
        course.setTeacherName("钟晖");
    }

    //路径1
    @Test
    void addCourse_user_null_test() {
        when(userDao.findUserAndRoleByUserName(course.getTeacherName())).thenReturn(null);
        try {
            courseService.addCourse(course);
            fail("expected Exception for 用户不存在");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("用户不存在"));
        }
    }

    //路径2
    @Test
    void addCourse_user_not_null_cruid_error_test() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName(course.getTeacherName())).thenReturn(user);
        when(courseDao.queryCurId()).thenReturn(-1);
        try {
            courseService.addCourse(course);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }

    //路径3
    @Test
    void addCourse_user_not_null_cruid_right_save_error_test() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName(course.getTeacherName())).thenReturn(user);
        when(courseDao.queryCurId()).thenReturn(1);
        when(courseDao.save(course)).thenReturn(null);
        try {
            courseService.addCourse(course);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }

    //路径4
    @Test
    void addCourse_user_not_null_cruid_right_save_right_save_error_test() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName(course.getTeacherName())).thenReturn(user);
        when(courseDao.queryCurId()).thenReturn(1);
        when(courseDao.save(course)).thenReturn(course);
        when(teachDao.save(any())).thenReturn(null);
        try {
            courseService.addCourse(course);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }


    @Test
    void queryCourseByTeacher() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName("钟晖")).thenReturn(user);
        List<CourseAndGym> courseAndGymList = new ArrayList<>();
        when(courseDao.queryCourseByTeacher(user.getUserId())).thenReturn(courseAndGymList);
        List<CourseAndGym> result = courseService.queryCourseByTeacher("钟晖");
        verify(userDao,times(1)).findUserAndRoleByUserName("钟晖");
        verify(courseDao,times(1)).queryCourseByTeacher(user.getUserId());
    }

    @Test
    void queryCorseByStudent() {
        UserAndRole user = new UserAndRole();
        user.setName("张诗晨");
        user.setPassword("123456");
        user.setUserName("张诗晨");
        user.setRoleName("ROLE_STUDENT");
        user.setRoleId(3);
        user.setUserId(5);
        when(userDao.findUserAndRoleByUserName("张诗晨")).thenReturn(user);
        List<CourseAndGym> courseAndGymList = new ArrayList<>();
        when(courseDao.queryCourseByStudent(user.getUserId())).thenReturn(courseAndGymList);
        List<CourseAndGym> result = courseService.queryCourseByStudent("张诗晨");
        verify(userDao,times(1)).findUserAndRoleByUserName("张诗晨");
        verify(courseDao,times(1)).queryCourseByStudent(user.getUserId());
    }

    //路径1
    @Test
    void deleteCourseByTeacher_user_null_test() {
        when(userDao.findUserAndRoleByUserName(anyString())).thenReturn(null);
        try {
            courseService.deleteCourseByTeacher("钟晖",2);
            fail("expected Exception for 用户不存在");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("用户不存在"));
        }
    }

    //路径2
    @Test
    void deleteCourseByTeacher_id_not_equal() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName("钟晖")).thenReturn(user);
        when(teachDao.findUserIdByCourseId(2)).thenReturn(2);
        try {
            courseService.deleteCourseByTeacher("钟晖",2);
            fail("expected Exception for 没有删除权限");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("没有删除权限"));
        }
    }

    //路径3
    @Test
    void deleteCourseByTeacher_id_equal_take_error() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName("钟晖")).thenReturn(user);
        when(teachDao.findUserIdByCourseId(2)).thenReturn(1);
        when(takeDao.deleteByCourseId(2)).thenReturn(-1);
        try {
            courseService.deleteCourseByTeacher("钟晖",2);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }

    //路径4
    @Test
    void deleteCourseByTeacher_id_equal_take_right_teach_error() {
        UserAndRole user = new UserAndRole();
        user.setName("钟晖");
        user.setPassword("123456");
        user.setUserName("钟晖");
        user.setRoleName("ROLE_TEACHER");
        user.setRoleId(2);
        user.setUserId(1);
        when(userDao.findUserAndRoleByUserName("钟晖")).thenReturn(user);
        when(teachDao.findUserIdByCourseId(2)).thenReturn(1);
        when(takeDao.deleteByCourseId(2)).thenReturn(0);
        when(teachDao.deleteByCourseId(2)).thenReturn(0);
        try {
            courseService.deleteCourseByTeacher("钟晖",2);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }

    @Test
    void deleteCourseByStudent_user_not_null_t_error() {
        UserAndRole user = new UserAndRole();
        user.setName("张诗晨");
        user.setPassword("123456");
        user.setUserName("张诗晨");
        user.setRoleName("ROLE_STUDENT");
        user.setRoleId(3);
        user.setUserId(5);
        when(userDao.findUserAndRoleByUserName("张诗晨")).thenReturn(user);
        when(takeDao.deleteByUserIdAndCourseId(user.getUserId(), 1)).thenReturn(-1);
        try {
            courseService.deleteCourseByStudent("张诗晨",1);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }

    @Test
    void queryAllCourse() {
        List<CourseAndGym> courseAndGymList = new ArrayList<>();
        when(courseDao.queryAllCourse()).thenReturn(courseAndGymList);
        List<CourseAndGym> result = courseService.queryAllCourse();
        verify(courseDao,times(1)).queryAllCourse();
    }

    @Test
    void takeCourse_user_not_null_t_error() {
        UserAndRole user = new UserAndRole();
        user.setName("张诗晨");
        user.setPassword("123456");
        user.setUserName("张诗晨");
        user.setRoleName("ROLE_STUDENT");
        user.setRoleId(3);
        user.setUserId(5);
        when(userDao.findUserAndRoleByUserName("张诗晨")).thenReturn(user);
        when(takeDao.save(any())).thenReturn(null);
        try {
            courseService.takeCourse("张诗晨",1);
            fail("expected Exception for 数据库异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("数据库异常"));
        }
    }
}