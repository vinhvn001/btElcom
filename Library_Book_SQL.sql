CREATE database BOOK_MANAGE;
Use BOOK_MANAGE;
CREATE TABLE CATEGORY
(
CATEGORY_ID int(15) not null auto_increment,
CATEGORY_NAME VARCHAR(30),
primary key(CATEGORY_ID)
) 	;	

CREATE TABLE AUTHOR (
    AUTHOR_ID int(15) not null auto_increment,
    AUTHOR_NAME VARCHAR(30),
    PRIMARY KEY (AUTHOR_ID)
);
CREATE TABLE BOOK 
(
ID int(15) NOT NULL auto_increment,
BOOK_NAME VARCHAR(30),
AMOUNT INT(15),
CATEGORY_ID int(15),
AUTHOR_ID int(15),
FIRST_LETTER VARCHAR(5),
primary key(ID),
 foreign key (CATEGORY_ID) references CATEGORY(CATEGORY_ID),
 foreign key (AUTHOR_ID) references AUTHOR(AUTHOR_ID)
);

Insert into CATEGORY(CATEGORY_ID, CATEGORY_NAME) values("1", " Lich su");
Insert into AUTHOR(AUTHOR_ID, AUTHOR_NAME) values("1", "Tran Hoang Long");
Insert into BOOK(ID, BOOK_NAME, AMOUNT, CATEGORY_ID, AUTHOR_ID, FIRST_LETTER) values
 ("1","Lich su dai viet","30","1","1", "L");
