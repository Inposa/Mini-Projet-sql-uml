package fr.iut.miniprojet.dao.produits;

import java.util.List;

import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

public class DAOProduitXml implements DAOProduit {
	
	private ProduitDAO_XML xmlDAO;

	public DAOProduitXml(String nomCatalogue) {
		this.xmlDAO = new ProduitDAO_XML(nomCatalogue);
	}

	@Override
	public boolean insertionProduit(I_Produit produit) {
		return this.xmlDAO.creer(produit);
	}

	@Override
	public boolean insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		return this.xmlDAO.creer(new Produit(nomProduit, prixProduit, qteProduit));
	}

	@Override
	public boolean deleteProduit(String nomProduit) {
		return this.xmlDAO.supprimer(this.lire(nomProduit));
	}

	@Override
	public boolean deleteProduit(I_Produit produit) {
		return this.xmlDAO.supprimer(produit);
	}

	@Override
	public boolean maj(I_Produit p) {
		return this.xmlDAO.maj(p);
	}

	@Override
	public I_Produit lire(String nomProduit) {
		return this.xmlDAO.lire(nomProduit);
	}

	@Override
	public List<I_Produit> getProduits() {
		return this.xmlDAO.lireTous();
	}

	@Override
	public boolean clear() {
		List<I_Produit> liste = this.getProduits();
		for(I_Produit p : liste) {
			this.deleteProduit(p);
		}
		return true;
	}

}
