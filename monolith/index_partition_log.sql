use book_manage;
alter table author add index author_name_asc(author_name);
alter table book add index book_name_asc(book_name);
alter table category add index category_name_asc(category_name);
ALTER TABLE `book_manage`.`borrowed_list` 
 PARTITION BY HASH	( MONTH(borrowed_date) )
 PARTITIONS 12	;
 INSERT INTO `book_manage`.`borrowed_list` (`id`, `user_id`, `book_id`, `limit_date`) VALUES ('17', '6', '14', '2022-10-12');
INSERT INTO `book_manage`.`borrowed_list` (`id`, `user_id`, `book_id`, `limit_date`) VALUES ('18', '7', '13', '2022-10-12');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `limit_date`) VALUES ('5', '15', '2022-10-13');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('2', '16', '2022-8-11', '2022-8-20');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('5', '17', '2022-8-12', '2022-8-22');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('4', '18', '2022-8-13', '2022-8-20');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('3', '14', '2022-7-12', '2022-7-24');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('5', '16', '2022-7-15', '2022-7-30');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('4', '17', '2022-7-18', '2022-7-29');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('6', '5', '2022-6-12', '2022-6-21');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('7', '6', '2022-6-15', '2022-6-25');
INSERT INTO `book_manage`.`borrowed_list` (`user_id`, `book_id`, `borrowed_date`, `limit_date`) VALUES ('2', '7', '2022-6-21', '2022-6-30');
