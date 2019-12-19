package fr.iut.miniprojet.controlers;

import fr.iut.miniprojet.entities.Catalogue;

import java.lang.NumberFormatException;

public class ControllerCreationSupressionProduit {
	
	private Catalogue catalogue;
	
	public ControllerCreationSupressionProduit(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	public boolean creationProduit(String nom, double prix, int quantite) {
		boolean retour = false;
		try {
			retour = catalogue.addProduit(nom, prix, quantite);
		}
		catch (NumberFormatException e) {
			System.err.println("Mauvais format, veuillez rentrer un nombre pour "
					+ "le prix et la quantité.");
		}
		
		return retour;
	}
}