package fr.iut.miniprojet.dao.produits;

import java.util.List;

import fr.iut.miniprojet.entities.I_Produit;

public interface DAOProduit {
	public boolean insertionProduit(I_Produit produit);
	public boolean insertionProduit(String nomProduit, double prixProduit, int qteProduit);
	
	public boolean deleteProduit(String nomProduit);
	public boolean deleteProduit(I_Produit produit);
	
	public boolean maj(I_Produit p);
	public boolean maj(String nomProduit, int qte);
	
	public I_Produit lire(String nomProduit);
	public List<I_Produit> getProduits();
	
	public boolean clear();
}
