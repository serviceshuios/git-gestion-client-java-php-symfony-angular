SELECT * FROM Client
WHERE login='admin' AND password='f71dbe52628a3f83a77ab494817525c6' AND expiration < NOW();

SELECT * FROM Client
WHERE nom LIKE 'Mac%';

SELECT ADDDATE(date, delais) AS 'exp' FROM BonDeCommande
WHERE 'exp' + 0 < CURDATE();

SELECT * FROM BonDeCommande
WHERE MATCH(commentaire)
AGAINST ('urgent');

SELECT * from BonDeCommande WHERE MATCH(commentaire) AGAINST ('corde*');

SELECT * from BonDeCommande WHERE MATCH(commentaire) AGAINST ('roule');

SELECT *, MATCH(commentaire) AGAINST ('corde* à sauter' IN BOOLEAN MODE) AS 'Match'
FROM BonDeCommande;
