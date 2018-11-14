# Lancer une transaction permettant de créer un nouveau client, puis, après un jalon, un bon de commande associé

DELETE FROM Client WHERE login = 'new';

START TRANSACTION;

INSERT INTO Client VALUES ('new', 'Jean', 'Pierre', MD5('chamalow'), 'un client ajouté via transaction', 'new.png', NOW() + INTERVAL 1 YEAR, 0);

SAVEPOINT client_cree;

INSERT INTO BonDeCommande
	(id, client, commentaire, montant, date, delais)
SELECT 
	REPLACE(REPLACE(LOWER(CONCAT(left(prenom, 1), nom, '_00', COUNT(id) + 1)), 'é', 'e'), 'ë', 'e'),
	'new', 'un bdc par transaction', 800, CURDATE(), 30
FROM
(
	SELECT BonDeCommande.*, Client.prenom, Client.nom, Client.login AS client_login FROM BonDeCommande
	RIGHT JOIN Client
	ON BonDeCommande.client = Client.login
	WHERE Client.login = 'new'
) AS Admin_Bdc;

COMMIT;