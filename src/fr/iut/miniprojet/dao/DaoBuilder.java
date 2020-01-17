package fr.iut.miniprojet.dao;


/**
 * 
 * Rediriger vers un DAO en particulier en fonction de la configuration 
 * souhaitée (Oracle ou xml) 
 */
public interface DaoBuilder {
	
	public DaoBuilder getDaoProduit();
	public DaoBuilder getDaoCatalogue();
	
	
	/*private static String CONFIG = "oracle";
	
	public static DaoBuilder getDaoBuilder() {
		//Lecture de la configuration et action en fonction de celle-ci
		DaoBuilder builder = null;
		
		switch (DaoBuilder.CONFIG) {
		case "oracle":
			builder = new DaoBuilderOracle();
			break;
			
		case "xml":
			builder = new DaoBuilderXML();
			break;
			
		default:
			builder = new DaoBuilderOracle();
			break;
		}
		return builder;
		
	}*/
	

	
}
