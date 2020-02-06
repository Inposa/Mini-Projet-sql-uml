package fr.iut.miniprojet.dao.produits;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import fr.iut.miniprojet.entities.I_Produit;
import fr.iut.miniprojet.entities.Produit;

public class DAOProduitXml implements DAOProduit {
	private String uri = "/home/licence/pechh/xml_files/Produits.xml";
	private Document doc;
	
	public static DAOProduitXml instance = null;
	
	
	public static DAOProduitXml getInstance() {
		if(DAOProduitXml.instance == null) {
			DAOProduitXml.instance = new DAOProduitXml();
		}
		return DAOProduitXml.instance;
	}
	
	private DAOProduitXml() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	@Override
	public boolean insertionProduit(I_Produit p) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("produit");
			prod.setAttribute("nom", p.getNom());
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(p.getPrixUnitaireHT())));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(p.getQuantite())));
			root.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	@Override
	public boolean insertionProduit(String nomProduit, double prixProduit, int qteProduit) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("produit");
			prod.setAttribute("nom", nomProduit);
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(prixProduit)));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(qteProduit)));
			root.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	@Override
	public boolean deleteProduit(I_Produit p) {
		try {
			Element root = doc.getRootElement();
			Element prod = chercheProduit(p.getNom());
			if (prod != null) {
				root.removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}

	@Override
	public boolean deleteProduit(String nomProduit) {
		try {
			Element root = doc.getRootElement();
			Element prod = chercheProduit(nomProduit);
			if (prod != null) {
				root.removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("Erreur supprimer produit");
			return false;
		}
	}

	@Override
	public boolean maj(I_Produit p) {
		try {
			Element prod = chercheProduit(p.getNom());
			if (prod != null) {
				prod.getChild("quantite").setText(String.valueOf(p.getQuantite()));
				return sauvegarde();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}

	@Override
	public boolean maj(String nomProduit, int qte) {
		try {
			Element prod = chercheProduit(nomProduit);
			if (prod != null) {
				prod.getChild("quantite").setText(String.valueOf(qte));
				return sauvegarde();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}

	/**
	 * Retourne un produit correspondant à un nom
	 */
	@Override
	public I_Produit lire(String nomProduit) {
		Element e = chercheProduit(nomProduit);
		if (e != null)
			return new Produit(e.getAttributeValue("nom"), Double.parseDouble(e.getChildText("prixHT")), Integer.parseInt(e.getChildText("quantite")));
		else
			return null;
	}

	/**
	 * Retourne tous les produits déjà enregistrés 
	 */
	@Override
	public List<I_Produit> getProduits() {

		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			Element root = doc.getRootElement();
			List<Element> lProd = root.getChildren("produit");

			for (Element prod : lProd) {
				String nomP = prod.getAttributeValue("nom");
				Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
				int qte = Integer.parseInt(prod.getChild("quantite").getText());
				l.add(new Produit(nomP, prix, qte));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return l;
	}
	
	
	/**
	 * Sauvegarde de nouvelles données dans le fichier xml
	 */
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

	/**
	 * Rechercher un produit dans le fichier xml en fonction de son nom
	 */
	private Element chercheProduit(String nom) {
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

	@Override
	public boolean clear() {
		// TODO Auto-generated method stub
		return false;
	}
}
