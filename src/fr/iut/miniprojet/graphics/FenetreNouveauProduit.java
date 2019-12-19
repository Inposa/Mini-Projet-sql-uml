package fr.iut.miniprojet.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iut.miniprojet.controlers.ControllerCreationSupressionProduit;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
	//	private JComboBox<String> combo;
	private JButton btValider;

	private ControllerCreationSupressionProduit controllerCreationSupressionProduit;

	//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(ControllerCreationSupressionProduit controllerCreationSupressionProduit) {

		this.controllerCreationSupressionProduit = controllerCreationSupressionProduit;

		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantité en stock");
		//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

		//		combo = new JComboBox<String>(lesCategories);
		//		combo.setPreferredSize(new Dimension(100, 20));
		//		contentPane.add(labCategorie);
		//		contentPane.add(combo);


		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btValider) {
			try {

				String nom = this.txtNom.getText();
				double prix = Double.parseDouble(this.txtPrixHT.getText());
				int quantite = Integer.parseInt(this.txtQte.getText());

				this.controllerCreationSupressionProduit.creationProduit(nom, prix, quantite);
			}
			catch(NumberFormatException ex) {
				System.err.println("Mauvais format, veuillez rentrer un nombre pour "
						+ "le prix et la quantité.");
			}
		}

		this.dispose();
	}

}