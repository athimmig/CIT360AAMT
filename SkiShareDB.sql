USE skishareDB

DROP TABLE IF EXISTS skiResorts;

CREATE TABLE IF NOT EXISTS skiResorts
	( resort_id				INT UNSIGNED		PRIMARY KEY AUTO_INCREMENT
	, resort_name			VARCHAR(50)				NOT NULL
	, lattitude 			FLOAT(20,8)			NOT NULL
	, longitude				FLOAT(20,8)			NOT NULL) ENGINE=InnoDB;

-- Load the data from a file, don't forget the \n after the \r on Windows or it won't work.
LOAD DATA LOCAL INFILE '/home/hyphrend/workspace/CIT360AAMT/SkiResortsCSV.csv'
INTO TABLE skiResorts
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
ESCAPED BY '\\'
LINES TERMINATED BY '\n';

DROP TABLE IF EXISTS resort_reviews;

CREATE TABLE IF NOT EXISTS resort_reviews
	( review_id				INT UNSIGNED		PRIMARY KEY AUTO_INCREMENT
	, resort_id			INT UNSIGNED				NOT NULL
	, weather 			INT UNSIGNED				NOT NULL
	, conditions			INT UNSIGNED				NOT NULL
	, crowd				INT UNSIGNED				NOT NULL) ENGINE=InnoDB;

ALTER TABLE resort_reviews
ADD CONSTRAINT fk_resort_id
FOREIGN KEY (resort_id) REFERENCES skiResorts(resort_id)
ON UPDATE CASCADE
ON DELETE CASCADE;

\. zip_codes.sql;
