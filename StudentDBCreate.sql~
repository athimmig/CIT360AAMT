USE studentdb

DROP TABLE IF EXISTS word_bank;

CREATE TABLE IF NOT EXISTS word_bank
	( word_id		INT UNSIGNED		PRIMARY KEY AUTO_INCREMENT
	, word 			VARCHAR(20)			NOT NULL
	, rhyme_type	VARCHAR(5) 			NOT NULL) ENGINE=InnoDB;

-- Load the data from a file, don't forget the \n after the \r on Windows or it won't work.
LOAD DATA INFILE 'C:/Data/mysql/Rhymes.csv'
INTO TABLE word_bank
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
ESCAPED BY '\\'
LINES TERMINATED BY '\r\n';
