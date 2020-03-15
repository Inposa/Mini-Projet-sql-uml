package fr.iut.miniprojet.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iut.miniprojet.controlers.ControllerGestionCatalogues;
import fr.iut.miniprojet.controlers.Observateur;

public class FenetreAccueil extends JFrame implements ActionListener, Observateur {

	//Définit la méthode de stockage à employer
	private ControllerGestionCatalogues controllerCatalogues;
	
	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;

	public FenetreAccueil() {
		this.controllerCatalogues = new ControllerGestionCatalogues();
		
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);
		
		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);
		
		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);
		
		panGestionCatalogues.setLayout (new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");
		
		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");
				
		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

		btAjouter.addActionListener(this);
		btSupprimer.addActionListener(this);
		btSelectionner.addActionListener(this);
		
		//String[] tab  = {"Formacia" , "Le Redoutable", "Noitaicossa"}; 
		String[] tab = this.controllerCatalogues.getNomsCatalogues();
		modifierListesCatalogues(tab);
		
		//String[] tab2 = {"Formacia : 6 produits" , "Le Redoutable : 4 produits" , "Noitaicossa : 0 produits" };
		String[] tab2 = this.controllerCatalogues.getDetailCatalogues();
		modifierDetailCatalogues(tab2);
		modifierNbCatalogues(tab.length);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		//Création d'un nouveau Catalogue
		if (e.getSource() == btAjouter) {
			String texteAjout = txtAjouter.getText();
			
			if (!texteAjout.equals("")) {
				System.out.println("Ajouter le catalogue "+texteAjout);
				this.controllerCatalogues.creerNouveauCatalogue(texteAjout);
				txtAjouter.setText(null);
			}
		}
		
		//Suppression du catalogue sélectionné
		if (e.getSource() == btSupprimer) {
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null) {
				System.out.println("supprime catalogue "+texteSupprime);
				this.controllerCatalogues.supprimerCatalogue(texteSupprime);
			}
		}
		
		//Démarrer l'application avec un catalogue en particulier
		if (e.getSource() == btSelectionner) {
			String texteSelection = (String)cmbSupprimer.getSelectedItem();
			
			if (texteSelection != null) {
				System.out.println("selectionne catalogue "+texteSelection);
				new FenetrePrincipale(this.controllerCatalogues.selectionnerCatalogue(texteSelection));
				this.dispose();
			}
		}	
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		if (nomsCatalogues != null) {
			for (int i=0 ; i<nomsCatalogues.length; i++) {
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
		}
	}
	
	private void modifierNbCatalogues(int nb) {
		lbNbCatalogues.setText(nb + " catalogues");
	}
	
	private void modifierDetailCatalogues(String[] detailCatalogues) {
		taDetailCatalogues.setText("");
		if (detailCatalogues != null) {
			for (int i=0 ; i<detailCatalogues.length; i++)
				taDetailCatalogues.append(detailCatalogues[i]+"\n");
		}
	}
	
	public static void main(String[] args) {
		FenetreAccueil fenetre_accueil = new FenetreAccueil();
		fenetre_accueil.controllerCatalogues.attach(fenetre_accueil);
	}

	
	//Appelé dès que l'on ajoute ou retire un catalogue de la liste pour pouvoir mettre à jour la liste
	@Override
	public void maj(String[] nomsCatalogues) {
		String[] tab = this.controllerCatalogues.getNomsCatalogues();
		modifierListesCatalogues(tab);
		
		//String[] tab2 = {"Formacia : 6 produits" , "Le Redoutable : 4 produits" , "Noitaicossa : 0 produits" };
		String[] tab2 = this.controllerCatalogues.getDetailCatalogues();
		modifierDetailCatalogues(tab2);
		modifierNbCatalogues(tab.length);
	}
}
