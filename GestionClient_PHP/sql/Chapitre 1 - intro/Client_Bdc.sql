CREATE TABLE IF NOT EXISTS Client_Bdc_asso (
	bdc VARCHAR(54) UNIQUE NOT NULL,
	client VARCHAR(50) NOT NULL,
	FOREIGN KEY (bdc)
		REFERENCES BonDeCommande(id),
	FOREIGN KEY (client)
		REFERENCES Client(login)
)ENGINE=InnoDB;

INSERT INTO Client VALUES
	('nmace_001',admin),
	('nmace_002',admin),
	('nmace_003',admin),
	('rduck_001',riri),
	('rduck_002',riri),
	('rduck_003',riri),
	('rduck_004',riri),
	('fduck_001',fifi),
	('fduck_002',fifi),
	('lduck_001',loulou),
	('lduck_002',loulou),
	('pquiroule_001',pir),
	('jchirak_001',Chirack);