package fr.iut.miniprojet.dao.catalogues;

import java.util.List;

import fr.iut.miniprojet.entities.Catalogue;
import fr.iut.miniprojet.entities.I_Catalogue;

public class DAOCatalogueXml implements DAOCatalogue {

	private CatalogueDAO_XML daoXML;
	
	public DAOCatalogueXml() {
		this.daoXML = new CatalogueDAO_XML();
	}
	
	@Override
	public boolean insertionCatalogue(I_Catalogue catalogue) {
		return this.daoXML.creer(catalogue);
	}

	@Override
	public boolean insertionCatalogue(String nom) {
		return this.daoXML.creer(new Catalogue(nom));
	}

	@Override
	public boolean deleteCatalogue(String nomCatalogue) {
		return this.deleteCatalogue(new Catalogue(nomCatalogue));
	}

	@Override
	public boolean deleteCatalogue(I_Catalogue catalogue) {
		return this.daoXML.supprimer(catalogue);
	}

	@Override
	public int getNbProduits(I_Catalogue catalogue) {
		return this.daoXML.lireTous().size();
	}

	@Override
	public I_Catalogue lire(String nomCatalogue) {
		return this.daoXML.lire(nomCatalogue);
	}

	@Override
	public List<I_Catalogue> getCatalogues() {
		return this.daoXML.lireTous();
	}

	@Override
	public boolean clear() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public I_Catalogue remplirCatalogue(I_Catalogue catalogue) {
		// TODO Auto-generated method stub
		return null;
	}

}
