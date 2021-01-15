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
--      CRUD operations on your table.
--    
--  TIPS:- The app does not need to be as complex as the example in the videos.
--       - You need an option for each of the CRUD operations (Create,Read,Update,Delete)
--       - Remember that PreparedStatment.executeQuery() is only for Reading data 
--         and .executeUpdate() is used for Creating, Updating, and Deleting data.
--       - Remember that both parameters on PreparedStatements and the ResultSet columns 
--         are based on indexes that start with 1, not 0. 
--
--

--
-- week5_populateDB assumes that the orchestra database exists.
--
-- orchestra is created using week5_createDB.sql
--      Run that script first!
--
USE orchestra;

-- section:  String, Woodwind, Percussion, Brass, Voice,
-- name:  Violin1(16-18), Violin2(16), Viola(12), Cello(12), Double Bass(8),
--        Flute(4), Piccolo(1) Oboe(4), English Horn(1), Clarinet(4), Bass Clarinet(1), 
--        Bassoon(4), Double Bassoon(1), 
--        French Horn(5-8), Trumpet(4), Trombone(4), Bass Trombone(1), Tuba(1)
--        Kettle Drum(1), Percussionists(3-4)
--        Harp (1-2) Keyboard Player(Piano, Celesta, Harpsichord)(1)
--        Conductor, Music Librarian
-- 		  Other temporary instruments: saxophone, guitar, bass oboe, synthesizer, etc.


-- 
-- Populate the instruments table
-- 

INSERT INTO instruments(section,name) 
VALUES ("String","Violin1"),
	   ("String","Violin2"),
	   ("String","Viola"),
	   ("String","Cello"),
	   ("String","Double Bass"),
	   ("Woodwind","Flute"),
	   ("Woodwind","Piccolo"),
	   ("Woodwind","Oboe"),
	   ("Woodwind","English Horn"),
	   ("Woodwind","Clarinet"),
	   ("Woodwind","Bass Clarinet"),
	   ("Woodwind","Bassoon"),
	   ("Woodwind","Double Bassoon"),
	   ("Brass","Trumpet"),
	   ("Brass","French Horn"),
	   ("Brass","Trombone"),
	   ("Brass","Bass Trombone"),
	   ("Brass","Tuba"), 
	   ("Percussion","Bells/Chimes"),
	   ("Percussion","Tympani"),
	   ("Percussion","Random"),
	   ("Percussion","Harp"),
	   ("Keyboard","Piano"), 
	   ("Keyboard","Celesta"), 
	   ("Keyboard","Harpsichord"),
	   ("Voice","Soprano Vocals"),
	   ("Voice","Mezzo Vocals"),
	   ("Voice","Alto Vocals"),
	   ("Voice","Tenor Vocals"),
	   ("Voice","Baritone Vocals"),
	   ("Voice","Bass Vocals");


-- 
-- Populate the musicians table
-- 

INSERT INTO musicians(first_name,last_name) 
VALUES 	("Mickey","Mouse"),
		("Minnie","Mouse"),
		("Donald","Duck"),
		("Daisy","Duck"),
		("Tinkerbell","Fairy"),
		("Pluto","Dog"),
		("Hewey","Duck"),
		("Duey","Duck"),
		("Louie","Duck"),
		("Scrooge","McDuck"),
		("Jiminy","Cricket"),
		("Dumbo","Elephant"),
		("Blackheart","Beagle"),
		("Grandpa","Beagle"),
		("176-761 Burger","Beagle"),
		("617-716 Baggy","Beagle"),
		("176-167 Babyface","Beagle"),
		("716-167 Bouncer","Beagle"),
		("617-167 Bankjob","Beagle"),
		("167-671 Bigtime", "Beagle"),
		("671-761 Bugle/Bebop", "Beagle");
		
		
--  FUTURE EXTENSION:  Add functionality to support multiple instruments by musician
--  The database supports it, but the Java program is a work in progress.

SET @main = 'primary';
SELECT musician_id into @music_id FROM musicians WHERE first_name = "Mickey" AND last_name = "Mouse";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Violin1";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Minnie" AND last_name = "Mouse";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Violin2";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Donald" AND last_name = "Duck";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Viola";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Daisy" AND last_name = "Duck";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Cello";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Tinkerbell" AND last_name = "Fairy";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Soprano Vocals";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Pluto" AND last_name = "Dog";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Double Bass";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Hewey" AND last_name = "Duck";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Oboe";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Duey" AND last_name = "Duck";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Flute";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Louie" AND last_name = "Duck";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Clarinet";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Scrooge" AND last_name = "McDuck";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Trumpet";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Jiminy" AND last_name = "Cricket";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Soprano Vocals";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Dumbo" AND last_name = "Elephant";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Double Bass";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Blackheart" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Tuba";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "Grandpa" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Tuba";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "176-761 Burger" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Violin2";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "617-716 Baggy" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Violin2";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "176-167 Babyface" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Violin1";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "716-167 Bouncer" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Trumpet";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "617-167 Bankjob" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Random";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "167-671 Bigtime" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Cello";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);

