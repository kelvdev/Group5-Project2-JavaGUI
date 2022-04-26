USE team5;

DROP TABLE IF EXISTS THI2;
DROP TABLE IF EXISTS Visit2;

-- We create alternate THI and Visit tables without any foreign key constrains so that we can insert sample data without figure what table references to what table

CREATE TABLE THI2(
	Visit_ID	INTEGER,
    Sc_T		SMALLINT,
    Sc_F		SMALLINT,
    Sc_E		SMALLINT,
    Sc_C		SMALLINT,
    F1			SMALLINT,
    F2			SMALLINT,
    E3			SMALLINT,
    F4			SMALLINT,
    C5			SMALLINT,
    E6			SMALLINT,
    F7			SMALLINT,
    C8			SMALLINT,
    F9			SMALLINT,
    E10			SMALLINT,
    C11			SMALLINT,
    F12			SMALLINT,
    F13			SMALLINT,
    E14			SMALLINT,
    F15			SMALLINT,
    E16			SMALLINT,
    E17			SMALLINT,
    F18			SMALLINT,
    C19			SMALLINT,
    F20			SMALLINT,
    E21			SMALLINT,
    E22			SMALLINT,
    C23			SMALLINT,
    F24			SMALLINT,
    E25			SMALLINT
);

CREATE TABLE Visit2(
	Visit_ID	INTEGER AUTO_INCREMENT,
    THC			INT NOT NULL,
    Visit_nr	SMALLINT NOT NULL,
    Date		DATETIME NOT NULL,
    Comments	TEXT,
    PRIMARY KEY (Visit_ID)
);

-- Sample data 
-- NOTE: Sc column values are all -1 as dummy values since Sc_T will be computed with the other column values using THIScore().
-- NOTE: All Sc column values (except Sc_T) may be updated in the future through either an SQL procedure/function or updating it through Java Method calls from the application
INSERT INTO THI2
VALUES
	(143, -1, -1, -1, -1, 0, 4, 4, 4, 0, 4, 4, 2, 2, 0, 2, 4, 4, 2, 4, 0, 0, 2, 4, 2, 4, 2, 2, 4, 2),
	(287, -1, -1, -1, -1, 0, 2, 4, 0, 4, 2, 4, 2, 0, 4, 4, 4, 4, 0, 4, 0, 4, 4, 2, 2, 2, 2, 4, 4, 2),
	(602, -1, -1, -1, -1, 2, 2, 2, 4, 4, 4, 4, 0, 0, 2, 4, 2, 2, 4, 0, 2, 0, 0, 0, 2, 2, 2, 0, 4, 4),
	(991, -1, -1, -1, -1, 2, 2, 0, 4, 4, 2, 0, 2, 0, 2, 4, 2, 4, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 4, 0);

SELECT * FROM THI2;

INSERT INTO Visit2
VALUES
	(NULL, 143, 3, '2000-01-01', 'Patient ear hurts'),
    (NULL, 287, 4, '2002-03-07', 'Patient got a new hearing aid'),
    (NULL, 210, 5, '2000-01-01', 'Patient taking new medication'),
	(NULL, 201, 6, '2001-11-12', 'Patient complaining about new ringing sensation'),
    (NULL, 602, 7, '2005-01-01', 'Patient wore earbuds in too tight recently'),
    (NULL, 991, 8, '2000-01-01', 'Patient has seen new improvement in symptoms');
    
SELECT * FROM Visit2;

-- End Create Tables and Insert samples


-- Begin Create PROCEDURE and FUNCTION
DROP PROCEDURE IF EXISTS THIScore;

-- Create the THIScore Procedure
DELIMITER //
CREATE PROCEDURE THIScore(INOUT visitorID INTEGER, OUT score INTEGER, OUT scoreDESC VARCHAR(25))
BEGIN
	SELECT F1 + F2 + E3 + F4 + C5 + E6 + F7 + C8 + F9 + E10 + C11 + F12 + F13 + E14 + F15 + E16 + E17 + F18 + C19 + F20 + E21 + E22 + C23 + F24 + E25 
    INTO score
    FROM THI2
    WHERE visitorID = Visit_ID;
    
    IF score <= 17 THEN SET scoreDESC = 'slight severity';
    ELSEIF score <= 37 THEN SET scoreDESC = 'mild';
    ELSEIF score <= 57 THEN SET scoreDESC = 'moderate';
    ELSEIF score <= 77 THEN SET scoreDESC = 'severe';
    ELSE SET scoreDESC = 'catastrophic';
    END IF;
END //
DELIMITER ;

-- Run the THIScore Procedure
-- INOUT is visitorID
-- OUT are score and scoreDESC
set @visitorID = 143;
set @score = 0;
set @scoreDESC = '0';
call team5.THIScore(@visitorID, @score, @scoreDESC);
select @visitorID, @score, @scoreDESC;

DROP PROCEDURE IF EXISTS VisitorsCount;

-- Create the VisitorsCount Function
DELIMITER //
CREATE FUNCTION VisitorsCount(onDate DATE) 
RETURNS INTEGER
READS SQL DATA
BEGIN
	DECLARE total INTEGER;

	SELECT COUNT(*)
    INTO total
    FROM Visit2
    WHERE `Date` = onDate;
    
RETURN total;
END //
DELIMITER ;

-- Run the VisitorsCount Function
select team5.VisitorsCount('2000-01-01');

-- End Create PROCEDURE and FUNCTION
