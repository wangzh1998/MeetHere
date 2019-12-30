package com.example.demo.serviceImpl;

import org.junit.jupiter.api.Test;
import com.example.demo.dao.UserDao;
import com.example.demo.vo.UserAndRole;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerUserServiceTest {

    @Mock
    UserDao userDao;
    @InjectMocks
    CustomerUserService customerUserService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername_found_test() {
        UserAndRole user = new UserAndRole(1, "Amy", "123456",
                "Wu", 3, "ROLE_TEACHER");
        when(userDao.findUserAndRoleByUserName(any())).thenReturn(user);

        UserDetails result = customerUserService.loadUserByUsername(anyString());
        verify(userDao,times(1)).findUserAndRoleByUserName(any());

        assertAll(
                ()->assertEquals("Amy",result.getUsername()),
                ()->assertEquals("123456",result.getPassword())
        );
    }
/**
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    //此测试方法报错
    @Test
    void loadUserByUsername_not_found_test1() throws UsernameNotFoundException{
        when(userDao.findUserAndRoleByUserName(any())).thenReturn(null);
        thrown.expect(UsernameNotFoundException.class);
        thrown.expectMessage("username not exist");
        UserDetails result = customerUserService.loadUserByUsername(anyString());
        verify(userDao,times(1)).findUserAndRoleByUserName(any());
    }
*/
    @Test
    void loadUserByUsername_not_found_test2() {
        when(userDao.findUserAndRoleByUserName(any())).thenReturn(null);
        try {
            UserDetails result = customerUserService.loadUserByUsername(anyString());
            fail("expected Exception for username not exist");
        } catch (UsernameNotFoundException ex) {
            assertThat(ex.getMessage(),containsString("username not exist"));
        }
        verify(userDao,times(1)).findUserAndRoleByUserName(any());
    }


}