package com.example.demo.service;

import com.example.demo.entity.Announce;
import java.util.List;

public interface AnnounceService {
    void addAnnounce(Announce announce) throws Exception;

    List<Announce> queryAnnounce();
}
