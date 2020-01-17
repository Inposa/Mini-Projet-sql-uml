package fr.iut.miniprojet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import fr.iut.miniprojet.entities.I_Produit;

public class DaoBuilderOracle implements DaoBuilder {
	private Connection cn;

	
	public DaoBuilderOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		} 

		try {
			// Instanciation de la connexion 
			// connexion depuis le réseau IUT : jdbc:oracle:thin:@gloin:1521:iut
			this.cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut","pechh","SuperMario64");
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public I_DAOProduit getDAOProduit() {
		return new DAOProduits();
	}

	@Override
	public DaoBuilder getDaoProduit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaoBuilder getDaoCatalogue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
