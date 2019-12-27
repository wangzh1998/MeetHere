package com.example.demo.dao;

import com.example.demo.entity.Reserve;
import com.example.demo.vo.ReserveAndUserAndGymAndField;
import org.hibernate.annotations.Subselect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReserveDao extends JpaRepository<Reserve,Integer> {
    //int addReserve(Reserve reserve);

    //int deleteReserve(int reserve_id);

    @Query(value = "select new com.example.demo.vo.ReserveAndUserAndGymAndField" +
            "(r.reserveId,r.userId,u.userName,r.gymId,g.gymName,r.fieldId,f.fieldName,r.date,r.startTime,r.endTime) " +
            "from Reserve r join User u  " +
            "on u.userName = ?1 and r.userId = u.userId " +
            "join Gym g " +
            "on r.gymId = g.gymId " +
            "join Field f " +
            "on g.gymId = f.gymId and r.fieldId = f.fieldId ")
    List<ReserveAndUserAndGymAndField> queryReserveInfoByUser(String name);
    //List<Reserve> findByUserName(String name);


    @Query(value = "select new com.example.demo.vo.ReserveAndUserAndGymAndField" +
            "(r.reserveId,r.userId,u.userName,r.gymId,g.gymName,r.fieldId,f.fieldName,r.date,r.startTime,r.endTime) " +
            "from Reserve r join User u  " +
            "on u.userId = ?1 and r.userId = u.userId " +
            "join Gym g " +
            "on r.gymId = g.gymId " +
            "join Field f " +
            "on g.gymId = f.gymId and r.fieldId = f.fieldId ")
    List<ReserveAndUserAndGymAndField> queryReserveByUser(Integer uid);
    //List<Reserve> queryReserveByUser(int uid);
}
