USE skishareDB

DROP TABLE IF EXISTS resort_reviews;
DROP TABLE IF EXISTS skiResorts;

CREATE TABLE IF NOT EXISTS skiResorts
	( id				INT UNSIGNED		AUTO_INCREMENT
	, resort_name			VARCHAR(50)				NOT NULL
	, latitude 			FLOAT(20,8)			NOT NULL
	, longitude				FLOAT(20,8)			NOT NULL
	, PRIMARY KEY (id)) ENGINE=InnoDB;

-- Load the data from a file, don't forget the \n after the \r on Windows or it won't work.

LOAD DATA LOCAL INFILE 'SkiResortsCSV.csv'

INTO TABLE skiResorts
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
ESCAPED BY '\\'
LINES TERMINATED BY '\n';

CREATE TABLE IF NOT EXISTS resort_reviews
	( id				INT UNSIGNED		 AUTO_INCREMENT
	, resort_id			INT UNSIGNED				NOT NULL
	, review_date			TIMESTAMP				NOT NULL
	, weather 			INT UNSIGNED				NOT NULL
	, conditions			INT UNSIGNED				NOT NULL
	, crowd				INT UNSIGNED				NOT NULL
	, PRIMARY KEY (id)) ENGINE=InnoDB;

ALTER TABLE resort_reviews
ADD CONSTRAINT fk_resort_id
FOREIGN KEY (resort_id) REFERENCES skiResorts(id)
ON UPDATE CASCADE
ON DELETE CASCADE;

\. zip_codes.sql
