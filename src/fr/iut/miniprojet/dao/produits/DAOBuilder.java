package fr.iut.miniprojet.dao.produits;

//Singleton
public class DAOBuilder {
	
	private static DAOBuilder instance = null;
	private static DAOProduit daoInstance = null;
	
	protected DAOBuilder() {}
	
	public static DAOBuilder getInstance() {
		if(DAOBuilder.instance == null) {
			DAOBuilder.instance = new DAOBuilder();
		}
		return DAOBuilder.instance;
	}
	
	//On retourne l'instance 
	public DAOProduit createDAOOracle() {
		return DAOBuilder.daoInstance = DAOProduitOracle.getInstance();
	}
	
	public DAOProduit createDAOXml() {
		return null;
	}
	
}
