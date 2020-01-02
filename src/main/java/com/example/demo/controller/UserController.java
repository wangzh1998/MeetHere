package com.example.demo.controller;

import com.example.demo.entity.Announce;
import com.example.demo.entity.Field;
import com.example.demo.entity.Reserve;
import com.example.demo.entity.User;
import com.example.demo.service.AnnounceService;
import com.example.demo.service.FieldService;
import com.example.demo.service.ReserveService;
import com.example.demo.service.UserService;
import com.example.demo.util.Util;
import java.util.List;

import com.example.demo.vo.GymAndField;
import com.example.demo.vo.ReserveAndUserAndGymAndField;
import com.example.demo.vo.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    ReserveService reserveService;
    @Autowired
    FieldService fieldService;
    @Autowired
    UserService userService;
    @Autowired
    AnnounceService announceService;

    public UserController() {
    }

    @RequestMapping(
            value = {"/add/reserve"},
            method = {RequestMethod.POST}
    )
    public String addReserve(@RequestParam(value = "gymname",required = false) String gymName,
                             @RequestParam(value = "gymid",required = true) int gymId,
                             @RequestParam(value = "fieldname",required = false) String fieldName,
                             @RequestParam(value = "fieldid",required = true) int fieldId,
                             @RequestParam(value = "date",required = true) String date,
                             @RequestParam(value = "starttime",required = true) String startTime,
                             @RequestParam(value = "endtime",required = true) String endTime) {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
       // Reserve reserve = new Reserve();
        ReserveAndUserAndGymAndField reserve = new ReserveAndUserAndGymAndField();
        reserve.setGymName(gymName);
        reserve.setGymId(gymId);
        reserve.setFieldName(fieldName);
        reserve.setFieldId(fieldId);
        reserve.setDate(date);
        reserve.setStartTime(startTime);
        reserve.setEndTime(endTime);
        reserve.setUserName(username);

        try {
            this.reserveService.addReserve(reserve);
            return "预约成功";
        } catch (Exception var12) {
            return var12.getMessage();
        }
    }

    @RequestMapping(
            value = {"/delete/reserve"},
            method = {RequestMethod.POST}
    )
    public String deleteReserve(@RequestParam(value = "reserveid",required = true) int reserve_id) {
        try {
            this.reserveService.deleteReserve(reserve_id);
            return "删除预约成功";
        } catch (Exception var3) {
            return var3.getMessage();
        }
    }

    @RequestMapping(
            value = {"/query/reserve"},
            method = {RequestMethod.GET}
    )
    public List<ReserveAndUserAndGymAndField> queryReserve() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<ReserveAndUserAndGymAndField> reservelist = this.reserveService.queryReserveByUser(username);
        return reservelist;
    }

    @RequestMapping(
            value = {"/query/announce"},
            method = {RequestMethod.GET}
    )
    public List<Announce> queryAnnounce() {
        List<Announce> announcelist = this.announceService.queryAnnounce();
        return announcelist;
    }

    @RequestMapping(
            value = {"/query/field"},
            method = {RequestMethod.GET}
    )
    public List<GymAndField> queryAvailableField(@RequestParam(value = "date",required = true) String date,
                                                 @RequestParam(value = "starttime",required = true) String starttime,
                                                 @RequestParam(value = "endtime",required = true) String endtime) {
        String weekday = Util.dateToWeek(date);
        return this.fieldService.queryAvailableField(date, weekday, starttime, endtime);
    }

    @RequestMapping(
            value = {"/query/user"},
            method = {RequestMethod.GET}
    )
    public UserAndRole queryUser() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return this.userService.queryUser(username);
    }
}
