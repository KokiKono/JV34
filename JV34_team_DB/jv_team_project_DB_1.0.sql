SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS advantage_salary_master;
DROP TABLE IF EXISTS attendance_table;
DROP TABLE IF EXISTS have_licence_table;
DROP TABLE IF EXISTS salary_licence_table;
DROP TABLE IF EXISTS salary_master;
DROP TABLE IF EXISTS employee_master;
DROP TABLE IF EXISTS sub_department_master;
DROP TABLE IF EXISTS department_master;
DROP TABLE IF EXISTS licence_master;
DROP TABLE IF EXISTS lowest_working_days_master;
DROP TABLE IF EXISTS official_position_master;




/* Create Tables */

CREATE TABLE advantage_salary_master
(
	advantage_id int NOT NULL AUTO_INCREMENT,
	work_year_from int NOT NULL,
	work_year_to int NOT NULL,
	advantage_salary int(8) NOT NULL,
	PRIMARY KEY (advantage_id),
	UNIQUE (advantage_id)
);


CREATE TABLE attendance_table
(
	date date NOT NULL,
	-- 0000001は先生のチェック用
	employee_master char(7) NOT NULL COMMENT '0000001は先生のチェック用',
	PRIMARY KEY (date, employee_master)
);


CREATE TABLE department_master
(
	department_id int NOT NULL AUTO_INCREMENT,
	department_name varchar(20) NOT NULL,
	PRIMARY KEY (department_id),
	UNIQUE (department_id)
);


CREATE TABLE employee_master
(
	-- 0000001は先生のチェック用
	employee_master char(7) NOT NULL COMMENT '0000001は先生のチェック用',
	-- 先生チェック用アカウントのパスワードはyamatyu
	password varchar(20) NOT NULL COMMENT '先生チェック用アカウントのパスワードはyamatyu',
	employee_name_kana varchar(20) NOT NULL,
	employee_name varchar(20) NOT NULL,
	birthday date NOT NULL,
	sex int(0) NOT NULL,
	postal_code char(7) NOT NULL,
	address varchar(50) NOT NULL,
	tell char(11),
	department_id int NOT NULL,
	sub_department_id int,
	official_position_id int NOT NULL,
	joined_month date NOT NULL,
	leaving_month date,
	PRIMARY KEY (employee_master),
	UNIQUE (employee_master)
);


CREATE TABLE have_licence_table
(
	-- 0000001は先生のチェック用
	employee_master char(7) NOT NULL COMMENT '0000001は先生のチェック用',
	licence_id int NOT NULL,
	get_licence_date date NOT NULL,
	PRIMARY KEY (employee_master, licence_id),
	UNIQUE (employee_master),
	UNIQUE (licence_id)
);


CREATE TABLE licence_master
(
	licence_id int NOT NULL AUTO_INCREMENT,
	licence_name varchar(30) NOT NULL,
	rank int(1) NOT NULL,
	PRIMARY KEY (licence_id),
	UNIQUE (licence_id)
);


CREATE TABLE lowest_working_days_master
(
	period date NOT NULL,
	lowest_working_days int NOT NULL,
	PRIMARY KEY (period)
);


CREATE TABLE official_position_master
(
	official_position_id int NOT NULL AUTO_INCREMENT,
	official_position_name varchar(20) NOT NULL,
	official_position_allowance int(8) NOT NULL,
	-- この値が大きいほど地位が高い
	rank int(4) DEFAULT 0 NOT NULL COMMENT 'この値が大きいほど地位が高い',
	PRIMARY KEY (official_position_id),
	UNIQUE (official_position_id)
);


CREATE TABLE salary_licence_table
(
	payroll_id int NOT NULL,
	licence_id int NOT NULL
);


CREATE TABLE salary_master
(
	payroll_id int NOT NULL AUTO_INCREMENT,
	-- 0000001は先生のチェック用
	employee_master char(7) NOT NULL COMMENT '0000001は先生のチェック用',
	payment_period date NOT NULL,
	salary int(8) NOT NULL,
	base_salary int(8) DEFAULT 250000 NOT NULL,
	work_year int NOT NULL,
	advantage_saraly int(8) DEFAULT 0 NOT NULL,
	attendance_salary int(8) DEFAULT 0 NOT NULL,
	official_position varchar(20),
	official_position_allowance int(8) DEFAULT 0 NOT NULL,
	licence_allowance int(8),
	PRIMARY KEY (payroll_id),
	UNIQUE (payroll_id)
);


CREATE TABLE sub_department_master
(
	sub_department_id int NOT NULL AUTO_INCREMENT,
	sub_department_name varchar(20) NOT NULL,
	department_id int NOT NULL,
	PRIMARY KEY (sub_department_id),
	UNIQUE (sub_department_id)
);



