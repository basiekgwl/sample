drop table if exists user;
drop table if exists user_accounts;

create table user(
user_id int primary key auto_increment NOT NULL,
user_name varchar(100) NOT NULL,
user_nip varchar(10),
user_pesel varchar(11),
user_address varchar(100),
city VARCHAR(40)
);

create table user_accounts(
account_id int auto_increment NOT NULL,
user_id int NOT NULL,
account_type enum('savings', 'credit') NOT NULL,
account_nrb VARCHAR(26),
balance float,
PRIMARY KEY ( account_id ),
KEY `FK_USER_ID` (`user_id`),
CONSTRAINT `FK_USER_ID` FOREIGN KEY (user_id) REFERENCES user(user_id)
);

START TRANSACTION;
INSERT INTO user (user_name, user_nip, user_pesel, user_address, city)
VALUES ('Barbara Gawalewicz', '5531710814', '78020412707', 'ul. Życzkowskiego 20, 31-864 Kraków, Kraków');
INSERT INTO user_accounts
set user_id = select user_id from user where user_id = last_insert_id(),
account_type = 'savings',
account_nrb = '26123420040000100312340001',
balance = 20000;
INSERT INTO user_accounts
set user_id = 1,
account_type = 'savings',
account_nrb = '13200315320000100312340001',
balance = 45000;
COMMIT;


START TRANSACTION;
INSERT INTO  user (user_name, user_nip, user_pesel, user_address, city)
VALUES ('Monika Kowalska', '1234320814', '80051214001', 'ul. Długa 12/6, 31-820 Kraków, Kraków');
INSERT INTO user_accounts
set user_id = select user_id from user where user_id =  last_insert_id(),
account_type = 'savings',
account_nrb = '14153220030000100312340006',
balance = 18500;
COMMIT;
