--
-- MySQL Week 3 Database Design
-- Coding Assignment #5
--
-- Author Lisa Maatta Smith
--

-- (1)  Choose one item that you like.  It could be vehicles, sports, foods, etc....
--
-- (2)  Create a new Java project in Eclipse.
--
-- (3)  Create an SQL script in the project to create a database with one table.
--      the table should be the item you picked
--
-- (4)  Write a Java menu driven application that allows you to perform all four
--      CRUD operations on your table.  (e.g. Create, Read, Update, Delete)
--    
--  TIPS:- The app does not need to be as complex as the example in the videos.
--       - You need an option for each of the CRUD operations (Create,Read,Update,Delete)
--       - Remember that PreparedStatment.executeQuery() is only for Reading data 
--         and .executeUpdate() is used for Creating, Updating, and Deleting data.
--       - Remember that both parameters on PreparedStatements and the ResultSet columns 
--         are based on indexes that start with 1, not 0. 
--
--

DROP DATABASE IF EXISTS orchestra;

CREATE DATABASE orchestra;

USE orchestra;

DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS instrument;


CREATE TABLE instruments (
	instrument_id INT NOT NULL AUTO_INCREMENT,
	section VARCHAR(20) NOT NULL,
	name VARCHAR(20) NOT NULL,
	PRIMARY KEY (instrument_id)
);

CREATE TABLE musicians (
	musician_id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	instrument1 VARCHAR(20) NOT NULL,
	instrument2 VARCHAR(20) DEFAULT NULL,	
	PRIMARY KEY (musician_id)
);

CREATE TABLE positions (
	position_id INT NOT NULL AUTO_INCREMENT,
	instrument_id INT NOT NULL,
	chair INT NOT NULL,
	special_title VARCHAR(20) DEFAULT NULL,
	PRIMARY KEY(position_id)
);


CREATE TABLE members (
	member_id INT NOT NULL AUTO_INCREMENT,
	instrument_id INT NOT NULL,
	position_id INT NOT null,
	PRIMARY KEY (member_id),
	FOREIGN KEY (instrument_id) REFERENCES instruments(instrument_id),
	FOREIGN KEY (position_id) REFERENCES positions(position_id)
);


