#添加自增
alter table user change user_id user_id int not null auto_increment;
alter table role change role_id role_id int not null auto_increment;
alter table reserve change reserve_id reserve_id int not null auto_increment;
alter table gym change gym_id gym_id int not null auto_increment;
alter table field change field_id field_id int not null auto_increment;
alter table course change course_id course_id int not null auto_increment;
alter table announce change announce_id announce_id int not null auto_increment;

#添加外键约束
alter table user add constraint fk0 foreign key(role_id) references role(role_id);
alter table field add constraint fk1 foreign key(gym_id) references gym(gym_id);
alter table reserve add constraint fk2 foreign key(user_id) references user(user_id);
alter table reserve add constraint fk3 foreign key(gym_id,field_id) references field(gym_id,field_id);
alter table course add constraint fk4 foreign key(gym_id) references gym(gym_id);
alter table take add constraint fk5 foreign key(user_id) references user(user_id);
alter table take add constraint fk6 foreign key(course_id) references course(course_id);
alter table teach add constraint fk7 foreign key(user_id) references user(user_id);
alter table teach add constraint fk8 foreign key(course_id) references course(course_id);