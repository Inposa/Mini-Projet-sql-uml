package fr.iut.miniprojet.controlers;

import fr.iut.miniprojet.entities.Catalogue;

public class ControllerStock {
	
	private Catalogue catalogue;
	
	public ControllerStock(Catalogue catalogue) {
		this.catalogue = catalogue;
	}
	
	public String afficherStock() {
		return this.catalogue.toString();
	}
}
