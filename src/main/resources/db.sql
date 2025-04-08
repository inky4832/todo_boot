use testdb;

drop table if exists todo;
drop table if exists member;

create table member(	
     userid varchar(255) primary key COMMENT '아이디',
     passwd varchar(255) not null    COMMENT '비밀번호',
     username varchar(255) not null  COMMENT 'TODO 작성자'
);
	  
create table todo
    ( id bigint not null auto_increment COMMENT 'TODO 번호',
      userid varchar(255) not null COMMENT 'TODO 아이디',
      description varchar(255) not null COMMENT 'TODO 목록',
      targetDate date  COMMENT 'TODO 목표날짜',
      done boolean   COMMENT 'TODO 완료여부',
      primary key(id)
     );
     
insert into todo ( id, userid, description, targetDate, done ) values ( 1000,'inky4832', 'Learn SpringBoot2.1.8', DATE_ADD(NOW(), INTERVAL 1 YEAR), false);
insert into todo ( id, userid, description, targetDate, done ) values ( 1001,'inky4832', 'Learn Reactjs', DATE_ADD(NOW(), INTERVAL 1 MONTH), false);
insert into todo ( id, userid, description, targetDate, done ) values ( 1002,'inky4832', 'Learn SpringSecurity', DATE_ADD(NOW(), INTERVAL 10 DAY), false);
insert into todo ( id, userid, description, targetDate, done ) values ( 200,'kim4832', 'Learn Dance', DATE_ADD(NOW(), INTERVAL 3 YEAR), false);
commit;


