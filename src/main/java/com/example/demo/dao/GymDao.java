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

    @Query(value = "select *\nfrom gym\n" +
            "where gym_id not in " +
            "(\nselect gym_id\n" +
            "from course\n" +
            "where course.weekday = ?1 and course.endtime >= ?2 and course.starttime <= ?3\n" +
            "union\n" +
            "select gym_id\n" +
            "from reserve r\n" +
            "where r.endtime >= ?2 and r.starttime <= ?3 and r.weekday = ?1)" +
            "and start_time <= ?2 and end_time >= ?3",
    nativeQuery = true)
    List<Gym> queryAvailableGym(String weekday, String startTime, String endTime);
}