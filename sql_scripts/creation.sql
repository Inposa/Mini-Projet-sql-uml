DROP TABLE Produits CASCADE CONSTRAINTS;
CREATE TABLE Produits(
	nomProduit VARCHAR(20), prixProduit DECIMAL, nbrStock NUMBER,
	CONSTRAINT pk_nomProduit PRIMARY KEY (nomProduit),
	CONSTRAINT ck_prixProduit CHECK (prixProduit > 0),
	CONSTRAINT ck_nbrStock CHECK (nbrStock >=0)
	)
