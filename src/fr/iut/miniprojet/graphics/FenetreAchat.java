package fr.iut.miniprojet.graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import fr.iut.miniprojet.controlers.ControllerAchatVente;

public class FenetreAchat extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	private ControllerAchatVente controllerAchatVente;

	public FenetreAchat(String[] lesProduits, ControllerAchatVente controllerAchatVente) {

		this.controllerAchatVente = controllerAchatVente;

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btAchat) {
			if(this.combo.getSelectedItem()!=null) {
				this.controllerAchatVente.acheterProduit(this.combo.getSelectedItem().toString(), Integer.parseInt(this.txtQuantite.getText()));
			}
		}

		this.dispose();
	}

}
