package fr.iut.miniprojet.dao.catalogues;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import fr.iut.miniprojet.entities.Catalogue;
import fr.iut.miniprojet.entities.I_Catalogue;
import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;


public class CatalogueDAO_XML {
	private String uri = "./db/Catalogues.xml";
	private Document doc;

	public CatalogueDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	public boolean creer(I_Catalogue c) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("catalogue");
			prod.setAttribute("nom", c.getNom());
			root.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	public boolean supprimer(I_Catalogue c) {
		try {
			Element root = doc.getRootElement();
			Element prod = chercheCatalogue(c.getNom());
			if (prod != null) {
				root.removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer catalogue");
			return false;
		}
	}

	public I_Catalogue lire(String nom) {
		Element e = chercheCatalogue(nom);
		if (e != null)
			return new Catalogue(e.getAttributeValue("nom"));
		else
			return null;
	}

	public List<I_Catalogue> lireTous() {

		List<I_Catalogue> l = new ArrayList<I_Catalogue>();
		try {
			Element root = doc.getRootElement();
			List<Element> lProd = root.getChildren("catalogue");

			for (Element prod : lProd) {
				String nomC = prod.getAttributeValue("nom");
				l.add(new Catalogue(nomC));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les Catalogues");
		}
		return l;
	}

	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

	private Element chercheCatalogue(String nom) {
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("produit");
		int i = 0;
		while (i < lProd.size() && !lProd.get(i).getAttributeValue("nom").equals(nom))
			i++;
		if (i < lProd.size())
			return lProd.get(i);
		else
			return null;
	}
}
