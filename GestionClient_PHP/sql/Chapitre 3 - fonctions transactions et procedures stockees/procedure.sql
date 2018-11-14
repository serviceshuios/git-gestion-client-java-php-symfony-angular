# reprendre les requêtes préparées précédentes, et les écrire comme procédures stockées

SELECT 'test d\'authentification' AS '=== MESSAGE ===';

## afficher 1 si un couple login / mdp existe, 0 sinon
DROP PROCEDURE IF EXISTS auth;

CREATE PROCEDURE auth(IN $login VARCHAR(50), IN $password CHAR(32))
SELECT login FROM Client WHERE login = $login AND password = $password;

CALL auth('admin', MD5('toto'));

## afficher un client donné via son login

SELECT 'récupérer un client via son login' AS '=== MESSAGE ===';

DROP PROCEDURE IF EXISTS getClientByLogin;


CREATE PROCEDURE getClientByLogin(IN $login VARCHAR(50))
SELECT * FROM Client WHERE login = $login;

CALL getClientByLogin('admin');

## créer une requête préparée pour la création d'un bon de commande

SELECT 'création d\'un bon de commande' AS '=== MESSAGE ===';

DROP PROCEDURE IF EXISTS createBdc;

DELIMITER $$

CREATE PROCEDURE createBdc(IN $commentaire TINYTEXT, IN $montant DECIMAL(6,2), IN $delais SMALLINT, IN $client_login VARCHAR(50))
BEGIN
	INSERT INTO BonDeCommande 
	(id, client, commentaire, montant, date, delais) 
	SELECT 
		bdcId(nom, prenom, COUNT(id) + 1),
		Admin_Bdc.client_login, $commentaire, $montant, CURDATE(), $delais 
	FROM
	(
		SELECT BonDeCommande.*, Client.prenom, Client.nom, Client.login AS client_login FROM BonDeCommande 
		RIGHT JOIN Client 
		ON BonDeCommande.client = Client.login 
		WHERE Client.login = $client_login
	) AS Admin_Bdc;
END $$

CALL createBdc('Un bon de commande par procédure', 42.006, 30, 'admin') $$

# récuper la liste des bons de commande par login de client

SELECT 'récupération des bons de commande par client' AS '=== MESSAGE ===' $$

DROP PROCEDURE IF EXISTS getBdcByClient $$

CREATE PROCEDURE getBdcByClient(IN $client_login VARCHAR(50))
begin
	SELECT * FROM BonDeCommande
	WHERE client = $client_login ;
end $$

CALL getBdcByClient('admin') $$

# faire de même, mais en vérifiant le mot de passe du client en question

SELECT 'avec verif du mdp' AS '=== MESSAGE ===' $$

DROP PROCEDURE IF EXISTS getBdcByClient_auth $$

CREATE PROCEDURE getBdcByClient_auth(IN $client_login VARCHAR(50), IN $password CHAR(32))
begin
	SELECT BonDeCommande.* FROM BonDeCommande
	INNER JOIN Client
	ON BonDeCommande.client = Client.login
	WHERE Client.password = $password
	AND Client.login = $client_login;
end $$

CALL getBdcByClient_auth('admin', MD5('toto')) $$

# Récupérer la liste complète des clients ssi un couple login / mdp valide et correspondant à un compte admin est délivré

SELECT 'liste complète des clients avec auth' $$

DROP PROCEDURE IF EXISTS getClients_ifAdmin $$

CREATE PROCEDURE getClients_ifAdmin(IN $login VARCHAR(50), IN $password CHAR(32))
begin
	SELECT * FROM Client
	WHERE exists (SELECT login FROM Client WHERE login = $login AND password = $password AND admin = 1);
end $$

CALL getClients_ifAdmin('admin', MD5('toto')) $$

# calculer la date d'expiration d'un bon de commande

SELECT 'date d\'expiration d\'un bon de commande' AS '=== MESSAGE ===' $$

DROP PROCEDURE IF EXISTS getBdcExpDate $$

CREATE PROCEDURE getBdcExpDate(IN $id VARCHAR(54), OUT $expiration DATETIME)
begin
	SELECT ADDDATE(date, delais) INTO $expiration
	FROM BonDeCommande
	WHERE id = $id;
end $$


CALL getBdcExpDate('nmace_001', @exp) $$

SELECT @exp $$

# récupérer l'id du dernier Bdc d'un utilisateur

SELECT 'id du dernier bdc d\'un utilisateur' AS '=== MESSAGE ===' $$

DROP PROCEDURE IF EXISTS getLastBdcId $$

CREATE PROCEDURE getLastBdcId(IN $login VARCHAR(50), OUT $id VARCHAR(54))
begin
	SELECT id INTO $id
	FROM Client
	INNER JOIN BonDeCommande
	ON BonDeCommande.client = Client.login
	WHERE BonDeCommande.client = $login
	ORDER BY date DESC LIMIT 1;
end $$

CALL getLastBdcId('admin', @id) $$

SELECT @id $$

DELIMITER ;