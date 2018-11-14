CREATE TABLE IF NOT EXISTS BonDeCommande (
	id VARCHAR(54) UNIQUE NOT NULL,
	client VARCHAR(50) NOT NULL,
	commentaire TINYTEXT,
	montant DECIMAL(6,2) NOT NULL DEFAULT 0,
	date DATE NOT NULL,
	delais SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (client) REFERENCES Client(login)
)ENGINE='InnoDB';


INSERT INTO BonDeCommande VALUES
('nmace_001', 'admin', 'Premier bdc de Noel', 8242.15, CURDATE(), 30),
('nmace_002', 'admin', 'Second bdc de Noel', 42.30, CURDATE(), 30),
('rduck_001', 'riri', 'On a besoin de pèles et de sauts', 200, CURDATE(), 30),
('rduck_002', 'riri', 'Des sacs et des tentes', 100, CURDATE(), 30),
('rduck_003', 'riri', 'Ils sont où les livres ?', 400, CURDATE(), 10),
('rduck_004', 'riri', 'Des cordes', 200, CURDATE(), 30),
('fduck_001', 'fifi', 'Une corde à sauter', 8000, CURDATE(), 60),
('fduck_002', 'fifi', 'Un camion qui roule', 200, CURDATE(), 30),
('lduck_001', 'loulou', 'Un vélo qui roule', 200, CURDATE(), 30),
('lduck_002', 'loulou', 'Une pierre qui roule', 200, CURDATE(), 30),
('pquiroule_001', 'pir', 'On m\'a demandé ?', 2, CURDATE(), 3),
('jchirak_001', 'Chirack', 'De la potion magique', 20, CURDATE(), 365);