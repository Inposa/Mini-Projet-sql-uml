package fr.iut.miniprojet.dao;

import java.sql.*;

import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;
import fr.iut.miniprojet.dao.catalogues.DAOCatalogueOracle;
import fr.iut.miniprojet.dao.produits.DAOProduit;
import fr.iut.miniprojet.dao.produits.DAOProduitOracle;
import fr.iut.miniprojet.entities.I_Catalogue;

public class DaoOracleFactory implements AbstractDaoFactory {
	
	private static DaoOracleFactory instance = null;
	private Connection cn;
	
	public static DaoOracleFactory getInstance() {
		if(instance == null) {
			instance = new DaoOracleFactory();
		}
		return instance;
	}
	
	private DaoOracleFactory() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut","pechh","OracleHippo");	
		}
		catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} 
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	

	@Override
	public DAOProduit createDaoProduit(I_Catalogue catalogue) {
		return new DAOProduitOracle(this.cn, catalogue);
	}

	@Override
	public DAOCatalogue createDaoCatalogue() {
		return new DAOCatalogueOracle(this.cn);
	}

}
