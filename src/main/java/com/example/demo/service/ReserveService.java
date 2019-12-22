package com.example.demo.service;

import com.example.demo.entity.Reserve;
import com.example.demo.vo.ReserveAndUserAndGymAndField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReserveService  {
    void addReserve(ReserveAndUserAndGymAndField reserveInfo) throws Exception;

    void deleteReserve(int reserve_id) throws Exception;

    List<ReserveAndUserAndGymAndField> queryReserveByUser(String name);

    List<ReserveAndUserAndGymAndField> queryReserveByUser(int uid);
}
