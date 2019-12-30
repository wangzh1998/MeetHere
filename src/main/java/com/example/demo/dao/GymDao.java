package com.example.demo.dao;

import com.example.demo.entity.Gym;
import org.hibernate.annotations.Subselect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
这个查询可用场馆好像有点问题？应该再加一个date参数给reserve表用？
 */
@Repository
public interface GymDao extends JpaRepository<Gym,Integer> {
    //Gym queryGymById(int gid);

    /*@Query(value = "select gym.gym_id as gymId,gym.gym_name as gymName,gym.start_time as startTime,gym.end_time as endTime\n" +
            "from gym\n" +
            "where gym_id \n" +
            "not in \n" +
            "(select gym_id \n" +
            "from course\n" +
            "where course.weekday = :weekday and course.end_time > :startTime and course.start_time < :endTime\n" +
            "union\n" +
            "select gym_id\n" +
            "from reserve r\n" +
            "where r.weekday = :weekday and r.end_time > :startTime and r.start_time < endTime)",
    nativeQuery = true)*/
    /*
    可用场馆的查询只排除被课程占据的，如果是部分场地被预约，则不考虑
    * */
  /*  @Query(value = "select gym.gym_id as gymId,gym.gym_name as gymName,gym.start_time as startTime,gym.end_time as endTime\n" +
            "from gym\n" +
            "where start_time <= :startTime and end_time >= :endTime " +
            "and  gym_id \n" +
            "not in \n" +
            "(select gym_id \n" +
            "from course\n" +
            "where course.weekday = :weekday and course.end_time > :startTime and course.start_time < :endTime)\n",
            nativeQuery = true)*/
  //删除别名
    @Query(value = "select gym.gym_id,gym.gym_name,gym.start_time,gym.end_time\n" +
            "from gym\n" +
            "where start_time <= :startTime and end_time >= :endTime " +
            "and  gym_id \n" +
            "not in \n" +
            "(select gym_id \n" +
            "from course\n" +
            "where course.weekday = :weekday and course.end_time > :startTime and course.start_time < :endTime)\n",
            nativeQuery = true)
    List<Gym> queryAvailableGym(String weekday, String startTime, String endTime);
}