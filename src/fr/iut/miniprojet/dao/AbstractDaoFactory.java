package fr.iut.miniprojet.dao;

import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;
import fr.iut.miniprojet.dao.produits.DAOProduit;

public interface  AbstractDaoFactory {
	public abstract DAOProduit createDaoProduit();
	public abstract DAOCatalogue createDaoCatalogue();
	
}
