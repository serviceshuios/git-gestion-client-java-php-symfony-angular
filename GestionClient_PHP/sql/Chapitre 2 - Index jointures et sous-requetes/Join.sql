# récupérer les bons de commande apparetenant à des clients dont le compte a expiré

SELECT BonDeCommande.* FROM BonDeCommande
	INNER JOIN Client
	ON BonDeCommande.client = Client.login
WHERE Client.expiration < CURRENT_TIMESTAMP();

# récupérer la liste des bons de commande par nom de client

SELECT Client.nom AS nom_client, Client.prenom AS prenom_client, BonDeCommande.* FROM Client
LEFT OUTER JOIN BonDeCommande
ON BonDeCommande.client = Client.login;