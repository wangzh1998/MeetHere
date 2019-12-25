package com.example.demo.serviceImpl;

        import com.example.demo.dao.AnnounceDao;
        import com.example.demo.entity.Announce;
        import com.example.demo.service.AnnounceService;
        import java.util.List;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    AnnounceDao announceDao;

    public AnnounceServiceImpl() {
    }

    public void addAnnounce(Announce announce) throws Exception {
        /*int t = this.announceDao.addAnnounce(announce);
        if (t <= 0) {
            throw new Exception("数据库异常");
        }*/
        if (announce == null) {
            throw new Exception("参数异常");
        }
        announceDao.save(announce);
    }

    public List<Announce> queryAnnounce() {
        /*return this.announceDao.queryAnnounce();*/
        return this.announceDao.findAll();
    }
}
