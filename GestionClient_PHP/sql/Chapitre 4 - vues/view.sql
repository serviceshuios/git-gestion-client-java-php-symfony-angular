# créer une vue présentant les bons de commande avec les login, nom et prenom de leurs propriétaires, ainsi que les clients sans bons de commande

CREATE OR REPLACE VIEW V_BdcWClient
AS SELECT BonDeCommande.id, Client.login AS client_login, Client.nom AS client_nom, Client.prenom AS client_prenom, BonDeCommande.commentaire,
	montant, date, delais
FROM BonDeCommande 
RIGHT JOIN Client 
ON BonDeCommande.client = Client.login ;

DESC V_BdcWClient;
SELECT * FROM V_BdcWClient;

# créer une vue présentant toutes les collonnes de Client plus une colonne indiquant le nombre de bons de commande de chaque client

CREATE OR REPLACE VIEW V_Client_C
AS SELECT DISTINCT Client.*, COUNT(BonDeCommande.id) AS nbrBdc FROM Client
LEFT JOIN BonDeCommande
ON BonDeCommande.client = Client.login
GROUP BY BonDeCommande.client;

DESC V_Client_C;
SELECT * FROM V_Client_C;

# créer une vue affichant les bons de commande avec, en lieu et place de leur date et delais, une date d'expiration

CREATE OR REPLACE VIEW V_Bdc_exp
AS SELECT BonDeCommande.id, BonDeCommande.client, BonDeCommande.commentaire, BonDeCommande.montant,
	ADDDATE(BonDeCommande.date, BonDeCommande.delais) AS expiration
FROM BonDeCommande;

DESC V_Bdc_exp;
SELECT * FROM V_Bdc_exp;

# utiliser ces vues pour :

## afficher uniquement les clients n'ayant pas de bon de commande

SELECT * FROM V_Client_C
WHERE nbrBdc = 0;

## n'afficher que les bons de commande qui arrivent à expiration dans les 5 prochains jours

SELECT * FROM V_Bdc_exp
WHERE expiration > NOW() AND expiration < NOW() + INTERVAL 5 DAY;

## BONUS : reprendre la procédure stockée permettant de créer un bondecommande

DROP PROCEDURE IF EXISTS createBdc;

DELIMITER $$

CREATE PROCEDURE createBdc(IN $commentaire TINYTEXT, IN $montant DECIMAL(6,2), IN $delais SMALLINT, IN $client_login VARCHAR(50))
BEGIN
	INSERT INTO BonDeCommande 
	(id, client, commentaire, montant, date, delais) 
	SELECT 
		bdcId(client_nom, client_prenom, COUNT(id) + 1), client_login, $commentaire, $montant, CURDATE(), $delais 
	FROM V_BdcWClient
	WHERE client_login = $client_login;
END $$

CALL createBdc('Ceci est un nouveau bdc', 78.01, 30, 'loulou');