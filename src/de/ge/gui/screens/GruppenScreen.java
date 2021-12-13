package de.ge.gui.screens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.utils.Tabellen;
import de.ge.utils.Utils;
import de.ge.utils.Wert;

public class GruppenScreen {

	private JFrame frame = new JFrame();
	private JButton btnUserEinladen = new JButton();
	private JButton btnUserEntfernen = new JButton();
	private JButton btnZurueck = new JButton();
	private JLabel lblGruppenName = new JLabel();
	private JTable jTable = new JTable(0, 5);
	private JScrollPane jTableScrollPane = new JScrollPane(jTable);
	private String Gruppenname = "";

	public GruppenScreen(int Gruppenid,String Gruppenname) {
		this.Gruppenname = Gruppenname;
		this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 603;
		int frameHeight = 477;
		this.frame.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - this.frame.getSize().width) / 2;
		int y = (d.height - this.frame.getSize().height) / 2;
		this.frame.setLocation(x, y);
		this.frame.setTitle("GruppenScreen");
		this.frame.setResizable(false);
		Container cp = this.frame.getContentPane();
		cp.setLayout(null);

		btnUserEntfernen.setBounds(423, 91, 131, 25);
		btnUserEntfernen.setText("User Entfernen");
		btnUserEinladen.setBounds(430, 126, 123, 25);
		btnUserEinladen.setText("User Einladen");
		btnUserEinladen.setMargin(new Insets(2, 2, 2, 2));
		btnUserEinladen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnUserEinladen_ActionPerformed(evt);
			}
		});
		cp.add(btnUserEinladen);
		btnZurueck.setBounds(443, 375, 75, 25);
		btnZurueck.setText("Zur�ck");
		btnZurueck.setMargin(new Insets(2, 2, 2, 2));
		btnZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnZurueck_ActionPerformed(evt);
			}
		});
		cp.add(btnZurueck);
		jTableScrollPane.setBounds(37, 78, 332, 342);
		cp.add(jTable);
		lblGruppenName.setBounds(48, 32, 110, 20);
		lblGruppenName.setText(Gruppenid+" -> "+Gruppenname);
		cp.add(lblGruppenName);
		btnUserEntfernen.setVisible(false);

		this.frame.setVisible(true);
	}

	public void btnUserEinladen_ActionPerformed(ActionEvent evt) {
		String benutzernametoinvite = JOptionPane.showInputDialog(this.frame, "Benutzername:", "Gruppen - Einladung", JOptionPane.PLAIN_MESSAGE);
		if(Geteilte_Einkaufsliste.getMySQL().benutzerNameExists(benutzernametoinvite)) {
			int id = Geteilte_Einkaufsliste.getMySQL().getInt("*", Tabellen.User, Wert.Benutzername, benutzernametoinvite, Wert.UserID);
			int gruppenid = -1;
			try {
				gruppenid = Integer.valueOf(lblGruppenName.getText().replace(" -> "+Gruppenname, ""));
				Geteilte_Einkaufsliste.getUser().inviteInGruppe(id, gruppenid);
			}catch (NumberFormatException e) {
				if(Utils.debug)
					e.printStackTrace();
			}
				
		}else {
			JOptionPane.showMessageDialog(this.frame, "Den Benutzer gibt es nicht!","Gruppen - Einladung",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void btnZurueck_ActionPerformed(ActionEvent evt) {

	}

}