SELECT musician_id into @music_id FROM musicians WHERE first_name = "671-761 Bugle/Bebop" AND last_name = "Beagle";
SELECT instrument_id INTO @inst_id FROM instruments WHERE name = "Cello";
INSERT INTO musician_instrument (musician_id,instrument_id,main_instrument) VALUES (@music_id,@inst_id,@main);






--
-- Populate the positions table
-- 

DROP PROCEDURE IF EXISTS InsertPositionsIntoSymphonyOrchestra;

DELIMITER %% 

CREATE PROCEDURE InsertPositionsIntoSymphonyOrchestra(IN positionLimit INT, IN instrumentId INT)
BEGIN
	DECLARE counter INT DEFAULT 1;
	DECLARE concert_master VARCHAR(20) DEFAULT "";
	WHILE counter <= positionLimit DO
		IF counter = 1 THEN 
			IF instrumentId = 1 THEN
				SET concert_master = "Concertmaster";
			ELSE
				SET concert_master = "Principal";
			END IF;
		END IF;				
		INSERT INTO positions (instrument_id, chair, special_title) VALUES (instrumentId,counter,concert_master);
		SET concert_master = "";
		SET counter = counter + 1;	
	END WHILE;
END%%

DELIMITER ; 


-- Violin1(16-18), Violin2(16), Viola(12), Cello(12), Double Bass(8)

SELECT instrument_id into @temp_id from instruments where name = "Violin1";
CALL InsertPositionsIntoSymphonyOrchestra(16, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Violin2";
CALL InsertPositionsIntoSymphonyOrchestra(16, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Viola";
CALL InsertPositionsIntoSymphonyOrchestra(12, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Cello";
CALL InsertPositionsIntoSymphonyOrchestra(12, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Double Bass";
CALL InsertPositionsIntoSymphonyOrchestra(8, @temp_id);


-- Flute(4), Piccolo(1) Oboe(4), English Horn(1), Clarinet(4), Bass Clarinet(1), 

SELECT instrument_id into @temp_id from instruments where name = "Flute";
CALL InsertPositionsIntoSymphonyOrchestra(4, @temp_id);  

SELECT instrument_id into @temp_id from instruments where name = "Piccolo";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Oboe";
CALL InsertPositionsIntoSymphonyOrchestra(4, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "English Horn";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Clarinet";
CALL InsertPositionsIntoSymphonyOrchestra(4, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Bass Clarinet";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
       
-- Bassoon(4), Double Bassoon(1)

SELECT instrument_id into @temp_id from instruments where name = "Bassoon";
CALL InsertPositionsIntoSymphonyOrchestra(4, @temp_id);
  
SELECT instrument_id into @temp_id from instruments where name = "Double Bassoon";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
            

-- French Horn(5-8), Trumpet(4), Trombone(4), Bass Trombone(1), Tuba(1)

SELECT instrument_id into @temp_id from instruments where name = "French Horn";
CALL InsertPositionsIntoSymphonyOrchestra(5, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Trumpet";
CALL InsertPositionsIntoSymphonyOrchestra(4, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Trombone";
CALL InsertPositionsIntoSymphonyOrchestra(4, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Bass Trombone";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Tuba";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
             
-- Kettle Drum(1), Percussionists(3-4), Harp (1-2) 

SELECT instrument_id into @temp_id from instruments where name = "Tympani";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Bells/Chimes";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Random";
CALL InsertPositionsIntoSymphonyOrchestra(3, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Harp";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
          
-- Keyboard Player(Piano, Celesta, Harpsichord)(1)

SELECT instrument_id into @temp_id from instruments where name = "Piano";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
       
SELECT instrument_id into @temp_id from instruments where name = "Celesta";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
       
SELECT instrument_id into @temp_id from instruments where name = "Harpsichord";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

-- Voice
SELECT instrument_id into @temp_id from instruments where name = "Soprano Vocals";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
 
SELECT instrument_id into @temp_id from instruments where name = "Mezzo Vocals";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Alto Vocals";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Tenor Vocals";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);

SELECT instrument_id into @temp_id from instruments where name = "Baritone Vocals";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
      
SELECT instrument_id into @temp_id from instruments where name = "Bass Vocals";
CALL InsertPositionsIntoSymphonyOrchestra(1, @temp_id);
