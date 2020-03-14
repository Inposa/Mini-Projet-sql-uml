package fr.iut.miniprojet.dao;

import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;
import fr.iut.miniprojet.dao.produits.DAOProduit;
import fr.iut.miniprojet.dao.produits.DAOProduitXml;

public class DaoXMLFactory implements AbstractDaoFactory {
	private static DaoXMLFactory instance = null;
	
	public static DaoXMLFactory getInstance() {
		if(instance == null) {
			instance = new DaoXMLFactory();
		}
		return instance;
	}
	
	private DaoXMLFactory() {
	
	}
	
	@Override
	public DAOProduit createDaoProduit() {
		return DAOProduitXml.getInstance();
	}

	@Override
	public DAOCatalogue createDaoCatalogue() {
		//TODO return DAOCatalogueXml.getInstance();
		return null;
	}
	
}
