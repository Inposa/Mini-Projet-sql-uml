package fr.iut.miniprojet.dao;

import java.util.List;

import fr.iut.miniprojet.entities.I_Produit;

public interface DAO {
	public void insertionProduit(I_Produit produit);
	public void insertionProduit(String nomProduit, double prixProduit, int qteProduit);
	public void deleteProduit(String nomProduit);
	public void deleteProduit(I_Produit produit);
	public void updateQuantiteProduit(I_Produit produit);
	public void updateQuantiteProduit(String nomProduit, int quantite);
	public List<I_Produit> getProduits();
}
