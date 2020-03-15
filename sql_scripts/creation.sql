/*
DROP TABLE Produits CASCADE CONSTRAINTS;
CREATE TABLE Produits(
	nomProduit VARCHAR(20), prixProduit DECIMAL, nbrStock NUMBER,
	CONSTRAINT pk_nomProduit PRIMARY KEY (nomProduit),
	CONSTRAINT ck_prixProduit CHECK (prixProduit > 0),
	CONSTRAINT ck_nbrStock CHECK (nbrStock >=0)
);


CREATE OR REPLACE PROCEDURE insererProduit(p_nomProduit IN Produits.nomProduit%TYPE, 
				p_prixProduit IN Produits.prixProduit%TYPE, 
				p_nbrStock IN Produits.nbrStock%TYPE) IS
	
	BEGIN
		INSERT INTO Produits(nomProduit, prixProduit, nbrStock)
		VALUES(p_nomProduit, p_prixProduit, p_nbrStock);	
	END;
*/

-- Table Produits2 contenant les informations en plus apportée dans la partie 3
DROP TABLE Produits2 CASCADE CONSTRAINTS;
CREATE TABLE Produits2(
	idProduit NUMBER, nomProduit VARCHAR(20), 
	prixProduit DECIMAL, nbrStock NUMBER,
	nomCatalogue VARCHAR(15),
	CONSTRAINT pk_idProduit PRIMARY KEY (idProduit),
	CONSTRAINT ck_prixProduit2 CHECK (prixProduit > 0),
	CONSTRAINT ck_nbrStock2 CHECK (nbrStock >=0),
	CONSTRAINT fk_nomCatalogue FOREIGN KEY (nomCatalogue) REFERENCES Catalogues(nomCatalogue) ON DELETE CASCADE
);


-- Séquence pour les id des produits
CREATE SEQUENCE incrementIdProduit
START WITH 1 INCREMENT BY 1;

-- Créé/insérer un nouveau produit
CREATE OR REPLACE PROCEDURE insererNouveauProduit(
				p_nomProduit IN Produits2.nomProduit%TYPE, 
				p_prixProduit IN Produits2.prixProduit%TYPE, 
				p_nbrStock IN Produits2.nbrStock%TYPE,
				p_nomCatalogue IN Produits2.nomCatalogue%TYPE) IS
	
	BEGIN
		INSERT INTO Produits2(idProduit, nomProduit, prixProduit, nbrStock, nomCatalogue)
		VALUES(incrementIdProduit.NEXTVAL, p_nomProduit, p_prixProduit, p_nbrStock, p_nomCatalogue);
	END;


-- Suppriemr un produit
CREATE OR REPLACE PROCEDURE deleteProduit(
				p_nomProduit IN Produits2.nomProduit%TYPE, 
				p_nomCatalogue IN Produits2.nomCatalogue%TYPE) IS

	BEGIN
		DELETE FROM Produits2
		WHERE nomProduit = p_nomProduit AND nomCatalogue = p_nomCatalogue;
	END;

-- Mettre à jour les stocks d'un produit
CREATE OR REPLACE PROCEDURE updateProduit(
				p_nbrStock IN Produits2.nbrStock%TYPE,
				p_nomProduit IN Produits2.nomProduit%TYPE, 
				p_nomCatalogue IN Produits2.nomCatalogue%TYPE) IS
	BEGIN
		UPDATE Produits2 
		SET nbrStock = p_nbrStock
		WHERE nomProduit = p_nomProduit 
				AND nomCatalogue = p_nomCatalogue;
	END;

-- Lire un produit d'un catalogue
CREATE OR REPLACE PROCEDURE lireProduit(
				p_nomProduit IN Produits2.nomProduit%TYPE, 
				p_nomCatalogue IN Produits2.nomCatalogue%TYPE) IS
	BEGIN
		SELECT nomProduit, prixProduit, nbrStock
		FROM Produits2
		WHERE nomProduit = p_nomProduit AND nomCatalogue = p_nomCatalogue;
	END;	

-- Vérifier si un nom de produit existe déjà dans le catalogue que l'on souhaite modifier
CREATE OR REPLACE TRIGGER verifDoubleNomProduitCatalogue
	BEFORE INSERT ON Produits2 
	FOR EACH ROW
	DECLARE
		v_number NUMBER;
	BEGIN
		SELECT COUNT(*) INTO v_number
		FROM Produits2
		WHERE nomProduit = :NEW.nomProduit AND nomCatalogue = :NEW.nomCatalogue;
		
		IF v_number > 0 THEN
			RAISE TOO_MANY_ROWS;
		END IF;
	END;



-- Table contenant tous les noms des catalogues, comme un produit appartient à un seul catalogue,
-- le nom du catalogue est présent dans la table Produits
DROP TABLE Catalogues CASCADE CONSTRAINTS;
CREATE TABLE Catalogues(
	nomCatalogue VARCHAR(15),
	CONSTRAINT pk_nomCatalogueNom PRIMARY KEY (nomCatalogue)
);

/*
CREATE OR REPLACE TRIGGER verifDoubleNomCatalogue 
	BEFORE INSERT ON Catalogues
	FOR EACH ROW
	DECLARE
		v_number NUMBER;
	BEGIN
		SELECT COUNT(*) INTO v_number
		FROM Catalogues
		WHERE nomCatalogue = :NEW.nomCatalogue;
		
		IF v_number > 0 THEN
			RAISE TOO_MANY_ROWS;
		END IF;
	END;
*/

-- Supprimer un catalogue
CREATE OR REPLACE PROCEDURE deleteCatalogue(p_nomCatalogue IN Catalogues.nomCatalogue%TYPE) IS
	BEGIN
		DELETE FROM Catalogues
		WHERE nomCatalogue = p_nomCatalogue;
	END;

-- Créer un nouveau catalogue
CREATE OR REPLACE PROCEDURE insererNouveauCatalogue(p_nomCatalogue IN Catalogues.nomCatalogue%TYPE) IS
	BEGIN
		INSERT INTO Catalogues(nomCatalogue)
		VALUES(p_nomCatalogue);
	END;
