select *
INTO OUTFILE "/tmp/zip_codes.csv"
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
ESCAPED BY '\\'
FROM zip_codes;