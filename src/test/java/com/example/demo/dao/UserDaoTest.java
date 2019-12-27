package com.example.demo.dao;

import com.example.demo.vo.UserAndRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext
class UserDaoTest {
    @Autowired
    UserDao userDao ;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public  void  setUp(){
        DataSupplier dataSupplier = new DataSupplier();
        dataSupplier.loadRole(entityManager);
        dataSupplier.loadUser(entityManager);
    }

    @AfterEach
    public  void cleanUp(){
        entityManager.clear();
    }

    @ParameterizedTest(name = "Happy :{index} test:user {0} has role_name {1}")
    @MethodSource("should_return_right_role_when_user_is_valid")
    void should_return_right_role_when_user_is_valid(String user_name,String role_name){
        UserAndRole userAndRole = userDao.findUserAndRoleByUserName(user_name);
        assertAll(
                ()->assertNotNull(userAndRole),
                ()->assertEquals(user_name,userAndRole.getUserName()),
                ()->assertEquals(role_name,userAndRole.getRoleName())
        );
    }
    static Stream<Arguments> should_return_right_role_when_user_is_valid(){
        return  Stream.of(
                Arguments.arguments("钟晖", "ROLE_TEACHER"),
                Arguments.arguments("潘国平", "ROLE_TEACHER"),
                Arguments.arguments("阎健","ROLE_TEACHER"),
                Arguments.arguments("张诗晨","ROLE_STUDENT"),
                Arguments.arguments("王昭辉","ROLE_STUDENT"),
                Arguments.arguments("林梦思", "ROLE_STUDENT"),
                Arguments.arguments("李雪莹","ROLE_STUDENT"),
                Arguments.arguments("张若昀", "ROLE_ADMIN"),
                Arguments.arguments("唐艺昕","ROLE_TOURIST")
        );
    }

    @ParameterizedTest(name = "Corner :{index} test:user {0} 无效用户或参数异常 has no role")
    @ValueSource(strings = {"李沁","林梦",""," "})
    @NotNull
    void should_return_nothing_when_user_is_invalid(String user_name){
        UserAndRole userAndRole = userDao.findUserAndRoleByUserName(user_name);
        assertNull(userAndRole);
    }

}