CREATE DATABASE team5;

USE team5;

-- Create Table and add constraints
CREATE TABLE REF_Problem(
	Problem_ID	SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25) UNIQUE,
    PRIMARY KEY	(Problem_ID)
);

CREATE TABLE REF_Instr_category(
	IC_ID		SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25) UNIQUE,
    Description	TEXT,
    PRIMARY KEY	(IC_ID)
);

CREATE TABLE REF_Instr_model(
	IM_ID		SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25) UNIQUE,
    Description	TEXT,
    PRIMARY KEY (IM_ID)
);

CREATE TABLE REF_Instr_type(
	IT_ID		SMALLINT AUTO_INCREMENT,
    IC_ID		SMALLINT,
    Name		VARCHAR(30) UNIQUE,
    Description	TEXT,
    PRIMARY KEY	(IT_ID)
);

CREATE TABLE Instrumentation(
	Instr_ID	SMALLINT AUTO_INCREMENT,
    IC_ID		SMALLINT,
    IT_ID		SMALLINT,
    IM_ID		SMALLINT,
    Visit_ID	INTEGER,
    Comments	TEXT,
    PRIMARY KEY (Instr_ID)
);

CREATE TABLE REM(
	Instr_ID	SMALLINT,
    Visit_ID	INTEGER,
    Comments	TEXT,
    Freg_RE		NUMERIC(28),
    T_R_SPL		NUMERIC(28),
    Mix_R_SPL	NUMERIC(28),
    MIX_R_SL	NUMERIC(28),
    Tol_R_SPL	NUMERIC(28),
    Tol_R_SL	NUMERIC(28),
    Max_R_SL	NUMERIC(28),
    Max_R_SPL	NUMERIC(28),
    Freg_LE		NUMERIC(28),
    Th_L_SPL	NUMERIC(28),
    Mix_L_SPL	NUMERIC(28),
    Mix_L_SL	NUMERIC(28),
    Tol_L_SPL	NUMERIC(28),
    Tol_L_SL	NUMERIC(28),
    Max_L_SL	NUMERIC(28),
    Max_L_SPL	NUMERIC(28)
);

CREATE TABLE Counseling(
	Visit_ID	INTEGER,
    Type		VARCHAR(1),
    Comments	LONGTEXT
);

CREATE TABLE Visit(
	Visit_ID	INTEGER AUTO_INCREMENT,
    THC			INT NOT NULL,
    Visit_nr	SMALLINT NOT NULL,
    Date		DATETIME NOT NULL,
    Comments	TEXT,
    PRIMARY KEY (Visit_ID)
);

CREATE TABLE Pharmacology(
	Medicament_ID	SMALLINT,
    Visit_ID		INTEGER,
    Dose			NUMERIC(28),
    Duration_mo		NUMERIC(28),
    Comments		LONGTEXT
);

CREATE TABLE Patient(
    THC 		INT AUTO_INCREMENT,
    Country		VARCHAR(30),
    State		VARCHAR(30),
    ZIP		MEDIUMINT,
    WStatus	SMALLINT,
    Occup		VARCHAR(30),
    Surname		VARCHAR(25),
    First_name	VARCHAR(15),
    SSN			VARCHAR(10) UNIQUE,
    DOB			DATE,
    Insurance	VARCHAR(10),
    Tin_background	CHAR(1),
    H_background	CHAR(1),
    T_Ind_comments	TEXT,
    H_Ind_comments	TEXT,
    PRIMARY KEY (THC)
);

CREATE TABLE REF_Medicament(
	Medicament_ID	SMALLINT AUTO_INCREMENT,
    Generic_ID		SMALLINT,
    Chem_ID			SMALLINT,
    Disease_ID		SMALLINT,
    Name			VARCHAR(25) UNIQUE,
    Description		TEXT,
    Usual_dose		NUMERIC(28),
    PRIMARY KEY (Medicament_ID)
);

CREATE TABLE REF_Disease(
	Disease_ID	SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25) UNIQUE,
    Description	TEXT,
    PRIMARY KEY (Disease_ID)
);

CREATE TABLE REF_Generic(
	Generic_ID	SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25) UNIQUE,
    Description	TEXT,
    PRIMARY KEY (Generic_ID)
);

