package fr.iut.miniprojet.dao;

import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;
import fr.iut.miniprojet.dao.produits.DAOProduit;
import fr.iut.miniprojet.entities.I_Catalogue;

public interface  AbstractDaoFactory {
	public abstract DAOProduit createDaoProduit(I_Catalogue catalogue);
	public abstract DAOCatalogue createDaoCatalogue();
	
}
