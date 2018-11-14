# afficher 1 si un couple login / mdp existe, 0 sinon

PREPARE author
FROM 'SELECT count(login) FROM Client WHERE login = ? AND password = ?'; 

SET @login = 'admin';
SET @password = MD5('toto');
EXECUTE author USING @login, @password;

# afficher un client donné via son login

PREPARE show_client
FROM 'SELECT * FROM Client WHERE login = ?';

SET @login = 'admin';
EXECUTE show_client USING @login;

# créer une requête préparée pour la création d'un bon de commande

#DEALLOCATE PREPARE add_bdc;

SET @req = 'INSERT INTO BonDeCommande 
(id, client, commentaire, montant, date, delais) 
SELECT 
	bdcId(prenom, nom, COUNT(id) + 1),
	Admin_Bdc.client_login, ?, ?, CURDATE(), ? 
FROM
(
	SELECT BonDeCommande.*, Client.prenom, Client.nom, Client.login AS client_login FROM BonDeCommande 
	RIGHT JOIN Client 
	ON BonDeCommande.client = Client.login 
	WHERE Client.login = ? 
) AS Admin_Bdc';

PREPARE add_bdc
FROM @req;

SET @commentaire = 'petit test de req préparée';
SET @montant = 800;
SET @delais = 10;
SET @clientLogin = 'alf';
EXECUTE add_bdc USING @commentaire, @montant, @delais, @clientLogin;

select * from BonDeCommande;