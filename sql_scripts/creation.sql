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
	