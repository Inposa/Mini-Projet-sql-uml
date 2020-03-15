package fr.iut.miniprojet.controlers;

import java.util.ArrayList;
import java.util.List;

import fr.iut.miniprojet.Config;
import fr.iut.miniprojet.dao.DaoOracleFactory;
import fr.iut.miniprojet.dao.DaoXMLFactory;
import fr.iut.miniprojet.dao.catalogues.DAOCatalogue;
import fr.iut.miniprojet.entities.I_Catalogue;

public class ControllerGestionCatalogues {
	private DAOCatalogue daoCatalogue;
	
	private List<Observateur> observateurs;
	
	public ControllerGestionCatalogues() {
		this.observateurs = new ArrayList<Observateur>();
		
		switch(Config.daoMethod) {
		case "oracle":
			this.daoCatalogue = DaoOracleFactory.getInstance().createDaoCatalogue();
			break;
		
		case "xml":
			this.daoCatalogue = DaoXMLFactory.getInstance().createDaoCatalogue();
			break;
		}
		
	}
	
	/**
	 * Renvoie le nom de tous les catalogues présents dans la BD
	 */
	public String[] getNomsCatalogues() {
		List<I_Catalogue> liste = this.daoCatalogue.getCatalogues();
		String[] tab = new String[liste.size()];
		
		for(int i = 0; i < liste.size(); i++) {
			tab[i] = liste.get(i).getNom();
		}
		
		return tab;
	}
	
	/**
	 *  Renvoie un tableau de String sous la forme : 
	 * 	"<nom catalogue> : X produits"
	 */
	public String[] getDetailCatalogues() {
		List<I_Catalogue> liste = this.daoCatalogue.getCatalogues();
		
		String[] tab = new String[liste.size()];
		
		for(int i = 0; i < liste.size(); i++) {
			I_Catalogue current = liste.get(i);
			tab[i] = current.getNom() +" : "+ this.daoCatalogue.getNbProduits(current) +" produits";
		}
		
		return tab;
	}
	
	public boolean creerNouveauCatalogue(String nom) {
		boolean reponse = this.daoCatalogue.insertionCatalogue(nom);
		this.notice();
		return reponse;		
	}
	
	public boolean supprimerCatalogue(String nom) {
		boolean reponse = this.daoCatalogue.deleteCatalogue(nom);
		this.notice();
		return reponse;
	}
	
	
	public I_Catalogue selectionnerCatalogue(String nom) {
		// Permet d'obtenir un catalogue possédant le nom souhaité
		// Il est ensuite rempli avec son contenu lu dans la BDD puis renvoyé
		I_Catalogue catalogue = this.daoCatalogue.lire(nom);
		this.daoCatalogue.remplirCatalogue(catalogue);
		
		return catalogue;
		
	}
	
	
	/**
	 * Attacher un nouvel observateur au controller
	 * @param observateur
	 */
	public void attach(Observateur observateur) {
		this.observateurs.add(observateur);
		observateur.maj(this.getNomsCatalogues());
	}
	
	/**
	 * Mettre à jour tous les observateurs attachés au controller
	 */
	public void notice() {
		for(Observateur observateur : this.observateurs) {
			observateur.maj(this.getNomsCatalogues());
		}
	}
}
