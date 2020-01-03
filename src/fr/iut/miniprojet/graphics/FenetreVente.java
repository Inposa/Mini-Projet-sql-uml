package fr.iut.miniprojet.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iut.miniprojet.controlers.ControllerAchatVente;

public class FenetreVente extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	private ControllerAchatVente controllerAchatVente;

	public FenetreVente(String[] lesProduits, ControllerAchatVente controllerAchatVente) {

		this.controllerAchatVente = controllerAchatVente;

		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btVente) {
			if(this.combo.getSelectedItem()!=null) {
				this.controllerAchatVente.vendreProduit(combo.getSelectedItem().toString(), Integer.parseInt(txtQuantite.getText()));
			}			
		}

		this.dispose();
	}

}
