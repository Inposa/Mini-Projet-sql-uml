package fr.iut.miniprojet.entities;

import java.util.List;

public class Produit implements I_Catalogue {
	private int quantiteStock;
	private String nom;
	private float prixUnitaireHT;

	private static float tauxTVA;

	@Override
	public boolean addProduit(I_Produit produit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeProduit(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getNomProduits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMontantTotalTTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return "["+this.nom+"|"+this.prixUnitaireHT+"]";
	}

}
