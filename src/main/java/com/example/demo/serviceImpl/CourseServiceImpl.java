package com.example.demo.serviceImpl;

import com.example.demo.dao.CourseDao;
import com.example.demo.dao.TakeDao;
import com.example.demo.dao.TeachDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Course;
import com.example.demo.entity.Take;
import com.example.demo.entity.Teach;
import com.example.demo.entity.User;
import com.example.demo.service.CourseService;
import java.util.List;

import com.example.demo.vo.CourseAndGym;
import com.example.demo.vo.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    TakeDao takeDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TeachDao teachDao;

    public CourseServiceImpl() {
    }

    @Transactional
    public void addCourse(Course course) throws Exception {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(course.getTeacherName());
        if (user == null) {
            throw new Exception("用户不存在");
        } else {
            int curid = this.courseDao.queryCurId();
            if (curid == -1) {
                throw new Exception("数据库异常");
            } else {
                course.setCourseId(curid);
                Course c = this.courseDao.save(course);
                if (c == null) {
                    throw new Exception("数据库异常");
                } else {
                    Teach t = new Teach(user.getUserId(), course.getCourseId());
                    t = this.teachDao.save(t);
                    if (t == null) {
                        throw new Exception("数据库异常");
                    }
                }
            }
        }
    }

    @Transactional
    public List<CourseAndGym> queryCourseByTeacher(String name) {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(name);
        return this.courseDao.queryCourseByTeacher(user.getUserId());
    }

    @Transactional
    public List<CourseAndGym> queryCourseByStudent(String name) {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(name);
        return this.courseDao.queryCourseByStudent(user.getUserId());
    }

    /*
    这里要删除course teach 以及所有相关Take
     */
    @Transactional
    public void deleteCourseByTeacher(String username, int course_id) throws Exception {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(username);
        if (user == null) {
            throw new Exception("用户不存在");
        } else {
            int userId = this.teachDao.findUserIDByCourseId(course_id);
            if (userId != user.getUserId()) {
                throw new Exception("没有删除权限");
            } else {
                //int t = this.takesDao.deleteTakesByTeacher(course_id);
                int t = this.takeDao.deleteByCourseId(course_id);
                if (t <= 0) {
                    throw new Exception("数据库异常");
                } else {
                    t = this.teachDao.deleteByCourseId(course_id);
                    if (t <= 0) {
                        throw new Exception("数据库异常");
                    } else {
                        this.courseDao.deleteById(course_id);
                    }
                }
            }
        }
    }

    public void deleteCourseByStudent(String name, int course_id) throws Exception {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(name);
        if (user == null) {
            throw new Exception("用户不存在");
        } else {
            int t = this.takeDao.deleteByUserIdAndCourseId(user.getUserId(), course_id);
            if (t <= 0) {
                throw new Exception("数据库异常");
            }
        }
    }


    public List<CourseAndGym> queryAllCourse() {
        return this.courseDao.queryAllCourse();
    }

    public void takeCourse(String name, int course_id) throws Exception {
        UserAndRole user = this.userDao.findUserAndRoleByUserName(name);
        if (user == null) {
            throw new Exception("用户不存在");
        } else {
            Take t = new Take(user.getUserId(),course_id);
            t=  this.takeDao.save(t);
            if (t == null) {
                throw new Exception("数据库异常");
            }
        }
    }
}
