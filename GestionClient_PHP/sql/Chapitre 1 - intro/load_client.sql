LOAD DATA LOCAL INFILE 'Client.csv'
INTO TABLE BonDeCommande
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;