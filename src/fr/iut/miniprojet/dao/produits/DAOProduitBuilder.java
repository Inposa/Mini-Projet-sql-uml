package fr.iut.miniprojet.dao.produits;

import fr.iut.miniprojet.dao.DaoBuilder;

//Singleton
public class DAOProduitBuilder {
	
	private static DAOProduitBuilder instance = null;
	private static DAOProduit daoInstance = null;
	
	protected DAOProduitBuilder() {}
	
	public static DAOProduitBuilder getInstance() {
		if(DAOProduitBuilder.instance == null) {
			DAOProduitBuilder.instance = new DAOProduitBuilder();
		}
		return DAOProduitBuilder.instance;
	}
	
	public DAOProduit createDAOProduit(String daoSource) {
		if(DAOProduitBuilder.daoInstance == null) {
			switch (daoSource) {
			case "oracle":
				DAOProduitBuilder.daoInstance = DAOProduitOracle.getInstance();
				break;
			case "xml":
				DAOProduitBuilder.daoInstance = DAOProduitXml.getInstance();
				break;
			}
		}
		return DAOProduitBuilder.daoInstance;
	}
	
	//On retourne l'instance 
	public DAOProduit createDAOOracle() {
		return DAOProduitBuilder.daoInstance = DAOProduitOracle.getInstance();
	}
	
	public DAOProduit createDAOXml() {
		return null;
	}
	
}
