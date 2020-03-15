package fr.iut.miniprojet.entities;

import java.text.DecimalFormat;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.iut.miniprojet.dao.DaoOracleFactory;
import fr.iut.miniprojet.dao.DaoXMLFactory;

//import fr.iut.miniprojet.dao.DAOProduits;

import fr.iut.miniprojet.dao.produits.*;

public class Catalogue implements I_Catalogue{
	
	public static String DaoMethod = "oracle";
	
	// Liste contenant le contenu du catalogue
	private List<I_Produit> lesProduit;

	private DAOProduit daoProduits;

	private String nom;
	
	public Catalogue(String nom) {
		this.nom = nom;
		
		this.lesProduit = new ArrayList<I_Produit>();
		
		
		switch (DaoMethod) {
		case "oracle":
			this.daoProduits = DaoOracleFactory.getInstance().createDaoProduit();
			break;
		case "xml":
			this.daoProduits = DaoXMLFactory.getInstance().createDaoProduit();
		
		default:
			this.daoProduits = DaoOracleFactory.getInstance().createDaoProduit();
			break;
		}
		//TODO Récupération liaison avec bdd
		/*//Création d'une nouvelle instance de dao en fonction de la méthode souhaitée (oracle ou xml)
		this.daoProduits = DAOProduitBuilder.getInstance().createDAOProduit();
*/		
		
		
		List<I_Produit> liste = this.daoProduits.getProduits();
		this.addProduits(liste);
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		boolean retour = false;

		if(produit != null) {
			String nomVerif = produit.getNom().trim().replaceAll("\t", " ");

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
		boolean booleanValue = false;
		String nomVerif = nom.trim().replaceAll("\t", " ");

		if(nom != null) {
			/*	Vérification si un produit du même nom existe déjà dans la liste, la fonction getProduitByName renvoie null s'il
	 		n'en existe aucun dans la liste.
	 		On vérifie également que sa quantité et son prix sont des valeur légales (supérieures à 0) */
			if(this.getProduitByName(nomVerif)==null) {
				if(prix > 0 && qte >= 0) {
					this.lesProduit.add(new Produit(nomVerif, prix, qte));
					this.daoProduits.insertionProduit(nomVerif, prix, qte);
					booleanValue = true;
				}
			}
		}

		return booleanValue;
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
		boolean booleanValue = false;
		I_Produit produit = this.getProduitByName(nom);

		if(produit != null) {
			this.lesProduit.remove(produit);
			this.daoProduits.deleteProduit(nom);
			booleanValue = true;
		}

		return booleanValue;
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
		boolean booleanValue = false;
		I_Produit produit = this.getProduitByName(nomProduit);

		if(produit != null) {
			if(produit.ajouter(qteAchetee)) {
				this.daoProduits.maj(produit);
				booleanValue = true;
			}
		}
		return booleanValue;
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
		boolean booleanValue = false;
		I_Produit produit = this.getProduitByName(nomProduit);

		if(produit != null) {
			if (produit.enlever(qteVendue)) {
				this.daoProduits.maj(produit);
				booleanValue = true;
			}
		}
		return booleanValue;
	}

	/**
	 * @return Retourne un tableau de string contenant les noms des produit présents dans le catalogue
	 */
	@Override
	public String[] getNomProduits() {
		List<String> tabRetour = new ArrayList<String>();

		for(I_Produit produit : this.lesProduit) {
			tabRetour.add(produit.getNom());
		}
		tabRetour.sort(Comparator.naturalOrder());
		return this.toStringArray(tabRetour);
	}

	/**
	 * 
	 * @param 	liste, une liste de chaînes de caractères
	 * @return 	Retourne un tableau ayant le même contenu que la liste liste,
	 * 			retourne null si liste = null
	 */
	private String[] toStringArray(List<String> liste) {
		String[] array = null;

		if(liste != null) {
			int size = liste.size();
			array = new String[size];
			for(int i = 0; i < size; i++) {
				array[i] = liste.get(i);
			}
		}
		return array;

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
		this.daoProduits.clear();
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
		String retourString = "";
		for(I_Produit produit : this.lesProduit) {
			retourString += produit.toString() + "\n";
		}
		DecimalFormat format = new DecimalFormat("###########0.00");

		retourString += "\nMontant total TTC du stock : "
				+format.format(this.getMontantTotalTTC()).replace('.', ',')+" €";
		return retourString;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return this.nom;
	}
}
