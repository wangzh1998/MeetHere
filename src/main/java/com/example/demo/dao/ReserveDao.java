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
            "from Reserve r join User u join Gym g join Field f " +
            "on u.userName = ?1 ")
    List<ReserveAndUserAndGymAndField> queryReserveInfoByUser(String name);
    //List<Reserve> findByUserName(String name);

    //List<Reserve> queryReserveByUser(int uid);
    @Query(value = "select new com.example.demo.vo.ReserveAndUserAndGymAndField" +
            "(r.reserveId,r.userId,u.userName,r.gymId,g.gymName,r.fieldId,f.fieldName,r.date,r.startTime,r.endTime) " +
            "from Reserve r join User u join Gym g join Field f " +
            "on u.id = ?1")
    List<ReserveAndUserAndGymAndField> queryReserveByUser(Integer uid);
}
