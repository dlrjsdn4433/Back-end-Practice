DROP TABLE if exists `리뷰`;
DROP TABLE if exists `매장`;
DROP TABLE if exists `사용자`;
DROP TABLE if exists `메뉴`;
DROP TABLE if exists `주문`;

CREATE TABLE if not exists `사용자`
(
    `user_code`    INTEGER(3) auto_increment NOT NULL COMMENT '사용자코드',
    `user_name`    VARCHAR(255) NOT NULL COMMENT '사용자이름',
    `phone`    VARCHAR(15) NOT NULL COMMENT '사용자전화번호',
    `user_address`    VARCHAR(255) NOT NULL COMMENT '사용자 주소',
    `distinction`    VARCHAR(5) NOT NULL COMMENT '사용자 구분',
 PRIMARY KEY ( `user_code` )
) COMMENT = '사용자';

CREATE TABLE if not exists `매장`
(
	`store_code`    INTEGER(3) AUTO_INCREMENT NOT NULL COMMENT '매장 코드',
    `user_code`    INTEGER(3) NOT NULL COMMENT '사장 코드',
    `store_name`    VARCHAR(255) NOT NULL COMMENT '매장명',
     PRIMARY KEY ( `store_code` )
) COMMENT = '매장';

CREATE TABLE if not exists `메뉴`
(
    `menu_name`    VARCHAR(20) NOT NULL COMMENT '메뉴명',
    `cooking_time`    VARCHAR(5) COMMENT '조리시간',
    `orderable_status`    VARCHAR(3) check(`orderable_status` in ('y','n')) NOT NULL COMMENT  '주문 가능 여부',
    `menu_price`    INTEGER(20) NOT NULL COMMENT '메뉴가격',
    `store_name`    VARCHAR(255) NOT NULL COMMENT '매장명',
 PRIMARY KEY ( `store_name`,`menu_name` )
) COMMENT = '메뉴';

CREATE TABLE if not exists `주문`
(
    `order_code`    INTEGER(3) auto_increment NOT NULL COMMENT '주문 코드',
    `delivery_time`    VARCHAR(10) NOT NULL COMMENT '배달 시간',
    `user_code`    INTEGER(3) NOT NULL COMMENT '주문자코드',
    `menu_name`    VARCHAR(20) NOT NULL COMMENT '메뉴명',
    `menu_amount`    INTEGER(10) NOT NULL COMMENT '메뉴 수량',
    `store_name`    VARCHAR(255) NOT NULL COMMENT '매장명',
 PRIMARY KEY ( `order_code` )
) COMMENT = '주문';

CREATE TABLE if not exists `리뷰`
(
    `review`    VARCHAR(255) NOT NULL COMMENT '리뷰 내용',
    `user_code`    INTEGER(3) NOT NULL COMMENT '주문자 코드',
    `store_name`    VARCHAR(255) NOT NULL COMMENT '매장명',
    `order_code`    INTEGER(3) NOT NULL COMMENT '리뷰 코드',
 PRIMARY KEY ( `order_code` )
) COMMENT = '리뷰';

insert into 사용자(user_code,user_name,phone,user_address,distinction)
values(null,'홍길동','010-1254-1734','하남시 미사동','주문자'),
	  (null,'유관순','010-1254-1734','하남시 천현동','주문자'),
	  (null,'이순신','010-1235-3756','하남시 신장동','주문자'),
	  (null,'안중근','010-1364-7348','하남시 덕풍동','사장'),
	  (null,'엄복동','010-1414-5324','하남시 창우동','주문자'),
	  (null,'이정재','010-1274-5798','하남시 풍산동','사장');
      
      



