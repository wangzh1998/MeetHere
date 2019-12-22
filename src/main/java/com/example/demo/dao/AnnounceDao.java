package com.example.demo.dao;

import com.example.demo.entity.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnounceDao extends JpaRepository<Announce,Integer>,
        JpaSpecificationExecutor<Announce> {
    //int addAnnounce(Announce announce);

    //List<Announce> queryAnnounce();

}
