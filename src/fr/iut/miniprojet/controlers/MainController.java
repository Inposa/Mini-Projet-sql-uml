package fr.iut.miniprojet.controlers;

import fr.iut.miniprojet.entities.Catalogue;

public class MainController {
	// Catalogue qui sera le même pour les trois autres controlers
	private Catalogue catalogue;
	
	// Controllers :
	private ControllerStock controllerStock;
	private ControllerAchatVente controllerAchatVente;
	private ControllerCreationSupressionProduit controllerCreationSupressionProduit;
	
	public MainController() {
		this.catalogue = new Catalogue();
		
		this.controllerAchatVente = new ControllerAchatVente(catalogue);
		this.controllerStock = new ControllerStock(catalogue);
		this.controllerCreationSupressionProduit = new ControllerCreationSupressionProduit(catalogue);
		
	}
	
	public Catalogue getCatalogue() {
		return this.catalogue;
	}

	public ControllerStock getControllerStock() {
		return controllerStock;
	}

	public ControllerAchatVente getControllerAchatVente() {
		return controllerAchatVente;
	}

	public ControllerCreationSupressionProduit getControllerCreationSupressionProduit() {
		return controllerCreationSupressionProduit;
	}
	
}