CREATE TABLE REF_CatChem(
	Chem_ID		SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25) UNIQUE,
    Description	TEXT,
    PRIMARY KEY (Chem_ID)
);

CREATE TABLE REF_Occupation(
	Occup_ID 	SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25),
    PRIMARY KEY (Occup_ID)
);

CREATE TABLE REF_WorkS(
	WStatus_ID 	SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25),
    PRIMARY KEY (WStatus_ID)
);

CREATE TABLE REF_State(
	State_ID 	SMALLINT AUTO_INCREMENT,
    Name		LONGTEXT,
    PRIMARY KEY (State_ID)
);

CREATE TABLE REF_Country(
	Country_ID 	SMALLINT AUTO_INCREMENT,
    Name		VARCHAR(25),
    PRIMARY KEY (Country_ID)
);

CREATE TABLE REF_Zip(
	ZIP_ID 		SMALLINT AUTO_INCREMENT, 
    Name		VARCHAR(25),
    PRIMARY KEY (ZIP_ID)
);

CREATE TABLE THI(
	Visit_ID	INTEGER,
    Sc_T		SMALLINT,
    SC_F		SMALLINT,
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

CREATE TABLE Audiological(
	Visit_ID	INTEGER,
    Comments	TEXT,
    R25			NUMERIC(28),
    R50			NUMERIC(28),
    R1			NUMERIC(28),
    R2			NUMERIC(28),
    R3			NUMERIC(28),
    R4			NUMERIC(28),
    R6			NUMERIC(28),
    R8			NUMERIC(28),
    R10			NUMERIC(28),
    R12			NUMERIC(28),
    L25			NUMERIC(28),
    L50			NUMERIC(28),
    L1			NUMERIC(28),
    L2			NUMERIC(28),
    L3			NUMERIC(28),
    L4			NUMERIC(28),
    L6			NUMERIC(28),
    L8			NUMERIC(28),
    L10			NUMERIC(28),
    L12			NUMERIC(28),
    T_PR		NUMERIC(28),
    T_Rm		VARCHAR(6),
    T_LR		NUMERIC(28),
    Th_R		NUMERIC(28),
    T_RLs		NUMERIC(28),
    T_PL		NUMERIC(28),
    T_Lm		VARCHAR(6),
    T_LL		NUMERIC(28),
    Th_L		NUMERIC(28),
    T_Ls		NUMERIC(28),
    WNR			NUMERIC(28),
    WNL			NUMERIC(28),
    MRR			NUMERIC(28),
    MRL			NUMERIC(28),
    MRB			NUMERIC(28),
    MLR			NUMERIC(28),
    MLL			NUMERIC(28),
    MLB			NUMERIC(28),
    MBR			NUMERIC(28),
    M_BL		NUMERIC(28),
    M_BB		NUMERIC(28),
    R_SD		NUMERIC(28),
    L_SD		NUMERIC(28),
    LR50		NUMERIC(28),
    LR1			NUMERIC(28),
    LR2			NUMERIC(28),
    LR3			NUMERIC(28),
    LR4			NUMERIC(28),
    LR6			NUMERIC(28),
    LR8			NUMERIC(28),
    LR12		NUMERIC(28),
    LRTP		NUMERIC(28),
    LL50		NUMERIC(28),
    LL1			NUMERIC(28),
    LL2			NUMERIC(28),
    LL3			NUMERIC(28),
    LL4			NUMERIC(28),
    LL6			NUMERIC(28),
    LL8			NUMERIC(28),
    LL12		NUMERIC(28),
    LLTP		NUMERIC(28)
);

CREATE TABLE Interview(
	Problem_ID	SMALLINT,
    IT_ID		SMALLINT,
    Visit_ID	INTEGER,
    Type		VARCHAR(1),
    Cat			CHAR(1),
    CC			CHAR(1),
    `Aw%T`		SMALLINT,
    `An%T`		SMALLINT,
    T_AN		SMALLINT,
    T_Sv		SMALLINT,
    T_EL		SMALLINT,
    T_ch		CHAR(1),
    On_per		DATE,
    On_prt		DATE,
    On_GS		CHAR(1),
    On_assoc	CHAR(1),
    Ms			CHAR(1),
    `Where`		CHAR(1),
    `Change`	CHAR(1),
    Fluc		CHAR(1),
    Sleep_h		SMALLINT,
    Tin_Concentration	SMALLINT,
    Tin_Sleep	SMALLINT,
    Tin_QRA		SMALLINT,
    Tin_Work	SMALLINT,
    Tin_Rest	SMALLINT,
    Tin_Sport	SMALLINT,
    Tin_Soc		SMALLINT,
    Tin_other	SMALLINT,
    Tin_Bad_days	CHAR(1),
    Tin_Freq	SMALLINT,
    Tin_As_Freq		CHAR(1),
    Tin_As_Bad		CHAR(1),
    Tin_Eff_snd		CHAR(1),
    Tin_How_Ing		CHAR(1),
    Tin_Treatm		LONGTEXT,
    Tin_Why_prob	LONGTEXT,
    Tin_comments	LONGTEXT,
    DST			CHAR(1),
    DST_Phys	CHAR(1),
    DST_Descr	INTEGER,
    DST_Concert	INTEGER,
    DST_shopp	INTEGER,
    DST_Mov		INTEGER,
    DST_Wrk		INTEGER,
    DST_Rest	INTEGER,
    DST_Drv		INTEGER,
    DST_Sport	INTEGER,
    DST_Church	INTEGER,
    DST_House	INTEGER,
    DST_Child	INTEGER,
    DST_Soc		INTEGER,
    DST_Oth		INTEGER,
    DST_Oth_des		TEXT,
    H_Sv		NUMERIC(28),
    H_An		NUMERIC(28),
    H_EL		NUMERIC(28),
    DST_Bad_d	CHAR(1),
    DST_Freq	INTEGER,
    DST_As_Bad	CHAR(1),
    DST_Eff_snd	CHAR(1),
    DST_how_Ing	CHAR(1),
    DST_Prot	CHAR(1),
    `DST%T`		INTEGER,
    DST_when	TEXT,
    DST_treat	TEXT,
    DST_why_prob	TEXT,
    DST_comments	TEXT,
    Hp			CHAR(1),
    HA			CHAR(1),
    HAt			VARCHAR(25),
    HAr			CHAR(1),
    HA_Comments	TEXT,
    `Out`		CHAR(1),
    T_pr		INTEGER,
    H_pr		INTEGER,
    HL_pr		INTEGER,
    Pr			CHAR(1),
    Ret			CHAR(1),
    Recom		TEXT,
    Next_v		NUMERIC(28),
    Next_t		NUMERIC(28),
    Comments	CHAR(10)
);

USE team5;

ALTER TABLE REF_Country
CHANGE Country_ID Country_ID VARCHAR(30);

ALTER TABLE REF_Occupation
CHANGE Occup_ID Occup_ID VARCHAR(30);

ALTER TABLE REF_State
CHANGE State_ID State_ID VARCHAR(30);

ALTER TABLE REF_WorkS
CHANGE WStatus_ID WStatus_ID SMALLINT;

ALTER TABLE REF_Zip
CHANGE ZIP_ID ZIP_ID MEDIUMINT;

ALTER TABLE THI
ADD FOREIGN KEY (Visit_ID) REFERENCES Visit (Visit_ID)
	ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE Patient
CHANGE Insurance Insurance VARCHAR(50);

ALTER TABLE Patient
DROP COLUMN Tin_Background,
DROP COLUMN H_Background;

-- Begin Create PROCEDURE and FUNCTION
DROP PROCEDURE IF EXISTS THIScore;

-- Create the THIScore Procedure
DELIMITER //
CREATE PROCEDURE THIScore(INOUT visitorID INTEGER, OUT score INTEGER, OUT scoreDESC VARCHAR(25))
BEGIN
	SELECT F1 + F2 + E3 + F4 + C5 + E6 + F7 + C8 + F9 + E10 + C11 + F12 + F13 + E14 + F15 + E16 + E17 + F18 + C19 + F20 + E21 + E22 + C23 + F24 + E25 
    INTO score
    FROM THI
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
    FROM Visit
    WHERE `Date` = onDate;
    
RETURN total;
END //
DELIMITER ;

-- Run the VisitorsCount Function
select team5.VisitorsCount('2000-01-01');

-- End Create PROCEDURE and FUNCTION