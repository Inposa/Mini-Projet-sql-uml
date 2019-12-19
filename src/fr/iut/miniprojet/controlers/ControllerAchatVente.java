package fr.iut.miniprojet.controlers;

import fr.iut.miniprojet.entities.Catalogue;

public class ControllerAchatVente {
	
	private Catalogue catalogue;
	
	public ControllerAchatVente(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	public boolean acheterProduit(String nom, int quantite) {
		return this.catalogue.acheterStock(nom, quantite);
	}
	
	public boolean vendreProduit(String nom, int quantite) {
		return this.catalogue.vendreStock(nom, quantite);
	}
}
