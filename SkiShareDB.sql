USE skishareDB

DROP TABLE IF EXISTS skiResorts;

CREATE TABLE IF NOT EXISTS skiResorts
	( resort_id				INT UNSIGNED		PRIMARY KEY AUTO_INCREMENT
	, resort_name			VARCHAR(50)				NOT NULL
	, lattitude 			DECIMAL(9,6)			NOT NULL
	, logitude				DECIMAL(9,6)			NOT NULL) ENGINE=InnoDB;

-- Load the data from a file, don't forget the \n after the \r on Windows or it won't work.
LOAD DATA LOCAL INFILE '/Users/arnoldmutariswa/Desktop/class/CIT360AAMT/SkiResortsCSV.csv'
INTO TABLE skiResorts
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
ESCAPED BY '\\'
LINES TERMINATED BY '\n';
