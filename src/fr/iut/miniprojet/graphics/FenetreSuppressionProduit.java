package fr.iut.miniprojet.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iut.miniprojet.controlers.ControllerCreationSupressionProduit;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	private ControllerCreationSupressionProduit controllerCreationSupressionProduit;
	
	public FenetreSuppressionProduit(String lesProduits[], ControllerCreationSupressionProduit controllerCreationSupressionProduit) {
		
		this.controllerCreationSupressionProduit = controllerCreationSupressionProduit;
		
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		//Si clic sur le bouton de suppression
		if(e.getSource() == btSupprimer) {
			if(this.combo.getSelectedItem()!=null) {
				this.controllerCreationSupressionProduit.suppressionProduit(this.combo.getSelectedItem().toString());

			}
		}
		
		this.dispose();
	}

}
