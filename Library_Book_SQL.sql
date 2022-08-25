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
 constraint fk_category foreign key (CATEGORY_ID) references CATEGORY(CATEGORY_ID),
constraint fk_author foreign key (AUTHOR_ID) references AUTHOR(AUTHOR_ID)
);

Insert into CATEGORY(CATEGORY_ID, CATEGORY_NAME) values("1", " Lich su"),
("2", " Toan hoc"),
("5", "Ngoai ngu"), 
("3", "Sinh hoc") , ("4","Hoa hoc");

Insert into AUTHOR(AUTHOR_ID, AUTHOR_NAME) values
("1", "Tran Hoang Long"),
("2", "Tran Quoc Viet"),
("3", "Tran Quoc Hoan"),
("4","Ha Ngoc Long");

Insert into BOOK(ID, BOOK_NAME, AMOUNT, CATEGORY_ID, AUTHOR_ID, FIRST_LETTER) values
 ("1","Lich su dai viet","30","1","1", "L"),
 ("2","Toan hoc 1","30","1","1", "T"),
 ("3","Sinh hoc 8","30","3","2", "S"),
 ("4", "Hoa hoc 9" , "30","4", "3","H");
