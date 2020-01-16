package fr.iut.miniprojet.dao;

import java.sql.*;
import java.util.ArrayList;

import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

public class DAOProduits implements DAO {
	private Connection cn;

	/**
	 * Connexion à la base de données 
	 */
	public DAOProduits() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
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
	
	@Override
	public void insertionProduit(I_Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits(nomProduit, prixProduit, nbrStock) "
					+ "VALUES(?,?,?)");

			statement.setString(1, produit.getNom());
			statement.setDouble(2, produit.getPrixUnitaireHT());
			statement.setInt(3, produit.getQuantite());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	@Override
	public void insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits(nomProduit, prixProduit, nbrStock) "
					+ "VALUES(?,?,?)");

			statement.setString(1, nomProduit);
			statement.setDouble(2, prixProduit);
			statement.setInt(3, qteProduit);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}

	}

	@Override
	public void deleteProduit(String nomProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");

			statement.setString(1, nomProduit);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void deleteProduit(I_Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");

			statement.setString(1, produit.getNom());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Update la quantité d'un produit lorsqu'il est modifié
	 */
	@Override
	public void updateQuantiteProduit(I_Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("UPDATE Produits SET nbrStock = ? WHERE nomProduit = ?");

			statement.setInt(1, produit.getQuantite());
			statement.setString(2, produit.getNom());
			statement.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());		
		}
	}
	
	@Override
	public void updateQuantiteProduit(String nomProduit, int quantite) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("UPDATE Produits SET nbrStock = ? WHERE nomProduit = ?");

			statement.setInt(1, quantite);
			statement.setString(2, nomProduit);
			statement.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());		
		}		
	}

	@Override
	public ArrayList<I_Produit> getProduits(){
		ArrayList<I_Produit> liste = new ArrayList<I_Produit>();
		
		try {
			PreparedStatement statement = this.cn.prepareStatement("SELECT * FROM Produits");
			ResultSet res = statement.executeQuery();
			
			// Tant qu'il y a un suivant dans le resultset, on l'ajoute à la liste que l'on
			// renvoiera.
			while(res.next()) {
				String nom = res.getString(1);
				Double prix = res.getDouble(2);
				int qte = res.getInt(3);
				
				liste.add(new Produit(nom, prix, qte));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());		
		}
		
		return liste;
		
	}

	
}
