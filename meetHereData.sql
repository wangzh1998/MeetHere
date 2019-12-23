insert into role values 
	(1,"ROLE_ADMIN"),(2,"ROLE_STUDENT"),
	(3,"ROLE_TEACHER"),(4,"ROLE_TOURIST");



insert into gym values 
	(1,"中北大学生活动中心羽毛球馆 ","08:00","22:00"),
	(2,"中北体育馆羽毛球馆","07:00","22:00");
insert into field(gym_id,field_id,field_name) values 
	(1,1,"1号场地"),(1,2,"2号 场地"),(1,3,"3号场地"),
	(1,4,"4号场地"),(1,5,"5号场地"),(1,6,"6号场地"),
	(1,7, "7号场地"),(1,8,"8号场地");
insert into field(gym_id,field_id,field_name) values 
	(2,1,"1号场地"),(2,2,"2号场地"),(2,3,"3号场地"),
	(2,4,"4号场地"),(2,5,"5号场地"),(2,6,"6号场地"),
	(2,7, "7号场地"),(2,8,"8号场地"),(2,9,"9号场地"),
	(2,10,"10号场地"),(2,11,"11号场地 "),(2,12,"12号场地");


insert into course(course_id,course_name,weekday,start_time,end_time,gym_id,teacher_name) values
	(1,"乒乓球","周一","10:00","11:30",1,"钟晖"),
	(2,"篮球","周二","08:00","09:30",1,"阎健"),
	(3,"排球","周四","13:00","14:30",2,"潘国平"),
	(4,"排球","周五","10:00","11:30",2,"阎健");


insert into teach(user_id,course_id) values
	(1,1),(2,2),(3,3);

insert into reserve(reserve_id,user_id,gym_id,field_id,date,start_time,end_time) values
	(1,3,2,1,"2019-12-23","08:00","12:00"),
	(2,5,2,1,"2019-12-23","13:00","15:00"),
	(3,7,1,1,"2019-12-24","09:00","11:00");

insert into take(user_id,course_id) values
	(4,1),(5,2),(6,3);

insert into announce(anounce_id,content,time) values
	(1,"第17周周一钟晖乒乓球课本周停课","2019-12-22 8:00"),
	(2,"第17周周三下午中北体育馆羽毛球馆举办软件学院羽毛球比赛","2019-12-23 12:00");

insert into user(user_id,name,password,role_id,user_name) values
	(1,"钟晖","123456",3,"Mike"),
	(2,"潘国平","456789",3,"Jack"),
	(3,"阎健","123789",3,"Tom"),
	(4,"张诗晨","147258",2,"Amy"),
	(5,"王昭辉","369258",2,"Lily"),
	(6,"林梦思","147369",2,"Maria"),
	(7,"李雪莹","741852",2,"Sarah");
  