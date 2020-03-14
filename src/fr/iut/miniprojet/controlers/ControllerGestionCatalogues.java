package fr.iut.miniprojet.controlers;

import fr.iut.miniprojet.dao.DaoOracleFactory;
import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;

public class ControllerGestionCatalogues {
	private DAOCatalogue daoCatalogue;
	
	public ControllerGestionCatalogues() {
		//this.daoCatalogue = DaoOracleFactory.getInstance().createDaoCatalogue();
	}
	
	public String[] getCatalogues() {
		//TODO Retourner tous les noms de catalogues
		return null;
	}
	
}
