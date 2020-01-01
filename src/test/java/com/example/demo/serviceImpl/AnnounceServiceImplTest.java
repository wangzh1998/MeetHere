package com.example.demo.serviceImpl;

import com.example.demo.dao.AnnounceDao;
import com.example.demo.entity.Announce;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@RunWith(SpringRunner.class)
class AnnounceServiceImplTest {

    @Mock
    private AnnounceDao announceDao;
    @InjectMocks
    private AnnounceServiceImpl announceService;

    @Rule
    public ExpectedException thrown= ExpectedException.none();


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addAnnounce_not_null_test() throws Exception {
        Announce a1 = new Announce("2019-12-23 8:00","announce_1");
        announceService.addAnnounce(a1);
        verify(announceDao,times(1)).save(a1);
    }

    //此测试方法报错
    /**
    @Test
    void addAnnounce_throws_Exception_for_null1() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("参数异常");
        announceService.addAnnounce(null);
    }
    */

    @Test
    void addAnnounce_throws_Exception_for_null2() throws Exception {
        try {
            announceService.addAnnounce(null);
            fail("expected Exception for 参数异常");
        } catch (Exception ex) {
            assertThat(ex.getMessage(),containsString("参数异常"));
        }
    }

    @Test
    void queryAnnounce() {
        List<Announce> announceList = new ArrayList<>();
        Announce a1 = new Announce("2019-12-23 8:00","announce_1");
        Announce a2 = new Announce("2019-12-23 10:00","announce_2");
        announceList.add(a1);
        announceList.add(a2);
        when(announceDao.findAll()).thenReturn(announceList);

        List<Announce> resultList = announceService.queryAnnounce();
        verify(announceDao,times(1)).findAll();

        assertAll(
                ()->assertEquals(2,resultList.size(),"全部announce的数量应为2"),
                ()->assertEquals("2019-12-23 8:00",resultList.get(0).getTime()),
                ()->assertEquals("announce_1",resultList.get(0).getContent())
        );
    }
}
