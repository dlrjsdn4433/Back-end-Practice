-- alter table users
-- DROP foreign key user_code;
-- alter table store
-- drop foreign key user_code;
-- alter table menu
-- drop foreign key store_code;
-- alter table orders
-- drop foreign key user_code;
-- alter table orders
-- drop foreign key menu_name ;
-- alter table orders
-- drop foreign key store_code; 

-- alter table
--  foreign key user_code;
--  foreign key order_code;
--  foreign key store_code;

-- DROP TABLE if exists `review`;
-- DROP TABLE if exists `store`;
-- DROP TABLE if exists `users`;
-- DROP TABLE if exists `menu`;
-- DROP TABLE if exists `orders`;

CREATE TABLE if not exists `users`
(
    `user_code`    INTEGER(3) auto_increment NOT NULL COMMENT '사용자코드',
    `user_name`    VARCHAR(255) NOT NULL COMMENT '사용자이름',
    `phone`    VARCHAR(15) NOT NULL COMMENT '사용자전화번호',
    `user_address`    VARCHAR(255) NOT NULL COMMENT '사용자 주소',
    `distinction`    VARCHAR(5) check(`distinction` in('주문자','사장')) NOT NULL COMMENT '사용자 구분',
	`user_id`    VARCHAR(10) unique NOT NULL COMMENT '사용자 아이디',
    `user_pwd`    VARCHAR(10) NOT NULL COMMENT '사용자 비밀번호',
 PRIMARY KEY ( `user_code` )
);

CREATE TABLE if not exists `store`
(
	`store_code`    INTEGER(3) AUTO_INCREMENT NOT NULL COMMENT '매장 코드',
    `user_code`    INTEGER(3) NOT NULL COMMENT '사장 코드',
    `store_name`    VARCHAR(255) NOT NULL COMMENT '매장명',
     PRIMARY KEY ( `store_code` ),
     foreign key(user_code) references users(user_code) on delete cascade
) COMMENT = '매장';

CREATE TABLE if not exists `menu`
(
    `store_code`    integer(3) NOT NULL COMMENT '매장코드',
    `menu_name`    VARCHAR(20) NOT NULL COMMENT '메뉴명',
    `cooking_time`    int(5) COMMENT '조리시간',
    `orderable_status`    VARCHAR(3) default 'Y' check(`orderable_status` in ('y','n'))  NOT NULL COMMENT  '주문 가능 여부',
    `menu_price`    INTEGER(20) NOT NULL COMMENT '메뉴가격',
 PRIMARY KEY ( `store_code`,`menu_name`),
 foreign key(`store_code`) references store(`store_code`) on delete cascade
);

CREATE TABLE if not exists `orders`
(
    `order_code`    INTEGER(3) primary key auto_increment NOT NULL COMMENT '주문 코드',
    `delivery_time`    integer(10) NOT NULL COMMENT '배달 시간',
    `user_code`    INTEGER(3) NOT NULL COMMENT '주문자코드',
    `menu_name`    VARCHAR(20) NOT NULL COMMENT '메뉴명',
    `menu_amount`    INTEGER(10) NOT NULL COMMENT '메뉴 수량',
    `store_code`    integer(3) NOT NULL COMMENT '매장코드',
    `review_status`    CHAR(1) DEFAULT 'N' COMMENT '리뷰 작성 유무',
    `total_price`    INTEGER(10) NOT NULL COMMENT '총 가격',
 foreign key(user_code) references users(user_code) on delete cascade,
 foreign key(store_code,menu_name) references menu(store_code,menu_name) on delete cascade,
 foreign key(store_code) references store(store_code) on delete cascade
);

CREATE TABLE if not exists `review`
(
    `review`    VARCHAR(255)  NOT NULL COMMENT '리뷰 내용',
    `user_code`    INTEGER(3) NOT NULL COMMENT '주문자 코드',
    `store_code`    integer(3)  NOT NULL COMMENT '매장코드',
    `order_code`    INTEGER(3)  NOT NULL COMMENT '리뷰 코드',
    primary key(user_code,store_code,order_code),
 foreign key(user_code) references users(user_code) on delete cascade,
 foreign key(order_code) references orders(order_code) on delete cascade,
 foreign key(store_code) references store(store_code) on delete cascade
 
);