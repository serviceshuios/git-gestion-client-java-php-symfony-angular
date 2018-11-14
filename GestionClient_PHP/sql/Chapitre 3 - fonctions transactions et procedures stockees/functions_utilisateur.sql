# créer une fonction 'replace_special_chars' permettant de remplacer les caractères "spéciaux" français par une lettre non accentuée

CREATE FUNCTION replace_special_chars (str VARCHAR(255))
RETURNS VARCHAR(255) 
RETURN REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(str, 'é', 'e'), 'è', 'e'), 'à', 'a'), 'ë', 'e'), 'ç', 'c'), 'ê', 'e');

# créer une fonction 'normalise' permettant de réaliser l'opération précédenter ET de passer la chaine en minuscule

CREATE FUNCTION normalise (str VARCHAR(255))
RETURNS VARCHAR(255)
RETURN lower(replace_special_chars (str));

# créer une fonction bdcId permettant de générer un identifiant de type 'première lettre du premier argument' + 'second argument' + '_00' + 'un chiffre'

CREATE FUNCTION bdcId (nom VARCHAR(50), prenom VARCHAR(50), i INT)
RETURNS VARCHAR(54)
RETURN normalise(CONCAT(LEFT(prenom, 1), nom, '_00', i));

#### autre version

DROP FUNCTION bdcId;
DROP FUNCTION replace_special_chars;
DROP FUNCTION normalise;

DELIMITER $$

CREATE FUNCTION bdcId (nom VARCHAR(50), prenom VARCHAR(50), id INT)
RETURNS VARCHAR(54)
BEGIN

	DECLARE rt VARCHAR(54);

	SET rt = CONCAT(LEFT(prenom, 1), nom, '_00', id);
	SET rt = lower(rt);
	RETURN REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(rt, 'é', 'e'), 'è', 'e'), 'à', 'a'), 'ë', 'e'), 'ç', 'c'), 'ê', 'e');

END$$

DELIMITER ;

SELECT bdcId(nom, prenom, 5) FROM Client WHERE login = 'admin';
