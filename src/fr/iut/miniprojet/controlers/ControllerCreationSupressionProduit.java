package fr.iut.miniprojet.controlers;

import fr.iut.miniprojet.entities.Catalogue;

public class ControllerCreationSupressionProduit {
	
	private Catalogue catalogue;
	
	public ControllerCreationSupressionProduit(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	public boolean creationProduit(String nom, double prix, int quantite) {
		return this.catalogue.addProduit(nom, prix, quantite);
	}
	
	public boolean suppressionProduit(String nom) {
		return this.catalogue.removeProduit(nom);
	}
	
}