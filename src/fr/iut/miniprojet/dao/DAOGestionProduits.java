package fr.iut.miniprojet.dao;

import java.sql.*;

import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

public class DAOGestionProduits {
	private Connection cn;
	private PreparedStatement pst;

	private ResultSet res;


	/**
	 * Connexion à la base de données 
	 */
	public DAOGestionProduits() {
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

	public void insertionProduit(Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits(nomProduit, prixProduit, nbrProduit) "
					+ "VALUES(?,?,?)");

			statement.setString(1, produit.getNom());
			statement.setDouble(2, produit.getPrixUnitaireHT());
			statement.setInt(3, produit.getQuantite());

			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	public void insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits(nomProduit, prixProduit, nbrProduit) "
					+ "VALUES(?,?,?)");

			statement.setString(1, nomProduit);
			statement.setDouble(2, prixProduit);
			statement.setInt(3, qteProduit);

			statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	public void deleteProduit(String nomProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");

			statement.setString(1, nomProduit);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	public void deleteProduit(Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");

			statement.setString(1, produit.getNom());
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Update la quantité d'un produit lorsqu'il est modifié
	 */
	public void updateQuantiteProduit(Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("UPDATE Produits SET nbrStock = ? WHERE nomProduit = ?");

			statement.setInt(1, produit.getQuantite());
			statement.setString(2, produit.getNom());
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());		
		}
	}

}
