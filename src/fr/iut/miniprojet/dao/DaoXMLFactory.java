package fr.iut.miniprojet.dao;

import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;
import fr.iut.miniprojet.dao.produits.DAOProduit;
import fr.iut.miniprojet.dao.produits.DAOProduitXml;
import fr.iut.miniprojet.entities.I_Catalogue;

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
	public DAOProduit createDaoProduit(I_Catalogue catalogue) {
		//TODO
		return DAOProduitXml.getInstance();
	}

	@Override
	public DAOCatalogue createDaoCatalogue() {
		//TODO return DAOCatalogueXml.getInstance();
		return null;
	}
	
}
