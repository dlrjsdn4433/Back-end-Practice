<h1>Back-end-Practice</h1>
<hr>
<h2>1. 기획</h2>
<p>배달연결 프로그램을 구현하여 사용자를 매장 사장과 주문자로 구별하여 매장등록, 메뉴 등록, 주문, 리뷰 작성 등의 행동을 할 수 있게 한다.</p>
<h2>2. 논리 모델</h2>
<img width="585" height="535" alt="Image" src="https://github.com/user-attachments/assets/929526df-3609-4f9d-bab6-f86784986ee0" />
<h2>3. 물리 모델</h2>
<img width="772" height="530" alt="Image" src="https://github.com/user-attachments/assets/36bd6d1d-99f1-425a-8bc3-85b5d86ffe77" />
<h2>4. SQL 스크립트파일</h2>
<pre>drop table if exists `리뷰`;
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
    `menu_amount` integer(10) not null comment '메뉴 수량',
    `delivery_time`    VARCHAR(10) not null COMMENT '배달 시간',
 PRIMARY KEY ( `order_code` )
) COMMENT = '주문';</pre>
