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
	
	
DROP TABLE Catalogues CASCADE CONSTRAINTS;

CREATE TABLE Catalogues(
	idCatalogue NUMBER,
	nomCatalogue VARCHAR(15),
	nbrProduits NUMBER,
	CONSTRAINT pk_idCatalogue PRIMARY KEY (idCatalogue),
	CONSTRAINT ck_nbrProduits CHECK (nbrProduits >= 0)
);


CREATE TABLE Produits2(
	idProduit NUMBER, nomProduit VARCHAR(20), 
	prixProduit DECIMAL, nbrStock NUMBER,
	idCatalogue NUMBER,
	CONSTRAINT pk_idProduit PRIMARY KEY (idProduit),
	CONSTRAINT ck_prixProduit2 CHECK (prixProduit > 0),
	CONSTRAINT ck_nbrStock2 CHECK (nbrStock >=0),
	CONSTRAINT fk_idCatalogue FOREIGN KEY (idCatalogue) REFERENCES Catalogues(idCatalogue)
);

CREATE SEQUENCE incrementIdProduit
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE insererNouveauProduit(
				p_nomProduit IN Produits2.nomProduit%TYPE, 
				p_prixProduit IN Produits2.prixProduit%TYPE, 
				p_nbrStock IN Produits2.nbrStock%TYPE,
				p_idCatalogue IN Produits2.idCatalogue%TYPE) IS
	
	BEGIN
		INSERT INTO Produits2(idProduit, nomProduit, prixProduit, nbrStock, idCatalogue)
		VALUES(incrementIdProduit.NEXTVAL, p_nomProduit, p_prixProduit, p_nbrStock, p_idCatalogue);
		
		UPDATE Catalogues
		SET nbrProduits = nbrProduits + 1
		WHERE idCatalogue = p_idCatalogue;
	END;


CREATE OR REPLACE PROCEDURE deleteProduit(
				p_nomProduit IN Produits2.nomProduit%TYPE, 
				p_idCatalogue IN Produits2.idCatalogue%TYPE) IS

	BEGIN
		DELETE FROM Produits2
		WHERE nomProduit = p_nomProduit AND idCatalogue = p_idCatalogue;
		
		UPDATE Catalogues
		SET nbrProduits = nbrProduits - 1
		WHERE idCatalogue = p_idCatalogue;
	END;



CREATE OR REPLACE TRIGGER verifDoubleNomProduitCatalogue
	BEFORE INSERT ON Produits 
	FOR EACH ROW
	DECLARE
		v_number NUMBER;
	BEGIN
		SELECT COUNT(*) INTO v_number
		FROM Produits2
		WHERE nomProduit = :NEW.nomProduit AND nomCatalogue = :NEW.idCatalogue;
		
		IF v_number > 0 THEN
			RAISE TOO_MANY_ROWS;
		END IF;
	END;

CREATE SEQUENCE incrementIdCatalogue
START WITH 1 INCREMENT BY 1;


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


CREATE OR REPLACE PROCEDURE insererNouveauCatalogue(p_nomCatalogue IN Catalogues.nomCatalogue%TYPE) IS
	BEGIN
		INSERT INTO Catalogues(idCatalogue, nomCatalogue, nbrProduits)
		VALUES(incrementIdCatalogue.NEXTVAL, p_nomCatalogue, 0);
	END;
