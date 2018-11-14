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
('admin','Macé','Noël',MD5('toto'),'Ceci est le compte de l\'administrateur général', 'admin.png', NOW() + INTERVAL 365 DAY,1),
('alf','onzi','alfred',MD5('babibo'),'Compte de alfred, commercial pour la société M2I', 'm2i.png', NOW() + INTERVAL 365 DAY, 0),
('jp','peste','jean',MD5('lemdpdelamort'),'Compte du DBAdmin pour le debugging', 'mysql.png', NOW() + INTERVAL 365 DAY, 1),
('riri','Duck','Richard',MD5('castor'),'Castor junior', 'castor.png', NOW() + INTERVAL 365 DAY, 0),
('fifi','Duck','Figerald',MD5('castor'),'Castor junior', 'castor.png', NOW() + INTERVAL 365 DAY, 0),
('loulou','Duck','Louis',MD5('castor'),'Castor junior', 'castor.png', NOW() + INTERVAL 365 DAY, 0),
('Jacko','Chirack','Jeaques',MD5('fictif'),'Mangez des pommes', 'fr.png', NOW() - INTERVAL 1 DAY, 0),
('pir','Quiroule','Pierre',MD5('aucarré'),'Sysadmin, pour le debugging', 'tux.png', NOW() + INTERVAL 365 DAY, 1);