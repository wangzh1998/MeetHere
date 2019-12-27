package com.example.demo.dao;

import com.example.demo.entity.*;

import javax.persistence.EntityManager;

/*
entityManager怎么知道对应的是什么表呢？
 */
public class DataSupplier {
    public  void loadRole(EntityManager entityManager) {
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_student = new Role("ROLE_STUDENT");
        Role role_teacher = new Role("ROLE_TEACHER");
        Role role_tourist = new Role("ROLE_TOURIST");
                role_admin.setRoleId(1);
                role_student.setRoleId(2);
                role_teacher.setRoleId(3);
                role_tourist.setRoleId(4);
        entityManager.persist(role_admin);
        entityManager.persist(role_student);
        entityManager.persist(role_teacher);
        entityManager.persist(role_tourist);
    }

    public  void loadUser(EntityManager entityManager){
        User user_1 = new User("钟晖","123456","Mike",3);
        User user_2 = new User("阎健","456789","Jack",3);
        User user_3 = new User("潘国平","123789","Tom",3);
        User user_4 = new User("张诗晨","147258","Amy",2);
        User user_5 = new User("王昭辉","369258","Lily",2);
        User user_6 = new User("林梦思","147369","Maria",2);
        User user_7 = new User("李雪莹","741852","Sarah",2);
        User user_8 = new User("张若昀","123456","Allen",1);
        User user_9 = new User("唐艺昕","123456","Juicy",4);
         user_1.setUserId(1);
         user_2.setUserId(2);
         user_3.setUserId(3);
         user_4.setUserId(4);
         user_5.setUserId(5);
         user_6.setUserId(6);
         user_7.setUserId(7);
         user_8.setUserId(8);
         user_9.setUserId(9);
        entityManager.persist(user_1);
        entityManager.persist(user_2);
        entityManager.persist(user_3);
        entityManager.persist(user_4);
        entityManager.persist(user_5);
        entityManager.persist(user_6);
        entityManager.persist(user_7);
        entityManager.persist(user_8);
        entityManager.persist(user_9);
    }

    public  void loadGym(EntityManager entityManager) {
        Gym gym_1 = new Gym("中北大学生活动中心羽毛球馆", "08:00", "22:00");
        Gym gym_2 = new Gym("中北体育馆羽毛球馆", "07:00", "22:00");
            gym_1.setGymId(1);
            gym_2.setGymId(2);
        entityManager.persist(gym_1);
        entityManager.persist(gym_2);
    }

    public void loadField(EntityManager entityManager) {
        Field one_fields[] = new Field[8];
        Field two_fields[] = new Field[12];
        for (int j = 0; j < 8; j++) {
            one_fields[j] = new Field();
            one_fields[j].setGymId(1);
            one_fields[j].setFieldId(j+1);
            one_fields[j].setFieldName("" + (j + 1) + "号场地");
            entityManager.persist(one_fields[j]);
        }
        for (int j = 0; j < 12; j++) {
            two_fields[j] = new Field();
            two_fields[j].setGymId(2);
            two_fields[j].setFieldId(j+1);
            two_fields[j].setFieldName("" + (j + 1) + "号场地");
            entityManager.persist(two_fields[j]);
        }
    }

    public  void loadCourse(EntityManager entityManager) {
        Course course_pingpong = new Course("乒乓球", "周一", "10:00", "11:30", 1, "钟晖");
        Course course_basketball = new Course("篮球", "周二", "08:00", "09:30", 1, "阎健");
        Course course_volleyball_1 = new Course("排球", "周二", "08:00", "09:30", 2, "潘国平");
        Course course_volleyball_2 = new Course("排球", "周五", "10:00", "11:30", 2, "阎健");
            course_pingpong.setCourseId(1);
            course_basketball.setCourseId(2);
            course_volleyball_1.setCourseId(3);
            course_volleyball_2.setCourseId(4);
        entityManager.persist(course_pingpong);
        entityManager.persist(course_basketball);
        entityManager.persist(course_volleyball_1);
        entityManager.persist(course_volleyball_2);
    }

    public  void loadTeach(EntityManager entityManager) {
        Teach teach_1 = new Teach(1, 1);
        Teach teach_2 = new Teach(2, 2);
        Teach teach_3 = new Teach(3, 3);
        Teach teach_4 = new Teach(2, 4);
        entityManager.persist(teach_1);
        entityManager.persist(teach_2);
        entityManager.persist(teach_3);
        entityManager.persist(teach_4);
    }

    public  void loadTake(EntityManager entityManager) {
        Take take_1 = new Take(4, 1);
        Take take_2 = new Take(5, 2);
        Take take_3 = new Take(6, 3);
        Take take_4 = new Take(4, 2);

        entityManager.persist(take_1);
        entityManager.persist(take_2);
        entityManager.persist(take_3);
        entityManager.persist(take_4);

    }

    public  void loadReserve(EntityManager entityManager) {
        Reserve reserve_1 = new Reserve(3,2,1,"2019-12-23","08:00","12:00");
        Reserve reserve_2 = new Reserve(5,2,1,"2019-12-23","13:00","15:00");
        Reserve reserve_3 = new Reserve(7,1,1,"2019-12-24","09:30","11:00");
        Reserve reserve_4 = new Reserve(5,2,2,"2019-12-24","13:00","15:00");
            reserve_1.setReserveId(1);
            reserve_2.setReserveId(2);
            reserve_3.setReserveId(3);
            reserve_4.setReserveId(4);
        entityManager.persist(reserve_1);
        entityManager.persist(reserve_2);
        entityManager.persist(reserve_3);
        entityManager.persist(reserve_4);
    }

    public  void loadAnnounce(EntityManager entityManager) {
        Announce announce_1 = new Announce("第17周周一钟晖乒乓球课本周停课","2019-12-22 8:00");
        Announce announce_2 = new Announce("第17周周三下午中北体育馆羽毛球馆举办软件学院羽毛球比赛","2019-12-23 12:00");
            announce_1.setAnnounceId(1);
            announce_2.setAnnounceId(2);
        entityManager.persist(announce_1);
        entityManager.persist(announce_2);
    }
}