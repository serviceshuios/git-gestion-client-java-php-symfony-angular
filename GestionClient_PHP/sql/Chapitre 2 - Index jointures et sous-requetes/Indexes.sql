CREATE INDEX Idx_login
ON Client (login(10));

DROP INDEX Idx_login ON Client;

CREATE INDEX Idx_login
ON Client (login(10));

CREATE UNIQUE INDEX Unique_denom
ON Client (nom,prenom);

/*
CREATE FULLTEXT INDEX FT_comm
ON Client (commentaire);
*/

CREATE INDEX Idx_expiration
ON BonDeCommande (date,delais);

/*
CREATE FULLTEXT INDEX FT_comm
ON BonDeCommande (commentaire);
*/

SHOW INDEX FROM Client;
SHOW INDEX FROM BonDeCommande;

