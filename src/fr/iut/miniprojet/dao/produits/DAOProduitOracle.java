package fr.iut.miniprojet.dao.produits;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.iut.miniprojet.entities.I_Catalogue;
import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

//Singleton
public class DAOProduitOracle implements DAOProduit {
	
	private Connection cn;
	
	private String nomCatalogue;
	
	public DAOProduitOracle(Connection cn, I_Catalogue catalogue) {
		this.cn = cn;
		this.nomCatalogue = catalogue.getNom();
	}
	
	@Override
	public boolean insertionProduit(I_Produit produit) {
		return this.insertionProduit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite());
	}

	@Override
	public boolean insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		try {
			
			CallableStatement statement = this.cn.prepareCall("{CALL insererNouveauProduit(?,?,?,?)}");
			
			/*
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits2(nomProduit, prixProduit, nbrStock, nomCatalogue) "
					+ "VALUES(?,?,?,?)");
			*/
			statement.setString(1, nomProduit);
			statement.setDouble(2, prixProduit);
			statement.setInt(3, qteProduit);
			statement.setString(4, this.nomCatalogue);

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteProduit(I_Produit produit) {
		return this.deleteProduit(produit.getNom());
	}
	
	@Override
	public boolean deleteProduit(String nomProduit) {
		try {
			CallableStatement statement = this.cn.prepareCall("{CALL deleteProduit(?,?)}");
			
			/*
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits2 WHERE nomProduit = ? "
					+ "AND nomCatalogue = ?");
			*/
			statement.setString(1, nomProduit);
			statement.setString(2, this.nomCatalogue);
			
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * Update la quantité d'un produit lorsqu'il est modifié
	 */
	@Override
	public boolean maj(I_Produit produit) {
		try {
			CallableStatement statement = this.cn.prepareCall("{CALL updateProduit(?,?,?}");
			/*
			PreparedStatement statement = this.cn.prepareStatement("UPDATE Produits2 SET nbrStock = ? WHERE nomProduit = ? "
					+ "AND nomCatalogue = ?");
			*/
	
			statement.setInt(1, produit.getQuantite());
			statement.setString(2, produit.getNom());
			statement.setString(3, this.nomCatalogue);
			
			statement.executeUpdate();
			return true;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Retourne un produit à partir de son nom
	 */
	@Override
	public I_Produit lire(String nomProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("SELECT nomProduit, prixProduit, nbrStock "
					+ "FROM Produits2 WHERE nomProduit = ? AND nomCatalogue = ?");
			
			statement.setString(1, nomProduit);
			statement.setString(2, this.nomCatalogue);
			
			ResultSet res = statement.executeQuery();
			
			return new Produit(res.getString(1), res.getDouble(2), res.getInt(3));
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Retourne tous les produits présents dans la base
	 */
	@Override
	public List<I_Produit> getProduits(){
		List<I_Produit> liste = new ArrayList<I_Produit>();
		
		try {
			PreparedStatement statement = this.cn.prepareStatement("SELECT nomProduit, prixProduit, nbrStock "
					+ "FROM Produits2 WHERE nomCatalogue = ?");
			statement.setString(1, this.nomCatalogue);
			
			ResultSet res = statement.executeQuery();
			
			// Tant qu'il y a un suivant dans le resultset, on l'ajoute à la liste que l'on
			// renvoiera.
			while(res.next()) {
				String nom = res.getString(1);
				Double prix = res.getDouble(2);
				int qte = res.getInt(3);
				
				liste.add(new Produit(nom, prix, qte));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
		
		return liste;
		
	}

	@Override
	public boolean clear() {
		PreparedStatement statement;
		try {
			statement = this.cn.prepareStatement("DELETE FROM Produits2 WHERE nomCatalogue = ?");
			statement.setString(1, this.nomCatalogue);
			
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());		
			return false;
		}
		
	}

}
