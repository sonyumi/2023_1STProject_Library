DROP TABLE RENTAL;
DROP TABLE BOOKLIST;
DROP TABLE MANAGER;
DROP TABLE G_USER ;

CREATE TABLE manager
(
 id varchar2(20) NOT NULL,
 pw varchar2(20) NOT NULL,
 name varchar2(15) NOT NULL,
 gender varchar2(4),
 birth varchar2(6),
 p_number varchar2(11),
 email varchar2(40),
 CONSTRAINT manager_pk PRIMARY KEY (id)
);

INSERT INTO manager (id,pw,name,gender,birth,p_number,email)
VALUES ('super','super','관리자','여','001004','01012345678','null');

CREATE TABLE g_user
(
 id varchar2(20) NOT NULL,
 pw varchar2(20) NOT NULL,
 name varchar2(15) NOT NULL,
 gender varchar2(4) NOT NULL,
 birth varchar2(6) NOT NULL,
 p_number varchar2(11) NOT NULL,
 email varchar2(40),
 CONSTRAINT g_user_pk PRIMARY KEY (id)
);

INSERT INTO g_user (id,pw,name,gender,birth,p_number,email)
VALUES ('test1','1234','커비','여','901004','01012345678','test1@gmail.com');

CREATE TABLE booklist
(
 book_code varchar2(11) NOT NULL,
 book_name varchar2(100) NOT NULL,
 writer varchar2(30),
 publisher varchar2(40),
 position varchar2(4),
 image varchar2(150),
 CONSTRAINT booklist PRIMARY key(book_code)
);

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023053011','JAVA의정석','남궁성','도우출판','A-1','..\20231STProjectLibrary\img\bookDBimage\JAVA_nomal.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023053012','JAVA의정석기초편','남궁성','도우출판','A-1','..\20231STProjectLibrary\img\bookDBimage\JAVA_basic.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023053013','HTML+CSS+자바스크립트','김기수','길벗','A-2','..\20231STProjectLibrary\img\bookDBimage\coding_html_css_javascript.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023061001','웹표준의정석','고경희','이지스퍼블리싱','A-2','..\20231STProjectLibrary\img\bookDBimage\HTML_CSS_Javascript.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023061002','자바프로그래밍베이직','MANA TAKAHKSHI','영진닷컴','B-2','..\20231STProjectLibrary\img\bookDBimage\imgs_Java_Programming_3rd_Edition__Mana_Takahashi.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023061212','모바일웹개발의정석','그랙아볼라,존라쉬','제우미디어','C-3','..\20231STProjectLibrary\img\bookDBimage\mobile_wep.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023061215','객체지향프로그래밍','변상용','홍릉과학출판사','C-2','..\20231STProjectLibrary\img\bookDBimage\object_programing.png');

INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023061305','베테랑의공부','임종령','콘텍트','A-2','..\20231STProjectLibrary\img\bookDBimage\pro_study.png');


INSERT INTO booklist (book_code,book_name,writer,publisher,POSITION,image)
VALUES ('A2023062001','이것이자바다','신용권,임경균','한빛미디어','D-6','..\20231STProjectLibrary\img\bookDBimage\thisisjava.png');

CREATE TABLE rental
(
 rental_code int NOT NULL PRIMARY KEY,
 book_code varchar2(11) NOT NULL,
 rental_user varchar2(20) NOT NULL,
 rental_date date,
 rental_days varchar2(2),
 return_date date,
 FOREIGN key(rental_user)
 REFERENCES g_user(id),
 FOREIGN key(book_code)
 REFERENCES booklist(book_code)
);

CREATE SEQUENCE num1 START WITH 1 
INCREMENT BY 1 MAXVALUE 99999 NOCYCLE nocache;

INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)
VALUES (num1.nextval,'A2023053011','test1',sysdate,'14');

INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)
VALUES (num1.nextval,'A2023061305','test1',sysdate,'14');

INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)
VALUES (num1.nextval,'A2023061001','test1',sysdate,'14');

INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)
VALUES (num1.nextval,'A2023053013','test1',sysdate,'14');

INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)
VALUES (num1.nextval,'A2023061215','test1',sysdate,'14');

INSERT INTO rental (rental_code,book_code,rental_user,rental_date,rental_days)
VALUES (num1.nextval,'A2023061212','test1',sysdate,'14');

UPDATE rental SET return_date=SYSDATE WHERE book_code='A2023061305';

UPDATE rental SET return_date=SYSDATE WHERE book_code='A2023061001';

UPDATE rental SET return_date=SYSDATE WHERE book_code='A2023053011';