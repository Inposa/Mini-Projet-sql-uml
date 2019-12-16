package fr.iut.miniprojet.entities;

import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue{

	private List<I_Produit> lesProduit;

	public Catalogue() {
		this.lesProduit = new ArrayList<I_Produit>();

	}

	@Override
	public boolean addProduit(I_Produit produit) {
		boolean retour = false;

		if(produit != null) {
			String nomVerif = produit.getNom().trim();

			/*	Vérification si un produit du même nom existe déjà dans la liste, la fonction getProduitByName renvoie null s'il
		 	n'en existe aucun dans la liste.
		 	On vérifie également que sa quantité et son prix sont des valeur légales (supérieures à 0) */


			if(this.getProduitByName(nomVerif) == null) {

				if(produit.getPrixUnitaireHT() > 0 && produit.getQuantite() >= 0) {
					this.lesProduit.add(produit);
					retour = true;
				}
			}
		}

		return retour;

	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		boolean retour = false;

		String nomVerif = nom.trim();

		if(nom != null) {

			/*	Vérification si un produit du même nom existe déjà dans la liste, la fonction getProduitByName renvoie null s'il
	 		n'en existe aucun dans la liste.
	 		On vérifie également que sa quantité et son prix sont des valeur légales (supérieures à 0) */

			if(this.getProduitByName(nomVerif)==null) {
				if(prix > 0 && qte >= 0) {
					this.lesProduit.add(new Produit(nomVerif, prix, qte));
					retour = true;
				}

			}
		}

		return retour;
	}

	/**
	 * @return Retourne le nombre de produit qui ont pu être ajoutés au catalogue
	 */
	@Override
	public int addProduits(List<I_Produit> l) {
		int i = 0;
		if(l != null) {
			for(I_Produit produit : l) {
				if(this.addProduit(produit)) {
					i++;
				}
			}
		}
		return i;
	}

	/**
	 * @param 	nom le nom du produit que l'on souhaite retirer du catalogue
	 * @return 	retourne true si le produit existe bien dans le catalogue et qu'il a bien été retiré de celui-ci
	 * 			retoure false, sinon.
	 */
	@Override
	public boolean removeProduit(String nom) {
		boolean retour = false;
		I_Produit produit = this.getProduitByName(nom);

		if(produit != null) {
			this.lesProduit.remove(produit);
			retour = true;
		}

		return retour;
	}


	/**
	 * @param 	nomProduit le nom du produit que l'on souhaite acheter
	 * @param 	qteVendue la quantité de produit que l'on souhaite acheter
	 * 
	 * @return 	retourne true si la fonction s'est déroulée sans erreur (stock bien soustrait et produit présent dans le catalogue),
	 * 			retourne faux, sinon.
	 */
	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		boolean retour = false;
		I_Produit produit = this.getProduitByName(nomProduit);

		if(produit != null) {
			if(produit.ajouter(qteAchetee)) {
				retour = true;
			}
		}

		return retour;
	}

	/**
	 * @param 	nomProduit le nom du produit que l'on souhaite vendre
	 * @param 	qteVendue la quantité de produit que l'on souhaite vendre
	 * 
	 * @return 	retourne true si la fonction s'est déroulée sans erreur (stock bien soustrait et produit présent dans le catalogue),
	 * 			retourne faux, sinon.
	 */
	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		boolean retour = false;
		I_Produit produit = this.getProduitByName(nomProduit);

		if(produit != null) {
			if (produit.enlever(qteVendue)) {
				retour = true;
			}
		}

		return retour;
	}

	/**
	 * @return Retourne un tableau de string contenant les noms des produit présents dans le catalogue
	 */
	@Override
	public String[] getNomProduits() {
		String[] tabRetour = new String[this.lesProduit.size()];

		for(int i = 0;i < this.lesProduit.size();i++) {
			tabRetour[i] = this.lesProduit.get(i).getNom();
		}
		
		return tabRetour;
	}


	@Override
	public double getMontantTotalTTC() {
		double total = 0;
		for(I_Produit produit : this.lesProduit) {
			total += produit.getPrixStockTTC();
		}
		return Math.round((total)*100.0)/100.0;
	}

	@Override
	public void clear() {
		this.lesProduit.clear();

	}

	/**
	 * 
	 * @param nomProduit, un nom de produit que l'on souhaite chercher dans la liste lesProduit
	 * @return Retourne null si un produit de nom nomProduit n'exite pas dans la liste, sinon retourne le produit trouvé.
	 */
	private I_Produit getProduitByName(String nomProduit) {
		I_Produit produit = null;
		int i = 0;

		while(produit == null && i < this.lesProduit.size()) {
			I_Produit testProduit = this.lesProduit.get(i);
			if(testProduit.getNom().equals(nomProduit)) {
				produit = testProduit;
			}

			i++;
		}

		return produit;

	}

	@Override
	public String toString() {
		String retour = "";
		for(I_Produit produit : this.lesProduit) {
			retour += produit.toString() + "\n";
		}

		return retour;
	}

}
