# afficher le nom de l'ancienne foreign key
SELECT * FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_NAME = 'BonDeCommande';

# la supprimer
ALTER TABLE BonDeCommande
DROP FOREIGN KEY `BonDeCommande_ibfk_1`;

# ajouter la nouvelle fk
ALTER TABLE BonDeCommande
ADD CONSTRAINT `fk_client_login` FOREIGN KEY (client)
REFERENCES Client (login)
ON DELETE CASCADE
ON UPDATE CASCADE;

# vérifier le résultat
SELECT * FROM information_schema.TABLE_CONSTRAINTS
WHERE table_name = 'BonDeCommande';

# ou encore

SHOW CREATE TABLE BonDeCommande;


