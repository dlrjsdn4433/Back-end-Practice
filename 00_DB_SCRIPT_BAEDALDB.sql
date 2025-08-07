drop table if exists `리뷰`;
drop table if exists `매장`;
drop table if exists `메뉴`;
drop table if exists `사용자`;
drop table if exists `주문`;

CREATE TABLE if not exists`리뷰`
(
	`review_code`    INTEGER(3) primary key auto_increment comment '리뷰 코드',
    `order_code`    INTEGER(3) unique not null COMMENT '주문 코드',
    `store_code`    VARCHAR(3) NOT NULL COMMENT '매장 코드',
    `user_code`    INTEGER(3) NOT NULL COMMENT '주문자 코드',
    `review`    VARCHAR(255) NOT NULL COMMENT '리뷰 내용'
) COMMENT = '리뷰';

CREATE TABLE if not exists `매장`
(
	`store_code`    INTEGER(3) AUTO_INCREMENT NOT NULL COMMENT '매장 코드',
    `user_code`    INTEGER(3) NOT NULL COMMENT '사장 코드',
    `store_name`    VARCHAR(255) NOT NULL COMMENT '매장명',
     PRIMARY KEY ( `store_code` )
) COMMENT = '매장';

CREATE TABLE if not exists`메뉴`
(
	`menu_name`    VARCHAR(20) NOT NULL COMMENT '메뉴명',
    `menu_price`    INTEGER(20) not null comment '메뉴 가격',
	`store_code`    integer(3) NOT NULL COMMENT '매장 코드',
    `cooking_time`    INTEGER(5) COMMENT '조리시간',
    `orderable_status`    VARCHAR(3) NOT NULL COMMENT '주문 가능 여부',
 PRIMARY KEY ( `menu_name` )
) COMMENT = '메뉴';

CREATE TABLE if not exists`사용자`
(
    `user_code`   INTEGER(3) NOT NULL auto_increment COMMENT '사용자코드',
    `user_name`    VARCHAR(255) NOT NULL COMMENT '사용자이름',
    `phone`    VARCHAR(15) NOT NULL COMMENT '사용자전화번호',
    `user_address`    VARCHAR(255) NOT NULL COMMENT '사용자 주소',
    `distinction`    VARCHAR(5) NOT NULL COMMENT '사용자 구분',
 PRIMARY KEY ( `user_code` )
) COMMENT = '사용자';

CREATE TABLE if not exists `주문`
(
    `order_code`    INTEGER(3) NOT NULL auto_increment COMMENT '주문 코드',
    `user_code`    INTEGER(3) NOT NULL COMMENT '주문자코드',
    `menu_name`    VARCHAR(20) NOT NULL COMMENT '메뉴명',
    `delivery_time`    VARCHAR(10) COMMENT '배달 시간',
 PRIMARY KEY ( `order_code` )
) COMMENT = '주문';


insert into 사용자(user_code,user_name,phone,user_address,distinction)
values(null,'홍길동','010-1254-1734','하남시 미사동','주문자'),
	  (null,'유관순','010-1254-1734','하남시 천현동','주문자'),
	  (null,'이순신','010-1235-3756','하남시 신장동','주문자'),
	  (null,'안중근','010-1364-7348','하남시 덕풍동','사장'),
	  (null,'엄복동','010-1414-5324','하남시 창우동','주문자'),
	  (null,'이정재','010-1274-5798','하남시 풍산동','사장');
      
insert into 매장(store_code, store_name, user_code)
values (null,'길동이네 떡볶이',1),
	   (null,'길동이네 치킨',1),
	   (null,'길동이네 감자탕',1),
	   (null,'유관순 김밥',2),
	   (null,'유관순 떡집',2),
	   (null,'이순신 횟집',3),
	   (null,'안중근 마라탕',4),
	   (null,'안중근 탕후루',4),
	   (null,'엄복동 과일',5),
	   (null,'엄복동 스무디',5),
	   (null,'이정재 달고나',6),
	   (null,'이정재 국수',6),
	   (null,'이정재 국밥',6);
      
insert into 메뉴(menu_name, store_code,menu_price, cooking_time, orderable_status)
values('떡볶이',1,1000,10,'Y'),
	  ('라볶이',1,1400,13,'Y'),
	  ('간장치킨',2,10000,25,'Y'),
	  ('치즈볼',2,3000,10,'Y'),
	  ('뼈해장국',3,7000,5,'Y'),
	  ('감자탕',3,20000,16,'Y'),
	  ('김밥',4,2000,3,'Y'),
	  ('라면',4,2000,4,'Y'),
	  ('가래떡',5,1000,1,'Y'),
	  ('꿀떡',5,1000,2,'Y'),
	  ('광어',6,30000,15,'Y'),
	  ('마라샹궈',7,25000,11,'Y'),
	  ('마라탕',7,13000,13,'Y'),
	  ('꿔바로우',7,18000,25,'Y'),
	  ('딸기탕후루',8,4500,2,'Y'),
	  ('샤인머스캣탕후루',8,5000,6,'Y'),
	  ('바나나',9,3500,0,'Y'),
	  ('사과',9,1600,0,'Y'),
	  ('딸바스무디',10,3000,5,'Y'),
	  ('초코바나나스무디',10,3700,6,'Y'),
	  ('우산모양달고나',11,1300,7,'Y'),
	  ('별모양달고나',11,1700,6,'Y'),
	  ('잔치국수',12,3500,5,'Y'),
	  ('열무국수',12,4000,6,'Y'),
	  ('비빔국수',12,4000,7,'Y'),
	  ('돼지국밥',13,8000,90,'Y'),
	  ('소머리국밥',13,9000,70,'Y');
