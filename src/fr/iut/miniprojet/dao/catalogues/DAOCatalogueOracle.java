package fr.iut.miniprojet.dao.catalogues;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.iut.miniprojet.entities.Catalogue;
import fr.iut.miniprojet.entities.I_Catalogue;
import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

public class DAOCatalogueOracle implements DAOCatalogue {

	private Connection cn;

	public DAOCatalogueOracle(Connection cn) {
		this.cn = cn;
	}

	@Override
	public boolean insertionCatalogue(I_Catalogue catalogue) {
		return this.insertionCatalogue(catalogue.getNom());
	}

	@Override
	public boolean insertionCatalogue(String nom) {
		try {
			CallableStatement cst = this.cn.prepareCall("{CALL insererNouveauCatalogue(?)}");
			cst.setString(1, nom);
			cst.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteCatalogue(I_Catalogue catalogue) {
		return this.deleteCatalogue(catalogue.getNom());
	}

	@Override
	public boolean deleteCatalogue(String nomCatalogue) {
		try {
			CallableStatement cst = this.cn.prepareCall("{CALL deleteCatalogue(?)}");
			cst.setString(1, nomCatalogue);
			cst.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}



	@Override
	public int getNbProduits(I_Catalogue catalogue) {
		try {
			PreparedStatement pst = this.cn.prepareStatement("SELECT COUNT(*) FROM Produits2 WHERE nomCatalogue = ?");
			pst.setString(1, catalogue.getNom());

			ResultSet res = pst.executeQuery();		
			res.next();
			return res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return -1;
		}

	}

	@Override
	public I_Catalogue lire(String nomCatalogue) {
		try {
			PreparedStatement pst = this.cn.prepareStatement("SELECT * FROM Catalogues WHERE nomCatalogue = ?");

			pst.setString(1, nomCatalogue);
			ResultSet res = pst.executeQuery();

			return new Catalogue(res.getString(1));

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}

	}

	@Override
	public List<I_Catalogue> getCatalogues() {
		List<I_Catalogue> liste = new ArrayList<I_Catalogue>();

		try {
			PreparedStatement statement = this.cn.prepareStatement("SELECT * FROM Catalogues");
			ResultSet res = statement.executeQuery();

			// Tant qu'il y a un suivant dans le resultset, on l'ajoute à la liste que l'on
			// renvoiera.
			while(res.next()) {
				String nom = res.getString(1);
				System.out.println(nom);

				liste.add(new Catalogue(nom));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}

		return liste;
	}

	@Override
	public boolean clear() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Charger en mémoire les produits d'un catalogue vide (on ne possède que son nom pour le moment) 
	 */
	@Override
	public I_Catalogue remplirCatalogue(I_Catalogue catalogue) {
		try {
			PreparedStatement pst = this.cn.prepareStatement("SELECT nomProduit, prixProduit, nbrStock"
					+ " FROM Produits2 WHERE nomCatalogue = ? ");

			pst.setString(1, catalogue.getNom());
			ResultSet res = pst.executeQuery();

			List<I_Produit> liste = new ArrayList<I_Produit>();
			while(res.next()) {
				liste.add(new Produit(res.getString(1), res.getFloat(2), res.getInt(3)));
			}

			catalogue.addProduits(liste);
			return catalogue;

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}

	}

}
