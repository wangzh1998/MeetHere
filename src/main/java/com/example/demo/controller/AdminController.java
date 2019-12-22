package com.example.demo.controller;

import com.example.demo.entity.Announce;
import com.example.demo.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    AnnounceService announceService;

    public AdminController() {
    }

    @RequestMapping(
            value = {"/add/announce"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=gbk"}
    )
    public String addAnnounce(@RequestParam(value = "time",required = true) String time,
                              @RequestParam(value = "content",required = true) String content) {
        Announce announce = new Announce();
        announce.setTime(time);
        announce.setContent(content);

        try {
            this.announceService.addAnnounce(announce);
            return "添加成功";
        } catch (Exception var5) {
            return var5.getMessage();
        }
    }
}
