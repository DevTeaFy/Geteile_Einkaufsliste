package de.ge.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import de.ge.utils.PrettyColor;

public class ArtikelScreen {
	
	private JFrame frame = new JFrame();
	private JLabel lblArtikel = new JLabel();
	private JLabel lblBezeichnung = new JLabel();
	private JLabel lblStkZahl = new JLabel();
	private JLabel lblPreisje1 = new JLabel();
	private JLabel lblURLLink = new JLabel();
	private JTextField tfArtikel = new JTextField();
	private JTextField tfBezeichnung = new JTextField();
	private JTextField tfSTKzahl = new JTextField();
	private JTextField tfPreisje1 = new JTextField();
	private JTextField tfURLLINK = new JTextField();
	private JButton btnHinzufuegen = new JButton();
	private JButton btnBearbeiten = new JButton();
	private JButton btnLoeschen = new JButton();
	private JButton btnClear = new JButton();
	private JButton btnZurück = new JButton();
	private MainScreen mainScreen = null;

	public ArtikelScreen(String name, MainScreen mainscreen) {
		this.mainScreen = mainscreen;
		this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 640;
		int frameHeight = 327;
		this.frame.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - this.frame.getSize().width) / 2;
		int y = (d.height - this.frame.getSize().height) / 2;
		this.frame.setLocation(x, y);
		this.frame.setTitle("ArtikelScreen -> " + name);
		this.frame.setResizable(false);
		Container cp = this.frame.getContentPane();
		cp.setBackground(PrettyColor.BLUE);
		cp.setLayout(null);

		lblArtikel.setBounds(24, 56, 110, 27);
		lblArtikel.setText("Artikel");
		lblArtikel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblArtikel.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtikel.setBackground(PrettyColor.LITHEBLUE);
		lblArtikel.setForeground(PrettyColor.WHITE);
		lblArtikel.setOpaque(true);
		cp.add(lblArtikel);
		lblBezeichnung.setBounds(24, 88, 110, 23);
		lblBezeichnung.setText("Bezeichnung");
		lblBezeichnung.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblBezeichnung.setHorizontalAlignment(SwingConstants.CENTER);
		lblBezeichnung.setBackground(PrettyColor.LITHEBLUE);
		lblBezeichnung.setForeground(PrettyColor.WHITE);
		lblBezeichnung.setOpaque(true);
		cp.add(lblBezeichnung);
		lblStkZahl.setBounds(24, 120, 110, 23);
		lblStkZahl.setText("Stk. Zahl");
		lblStkZahl.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblStkZahl.setHorizontalAlignment(SwingConstants.CENTER);
		lblStkZahl.setBackground(PrettyColor.LITHEBLUE);
		lblStkZahl.setForeground(PrettyColor.WHITE);
		lblStkZahl.setOpaque(true);
		cp.add(lblStkZahl);
		lblPreisje1.setBounds(24, 152, 110, 23);
		lblPreisje1.setText("Preis je. 1");
		lblPreisje1.setBackground(PrettyColor.LITHEBLUE);
		lblPreisje1.setForeground(PrettyColor.WHITE);
		lblPreisje1.setOpaque(true);
		lblPreisje1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblPreisje1.setHorizontalAlignment(SwingConstants.CENTER);
		cp.add(lblPreisje1);
		lblURLLink.setBounds(24, 184, 110, 23);
		lblURLLink.setText("URL-Link");
		lblURLLink.setBackground(PrettyColor.LITHEBLUE);
		lblURLLink.setForeground(PrettyColor.WHITE);
		lblURLLink.setOpaque(true);
		lblURLLink.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblURLLink.setHorizontalAlignment(SwingConstants.CENTER);
		cp.add(lblURLLink);
		tfArtikel.setBounds(136, 64, 150, 20);
		cp.add(tfArtikel);
		tfBezeichnung.setBounds(136, 96, 150, 20);
		cp.add(tfBezeichnung);
		tfSTKzahl.setBounds(136, 128, 150, 20);
		cp.add(tfSTKzahl);
		tfPreisje1.setBounds(136, 160, 150, 20);
		cp.add(tfPreisje1);
		tfURLLINK.setBounds(136, 192, 150, 20);
		cp.add(tfURLLINK);
		btnHinzufuegen.setBounds(312, 96, 91, 33);
		btnHinzufuegen.setText("Hinzufügen");
		btnHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnHinzufuegen_ActionPerformed(evt);
			}
		});
		btnHinzufuegen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnHinzufuegen.setBackground(PrettyColor.LITHEBLUE);
		btnHinzufuegen.setForeground(PrettyColor.WHITE);
		cp.add(btnHinzufuegen);
		btnBearbeiten.setBounds(432, 96, 91, 33);
		btnBearbeiten.setText("Bearbeiten");
		btnBearbeiten.setMargin(new Insets(2, 2, 2, 2));
		btnBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnBearbeiten_ActionPerformed(evt);
			}
		});
		btnBearbeiten.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnBearbeiten.setBackground(PrettyColor.LITHEBLUE);
		btnBearbeiten.setForeground(PrettyColor.WHITE);
		cp.add(btnBearbeiten);

		btnLoeschen.setBounds(312, 152, 91, 33);
		btnLoeschen.setText("Löschen");
		btnLoeschen.setMargin(new Insets(2, 2, 2, 2));
		btnLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnLoeschen_ActionPerformed(evt);
			}
		});
		btnLoeschen.setBackground(PrettyColor.LITHEBLUE);
		btnLoeschen.setForeground(PrettyColor.WHITE);
		btnLoeschen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnLoeschen);

		btnClear.setBounds(432, 152, 91, 33);
		btnClear.setText("Clear");
		btnClear.setMargin(new Insets(2, 2, 2, 2));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnClear_ActionPerformed(evt);
			}
		});
		btnClear.setBackground(PrettyColor.LITHEBLUE);
		btnClear.setForeground(PrettyColor.WHITE);
		btnClear.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnClear);

		btnZurück.setBounds(432, 220, 91, 33);
		btnZurück.setText("Zurück");
		btnZurück.setMargin(new Insets(2, 2, 2, 2));
		btnZurück.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnZurück_ActionPerformed(e);
			}
		});
		btnZurück.setBackground(PrettyColor.LITHEBLUE);
		btnZurück.setForeground(PrettyColor.WHITE);
		btnZurück.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnZurück);

		this.frame.setVisible(true);
	}

	public void btnZurück_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnZurück) {
			frame.dispose();
			new MainScreen();
		}
	}

	public void btnHinzufuegen_ActionPerformed(ActionEvent evt) {
		if (evt.getSource() == btnHinzufuegen) {

			this.mainScreen.getjTable1().getModel().setValueAt(tfArtikel.getText(), 1, 1);
//	  jTable1.getColumnModel().getColumn(1)= tfBezeichnung.getText();
//	  jTable1.getColumnModel().getColumn(2)= tfSTKzahl.getText();
//	  jTable1.getColumnModel().getColumn(3)= tfPreisje1.getText();
//	  jTable1.getColumnModel().getColumn(4) = tfURLLINK.getText();

			tfArtikel.getText();
			tfBezeichnung.getText();
			tfSTKzahl.getText();
			tfPreisje1.getText();
			tfURLLINK.getText();

		}
	}

	public void btnBearbeiten_ActionPerformed(ActionEvent evt) {

	}

	public void btnLoeschen_ActionPerformed(ActionEvent evt) {

	}

	public void btnClear_ActionPerformed(ActionEvent evt) {

	}

}
