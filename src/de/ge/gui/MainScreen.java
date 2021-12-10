package de.ge.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.utils.PrettyColor;

public class MainScreen {

	private JFrame frame = new JFrame();
	private JLabel lblListen = new JLabel();
	private JButton btnListeerstellen = new JButton();
	private DefaultListModel listModel = new DefaultListModel();
	private JList lListen = new JList(listModel);
	private JScrollPane lListenScrollPane = new JScrollPane(lListen);
	private JTextField tfListenName = new JTextField();
	private JButton btnListeLoeschen = new JButton();
	private JButton btnEinstellung = new JButton();
	private JButton btnHinzufuegen = new JButton();
	private JTable jTable1 = new JTable(20, 5);
	private DefaultTableModel jTable1Model = (DefaultTableModel) jTable1.getModel();
	private JScrollPane jTable1ScrollPane = new JScrollPane(jTable1);
	private JButton btnOefnnen = new JButton();
	private JButton btnZurück = new JButton();

	@SuppressWarnings("unchecked")
	public MainScreen() {

		this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 948;
		int frameHeight = 647;
		this.frame.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - this.frame.getSize().width) / 2;
		int y = (d.height - this.frame.getSize().height) / 2;
		this.frame.setLocation(x, y);
		this.frame.setTitle(Geteilte_Einkaufsliste.getUser().getBenutzername()+" -> Hallo, "+Geteilte_Einkaufsliste.getUser().getVorname());
		this.frame.setResizable(false);
		Container cp = this.frame.getContentPane();
		cp.setBackground(PrettyColor.BLUE);
		cp.setLayout(null);

		lblListen.setBounds(0, 0, 246, 79);
		lblListen.setText("Listen:");
		lblListen.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblListen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblListen.setHorizontalAlignment(SwingConstants.CENTER);
		lblListen.setBackground(PrettyColor.LITHEBLUE);
		lblListen.setForeground(PrettyColor.WHITE);
		lblListen.setOpaque(true);
		cp.add(lblListen);
		btnListeerstellen.setBounds(8, 488, 115, 33);
		btnListeerstellen.setText("Liste erstellen");
		btnListeerstellen.setMargin(new Insets(2, 2, 2, 2));
		btnListeerstellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnListeerstellen_ActionPerformed(evt);
			}
		});
		btnListeerstellen.setHorizontalTextPosition(SwingConstants.CENTER);
		btnListeerstellen.setBackground(PrettyColor.GREEN);
		btnListeerstellen.setForeground(PrettyColor.WHITE);
		btnListeerstellen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnListeerstellen);
		lListen.setModel(listModel);
		lListenScrollPane.setBounds(0, 80, 246, 340);
		lListen.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lListen.setBackground(PrettyColor.LITHEBLUE);
		lListen.setForeground(PrettyColor.WHITE);
		cp.add(lListenScrollPane);
		tfListenName.setBounds(0, 424, 246, 60);
		tfListenName.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		tfListenName.setBackground(PrettyColor.LITHEBLUE);
		tfListenName.setForeground(PrettyColor.WHITE);
		cp.add(tfListenName);
		btnListeLoeschen.setBounds(128, 488, 115, 33);
		btnListeLoeschen.setText("Liste Löschen");
		btnListeLoeschen.setMargin(new Insets(2, 2, 2, 2));
		btnListeLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnListeLoeschen_ActionPerformed(evt);
			}
		});
		btnListeLoeschen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnListeLoeschen.setBackground(PrettyColor.RED);
		btnListeLoeschen.setForeground(PrettyColor.WHITE);
		cp.add(btnListeLoeschen);
		btnEinstellung.setBounds(128, 528, 115, 33);
		btnEinstellung.setText("Einstellung");
		btnEinstellung.setMargin(new Insets(2, 2, 2, 2));
		btnEinstellung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEinstellung_ActionPerformed(evt);
			}
		});
		btnEinstellung.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnEinstellung.setBackground(PrettyColor.LITHEBLUE);
		btnEinstellung.setForeground(PrettyColor.WHITE);
		cp.add(btnEinstellung);
		jTable1ScrollPane.setBounds(264, 8, 644, 502);
		jTable1.setRowHeight(1);
		jTable1.setRowSelectionAllowed(false);
		jTable1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		jTable1.setAutoCreateRowSorter(false);
		jTable1.getColumnModel().getColumn(0).setHeaderValue("Artikel");
		jTable1.getColumnModel().getColumn(1).setHeaderValue("Bezeichnung");
		jTable1.getColumnModel().getColumn(2).setHeaderValue("Stk. Zahl");
		jTable1.getColumnModel().getColumn(3).setHeaderValue("Preis je. 1");
		jTable1.getColumnModel().getColumn(4).setHeaderValue("URL-Link");
		cp.add(jTable1ScrollPane);
		
		
		
		btnOefnnen.setBounds(8, 528, 115, 33);
		btnOefnnen.setText("Öfnnen");
		btnOefnnen.setMargin(new Insets(2, 2, 2, 2));
		btnOefnnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnOefnnen_ActionPerformed(evt);
			}
		});
		btnOefnnen.setBackground(PrettyColor.LITHEBLUE);
		btnOefnnen.setForeground(PrettyColor.WHITE);
		btnOefnnen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnOefnnen);

		btnHinzufuegen.setBounds(330, 528, 115, 33);
		btnHinzufuegen.setText("Hinzufügen");
		btnHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
		btnHinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHinzufuegen_ActionPerformed(e);
			}
		});
		btnHinzufuegen.setBackground(PrettyColor.LITHEBLUE);
		btnHinzufuegen.setForeground(PrettyColor.WHITE);
		btnHinzufuegen.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnHinzufuegen);
		
		
		btnZurück.setBounds(660, 528, 115, 33);
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
	public JTable getjTable1() {
		return jTable1;
	}
	public void setjTable1(JTable jTable1) {
		this.jTable1 = jTable1;
	}
	public void btnZurück_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnZurück) {
			frame.dispose();
			new LoginScreen();
		}
	}

	public void btnHinzufuegen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnHinzufuegen) {
			if(lListen.getSelectedValue() != null) {
				frame.dispose();
				new ArtikelScreen(lListen.getSelectedValue().toString(),this);
			}else {
				//Frag ihn ob er eine Liste anlegen möchte :P
			}
		}
	}

	public void btnListeerstellen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnListeerstellen) {
			listModel.addElement(tfListenName.getText());
		}
	}

	public void btnListeLoeschen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnListeLoeschen) {

			listModel.removeElement(tfListenName.getText());
			// listModel.removeElementAt(Integer.valueOf(tfListenName.getText()));
			// listModel.remove(index);

		}

	}

	public void btnEinstellung_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnEinstellung) {

		}
	}

	public void btnOefnnen_ActionPerformed(ActionEvent e) {
		if (e.getSource() == btnOefnnen) {

		}

	}

}
