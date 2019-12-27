package com.example.demo.dao;

import com.example.demo.entity.Course;
import com.example.demo.vo.CourseAndGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseDao extends JpaRepository<Course,Integer> {
    //int addCourse(Course course);

    //List<Course> queryCourseByTeacher(int userId);
    //修改为CourseAndGym

   /* @Query(value = "select new com.example.demo.dao.vo.CourseAndGym" +
            "(c.id,c.name,c.weekday,c.startTime,c.endTime,) " +
            "from Teach t join Course c join Gym g " +
            "on t.user_id = ?1")*/
    //List<CourseAndGym> queryCourseByTeacher(int userId);

    @Query(value = "select new com.example.demo.vo.CourseAndGym" +
            "(c.courseId,c.courseName,c.weekday,c.startTime,c.endTime,g.gymId,g.gymName,c.teacherName) " +
            "from Teach t join Course c " +
            "on t.userId = ?1 and t.courseId = c.courseId " +
            "join Gym g " +
            " on c.gymId = g.gymId")
    List<CourseAndGym> queryCourseByTeacher(int userId);


    //同上
    @Query(value = "select new com.example.demo.vo.CourseAndGym" +
            "(c.courseId,c.courseName,c.weekday,c.startTime,c.endTime,g.gymId,g.gymName,c.teacherName) " +
            "from Take t join Course c  " +
            "on t.userId = ?1 and t.courseId = c.courseId " +
            "join Gym g " +
            "on c.gymId = g.gymId ")
    List<CourseAndGym> queryCourseByStudent(int userId);


    //int deleteCourseByTeacher(int course_id);

    //同上
    @Query(value = "select new com.example.demo.vo.CourseAndGym" +
            "(c.courseId,c.courseName,c.weekday,c.startTime,c.endTime,c.gymId,g.gymName,c.teacherName) " +
            "from Course c join Gym g " +
            "on c.gymId = g.gymId ")
    List<CourseAndGym> queryAllCourse();

    @Query(value = "select max(course_id)+1 as course_id from course",nativeQuery = true)
    int queryCurId();
}
