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
INSERT INTO musicians(first_name,last_name, instrument1, instrument2) 
VALUES 
	("Mickey","Mouse","Violin1",""),
	("Minnie","Mouse","Violin2",""),
	("Donald","Duck","Viola",""),
	("Daisy","Duck","Cello",""),
	("Tinkerbell","Fairy","Soprano Vocals",""),
	("Pluto","Dog","Double Bass",""),
	("Hewey","Duck","Oboe","English Horn"),
	("Duey","Duck","Flute","Piccolo"),
	("Louie","Duck","Clarinet",""),
	("Scrooge","McDuck","Trumpet",""),
	("Jiminy","Cricket","Soprano Vocals","Bells"),
	("Dumbo","Elephant","Double Bass",""),
	("Blackheart","Beagle","Tuba",""),
	("Grandpa","Beagle","Tympani",""),
	("176-761 Burger","Beagle","Violin2",""),
	("617-716 Baggy","Beagle","Violin2",""),
	("176-167 Babyface","Beagle","Violin1",""),
	("716-167 Bouncer","Beagle","Trumpet",""),
	("617-167 Bankjob","Beagle","Random",""),
	("167-671 Bigtime", "Beagle", "Cello", ""),
	("671-761 Bugle/Bebop", "Beagle", "Cello", "");

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
