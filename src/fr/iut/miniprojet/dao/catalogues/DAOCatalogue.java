package fr.iut.miniprojet.dao.catalogues;

import java.util.List;

import fr.iut.miniprojet.entities.Catalogue;
import fr.iut.miniprojet.entities.I_Catalogue;

public interface DAOCatalogue {
	public boolean insertionCatalogue(I_Catalogue Catalogue);
	public boolean insertionCatalogue(String nom);
	
	public boolean deleteCatalogue(String nomCatalogue);
	public boolean deleteCatalogue(I_Catalogue catalogue);
	
	public int getNbProduits(I_Catalogue catalogue);
	
	
	public I_Catalogue lire(String nomCatalogue);
	public List<I_Catalogue> getCatalogues();
	
	public boolean clear();
	
	public I_Catalogue remplirCatalogue(I_Catalogue catalogue);
	
}
