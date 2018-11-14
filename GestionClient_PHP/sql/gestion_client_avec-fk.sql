CREATE TABLE IF NOT EXISTS Client (
	login VARCHAR(50) UNIQUE NOT NULL,
	nom VARCHAR(50),
	prenom VARCHAR(50),
	password VARCHAR(32) NOT NULL,
	commentaire TEXT,
	icon VARCHAR(255),
	expiration TIMESTAMP,
	admin TINYINT(1) NOT NULL DEFAULT 0,
	PRIMARY KEY (login)
)ENGINE=InnoDB;

INSERT INTO Client VALUES
('admin','Mace','Noel',MD5('toto'),'Ceci est le compte de l\'administrateur général', 'admin.png', NOW() + INTERVAL 365 DAY,1),
('alf','onzi','alfred',MD5('babibo'),'Compte de alfred, commercial pour la société M2I', 'm2i.png', NOW() + INTERVAL 365 DAY, 0),
('jp','peste','jean',MD5('lemdpdelamort'),'Compte du DBAdmin pour le debugging', 'mysql.png', NOW() + INTERVAL 365 DAY, 1),
('riri','Duck','Richard',MD5('castor'),'Castor junior', 'castor.png', NOW() + INTERVAL 365 DAY, 0),
('fifi','Duck','Figerald',MD5('castor'),'Castor junior', 'castor.png', NOW() + INTERVAL 365 DAY, 0),
('loulou','Duck','Louis',MD5('castor'),'Castor junior', 'castor.png', NOW() + INTERVAL 365 DAY, 0),
('Jacko','Chirack','Jeaques',MD5('fictif'),'Mangez des pommes', 'fr.png', NOW() - INTERVAL 1 DAY, 0),
('pir','Quiroule','Pierre',MD5('aucarré'),'Sysadmin, pour le debugging', 'tux.png', NOW() + INTERVAL 365 DAY, 1);

CREATE TABLE IF NOT EXISTS BonDeCommande (
	id VARCHAR(54) UNIQUE NOT NULL,
	client VARCHAR(50) NOT NULL,
	commentaire TINYTEXT,
	montant DECIMAL(6,2) NOT NULL DEFAULT 0,
	date DATE NOT NULL,
	delais SMALLINT UNSIGNED NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	FOREIGN KEY (client) REFERENCES Client (login)
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
('jchirak_001', 'Jacko', 'De la potion magique', 20, CURDATE(), 365);

CREATE INDEX Idx_login
ON Client (login(10));

CREATE UNIQUE INDEX Unique_denom
ON Client (nom,prenom);

CREATE INDEX Idx_expiration
ON BonDeCommande (date,delais);

SHOW INDEX FROM Client;
SHOW INDEX FROM BonDeCommande;

