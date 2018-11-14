# afficher l'administateur ayant la plus ancienne date d'expiration

SELECT MIN(expiration) FROM (
	SELECT * FROM Client
	WHERE admin=1
) AS Admin_clients;

# afficher le plus petit montant des bons de commande des admins

SELECT MIN(montant) FROM (
	SELECT BonDeCommande.* FROM BonDeCommande
		INNER JOIN Client
		ON BonDeCommande.client = Client.login
	Where admin=1
) AS Bdc_admins ;

# afficher les bons de commande de tous les administrateurs dont le compte n'a pas expiré, par une sous-requête dans la condition

SELECT * FROM BonDeCommande
WHERE client IN (
	SELECT login FROM Client 
	WHERE admin=1 AND expiration + 0 < NOW()
);

# récupérer le login et la date d'expiration du compte client ayant la date d'expiration la plus ancienne, via une sous-requête dans la condition

SELECT login, expiration FROM Client
WHERE expiration = (
	SELECT min(expiration) FROM Client
);

# afficher les bons de commande si il existe un administrateur dont le compte n'a pas expiré

SELECT * FROM BonDeCommande
WHERE EXISTS (
	SELECT * FROM Client
	WHERE admin=1 AND expiration > NOW()
);

# Créer un nouveau bon de commande pour 'Noël Macé' avec les informations suivantes :
# 'nmace_00' + nombre de bdc de cet utilisateur + 1, login de l'utilisateur, 'un test de insert par sous-requête', 10, date courante, 30

INSERT INTO BonDeCommande
	(id, client, commentaire, montant, date, delais)
SELECT 
	CONCAT('nmace_00', COUNT(id) + 1),
	Admin_Bdc.client_login AS client,
	'un test de insert par sr',
	10, CURDATE(), 30
FROM
(
	SELECT BonDeCommande.*, Client.login AS client_login FROM BonDeCommande
	INNER JOIN Client
	ON BonDeCommande.client = Client.login
	WHERE Client.nom = 'Macé' AND Client.prenom = 'Noël'
) AS Admin_Bdc;

# Bonus : amélioration

INSERT INTO BonDeCommande
	(id, client, commentaire, montant, date, delais)
SELECT 
	REPLACE(REPLACE(LOWER(CONCAT(left(prenom, 1), nom, '_00', COUNT(id) + 1)), 'é', 'e'), 'ë', 'e'),
	Admin_Bdc.client_login AS client,
	'un nouveau test de insert par sr',
	10, CURDATE(), 30
FROM
(
	SELECT BonDeCommande.*, Client.prenom, Client.nom, Client.login AS client_login FROM BonDeCommande
	INNER JOIN Client
	ON BonDeCommande.client = Client.login
	WHERE Client.nom = 'Macé' AND Client.prenom = 'Noël'
) AS Admin_Bdc;

# modifier le commentaire du bon de commande de noel mace de plus grand id

UPDATE BonDeCommande SET commentaire = 'Nouveau commentaire'
WHERE client = (
		SELECT login FROM Client
		WHERE Client.nom = 'Macé' AND Client.prenom = 'Noël'
	)
ORDER BY id DESC
LIMIT 1;

# BONUS : mettre à jour les références 'client' des bons de commandes de riri

UPDATE BonDeCommande SET client = 'riri'
WHERE id LIKE (
	SELECT CONCAT(LEFT(Client.prenom, 1), Client.nom, '%') FROM Client
	WHERE Client.login = 'riri'
);
