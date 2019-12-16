package fr.iut.miniprojet.entities;


public class Produit implements I_Produit{
	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;

	private static double tauxTVA = 0.2;
	
	public Produit(String nom, double prixUnitaireHT, int quantite) {
		this.quantiteStock = quantite;
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		boolean response = false;
		
		/* On ne peut pas ajouter de quantité négative */
		if(qteAchetee > 0) {
			this.quantiteStock += qteAchetee;
			response = true;
		}
		
		return response;
	}

	@Override
	public boolean enlever(int qteVendue) {
		boolean response = false;
		
		/*	On ne peut pas ajouter de quantité négative ou qui soit supérieure à ce que 
			l'on a déjà, il ne faut pas que this.quantiteStock < 0 */
		if(qteVendue > 0 && qteVendue <= this.quantiteStock) {
			this.quantiteStock -= qteVendue;
			response = true;
		}
		
		return response;
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT + this.prixUnitaireHT * Produit.tauxTVA;
	}

	@Override
	public double getPrixStockTTC() {
		return this.getPrixUnitaireTTC() * this.quantiteStock;
	}
	
	@Override
	public String toString() {
		return this.nom+" | Prix HT : "+Double.toString(prixUnitaireHT)+
				" | Prix TTC"+Double.toString(this.getPrixUnitaireTTC())+
				" | Quantité stock : "+this.quantiteStock;
	}
}
