package fr.iut.miniprojet.dao.produits;

import java.util.List;

import fr.iut.miniprojet.entities.I_Produit;

public class DAOProduitXml implements DAOProduit {
	
	public static DAOProduit instance = null;
	
	public static DAOProduit getInstance() {
		if(DAOProduitXml.instance == null) {
			DAOProduitXml.instance = new DAOProduitXml();
		}
		return DAOProduitXml.instance;
	}
	
	private DAOProduitXml() {}
	
	@Override
	public void insertionProduit(I_Produit produit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProduit(String nomProduit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProduit(I_Produit produit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQuantiteProduit(I_Produit produit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQuantiteProduit(String nomProduit, int quantite) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<I_Produit> getProduits() {
		// TODO Auto-generated method stub
		return null;
	}

}
