package fr.iut.miniprojet.dao;

import java.sql.*;

import fr.iut.miniprojet.entities.I_Produit;

public class DAOProduits {
	private Connection cn;
	private PreparedStatement pst;

	private ResultSet res;

	public DAOProduits() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} 
		
		try {
			//Instanciation de la connexion 
			this.cn = DriverManager.getConnection("jdbc:oracle:thin:@gloin:1521:iut","pechh","SuperMario64");

		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean insertProduit(I_Produit p) {
		String sql = "INSERT INTO Produits(nom,prix,quantite) VALUES(?,?,?)";
		
		
		return false;
		
	}
	
}
