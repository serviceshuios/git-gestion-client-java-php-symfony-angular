# afficher tous les commentaires de tous les bons de commandes et clients
# en ajoutant une collonne intitulée 'ref' indiquant si il s'agit d'un bdc ou d'un client
# ainsi qu'une collonne affichant le login du client associé pour chacun

SELECT 'bdc' AS ref, client, commentaire FROM BonDeCommande
union
SELECT 'client', login, commentaire FROM Client;

# trier le résultat précédent par nom de client

SELECT 'bdc' AS ref, client, commentaire FROM BonDeCommande
union
SELECT 'client', login, commentaire FROM Client
order by client;

# enregistrer ce résultat dans une nouvelle table MyISAM 'Comments_backup'

CREATE TABLE Comments_backup (
	id SMALLINT UNIQUE NOT NULL auto_increment,
	ref ENUM('bdc', 'client') NOT NULL,
	client VARCHAR(50) NOT NULL,
	commentaire TEXT,
	PRIMARY KEY (id)
)ENGINE=MyISAM;

INSERT INTO Comments_backup (ref, client, commentaire)
SELECT 'bdc' AS ref, client, commentaire FROM BonDeCommande
union
SELECT 'client', login, commentaire FROM Client;

# afficher les logins et id des bons de commande et comptes clients expirés

SELECT id FROM (
	SELECT login as id, expiration FROM Client
	union all
	SELECT id, ADDDATE(date, delais) FROM BonDeCommande
	order by expiration
) AS Expiration_all
WHERE expiration < NOW();