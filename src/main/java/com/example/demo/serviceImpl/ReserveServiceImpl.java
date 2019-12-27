package com.example.demo.serviceImpl;

import com.example.demo.dao.ReserveDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Reserve;
import com.example.demo.entity.User;
import com.example.demo.service.ReserveService;
import java.util.List;

import com.example.demo.vo.ReserveAndUserAndGymAndField;
import com.example.demo.vo.UserAndRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private UserDao userDao;
    @Autowired
    ReserveDao reserveDao;



    public ReserveServiceImpl() {
    }

    public void addReserve(ReserveAndUserAndGymAndField reserveInfo) throws Exception {
        //User user = this.userDao.queryUserByUserName(reserveInfo.getUserName());
        UserAndRole user = this.userDao.findUserAndRoleByUserName(reserveInfo.getUserName());
        Reserve reserve = new Reserve();
        reserve.setUserId(user.getUserId());
        reserve.setGymId(reserveInfo.getGymId());
        reserve.setFieldId(reserveInfo.getFieldId());
        reserve.setDate(reserveInfo.getDate());
        reserve.setStartTime(reserveInfo.getStartTime());
        reserve.setEndTime(reserveInfo.getEndTime());
        Reserve t = this.reserveDao.save(reserve);
        if (t == null) {
            throw new Exception("数据库异常");
        }
    }

    public void deleteReserve(int reserve_id) throws Exception {
        this.reserveDao.deleteById(reserve_id);
    }

    public List<ReserveAndUserAndGymAndField> queryReserveByUser(String name) {
        //User user = this.userDao.queryUserByUserName(name);
        //User user = this.userDao.findUserByUserName(name);
        return this.reserveDao.queryReserveInfoByUser(name);
    }

    public List<ReserveAndUserAndGymAndField> queryReserveByUser(int uid) {
        return this.reserveDao.queryReserveByUser(uid);
    }
}
