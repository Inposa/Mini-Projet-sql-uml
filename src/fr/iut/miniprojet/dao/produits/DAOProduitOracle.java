package fr.iut.miniprojet.dao.produits;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

//Singleton
public class DAOProduitOracle implements DAOProduit {
	
	private Connection cn;
	public static DAOProduit instance = null;
	
	public static DAOProduit getInstance() {
		if(DAOProduitOracle.instance == null) {
			DAOProduitOracle.instance = new DAOProduitOracle();
		}
		return DAOProduitOracle.instance;
	}
	
	private DAOProduitOracle() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connexion depuis le réseau IUT : jdbc:oracle:thin:@gloin:1521:iut
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
	public boolean insertionProduit(I_Produit produit) {
		try {
			//CallableStatement cs = ("{call insererProduit(?,?,?)}");
			
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits(nomProduit, prixProduit, nbrStock) "
					+ "VALUES(?,?,?)");

			statement.setString(1, produit.getNom());
			statement.setDouble(2, produit.getPrixUnitaireHT());
			statement.setInt(3, produit.getQuantite());

			statement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		try {
			
			PreparedStatement statement = this.cn.prepareStatement("INSERT INTO Produits(nomProduit, prixProduit, nbrStock) "
					+ "VALUES(?,?,?)");

			statement.setString(1, nomProduit);
			statement.setDouble(2, prixProduit);
			statement.setInt(3, qteProduit);

			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteProduit(String nomProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");

			statement.setString(1, nomProduit);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteProduit(I_Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");

			statement.setString(1, produit.getNom());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Update la quantité d'un produit lorsqu'il est modifié
	 */
	@Override
	public boolean maj(I_Produit produit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("UPDATE Produits SET nbrStock = ? WHERE nomProduit = ?");

			statement.setInt(1, produit.getQuantite());
			statement.setString(2, produit.getNom());
			statement.executeUpdate();
			return true;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
/*	@Override
	public boolean maj(String nomProduit, int quantite) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("UPDATE Produits SET nbrStock = ? WHERE nomProduit = ?");

			statement.setInt(1, quantite);
			statement.setString(2, nomProduit);
			statement.executeUpdate();
			return true;
			
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}		
	}*/
	
	/**
	 * Retourne un produit à partir de son nom
	 */
	@Override
	public I_Produit lire(String nomProduit) {
		try {
			PreparedStatement statement = this.cn.prepareStatement("SELECT * FROM Produits WHERE nomProduit = ?");
			
			statement.setString(1, nomProduit);
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
			statement = this.cn.prepareStatement("DELETE FROM Produits");
			ResultSet res = statement.executeQuery();
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());		
			return false;
		}
		
	}

}
