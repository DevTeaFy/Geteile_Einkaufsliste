package de.ge.gui.screens;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import de.ge.gui.listener.CloseWindowListener;
import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.utils.PrettyColor;
import de.ge.utils.Tabellen;
import de.ge.utils.Wert;

public class InviteScreen {
	
	private JFrame frame = new JFrame();
	private JButton btnGruppeertellen = new JButton();
	private JTable jTable = new JTable(5, 3);// Zeilen / Spalten
	private JScrollPane jTable1ScrollPane = new JScrollPane(jTable);
	private JList lListen = new JList();
	private JScrollPane lListenScrollPane = new JScrollPane(lListen);
	private JButton btnZurueck = new JButton();
	private JButton btnEinladungannehmen = new JButton();
	private JButton btnEinladungabehlen = new JButton();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JRadioButton rbEinladungen = new JRadioButton();
	private JRadioButton rbGesendeteEinladungen = new JRadioButton();
	private JButton btnWiederrufen = new JButton();

	public InviteScreen() {
		this.frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    this.frame.addWindowListener(new CloseWindowListener());
		int frameWidth = 430;
		int frameHeight = 440;
		this.frame.setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - this.frame.getSize().width) / 2;
		int y = (d.height - this.frame.getSize().height) / 2;
		this.frame.setLocation(x, y);
		this.frame.setTitle("EinladungsScreen");
		this.frame.setResizable(false);
		Container cp = this.frame.getContentPane();
		cp.setBackground(PrettyColor.BLUE);
		cp.setLayout(null);

		btnGruppeertellen.setBounds(256, 32, 115, 25);
		btnGruppeertellen.setText("Gruppeerterstellen");
		btnGruppeertellen.setMargin(new Insets(2, 2, 2, 2));
		btnGruppeertellen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnGruppeertellen_ActionPerformed(evt);
			}
		});

		btnGruppeertellen.setBorderPainted(false);
		btnGruppeertellen.setContentAreaFilled(false);
		cp.add(btnGruppeertellen);
		
		jTable.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				btnEinladungabehlen.hide();
				btnEinladungannehmen.hide();
				return;
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				btnEinladungannehmen.show();
				btnEinladungabehlen.show();
				return;
			}
		});
		jTable1ScrollPane = new JScrollPane(jTable);
		jTable1ScrollPane.setBounds(24, 24, 204, 358);
		cp.add(jTable1ScrollPane);
		setInvitesInToTable(Geteilte_Einkaufsliste.getUser().getiD());

		
		btnEinladungannehmen.setBounds(245, 183, 139, 25);
		btnEinladungannehmen.setText("Einladung annehmen");
		btnEinladungannehmen.setMargin(new Insets(2, 2, 2, 2));
		btnEinladungannehmen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEinladungannehmen_ActionPerformed(evt);
			}
		});
		btnEinladungannehmen.setVisible(false);
		btnEinladungannehmen.setBorderPainted(false);
		btnEinladungannehmen.setContentAreaFilled(false);
		cp.add(btnEinladungannehmen);
		btnEinladungabehlen.setBounds(242, 211, 139, 25);
		btnEinladungabehlen.setText("Einladung abehlen");
		btnEinladungabehlen.setMargin(new Insets(2, 2, 2, 2));
		btnEinladungabehlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEinladungabehlen_ActionPerformed(evt);
			}
		});
		btnEinladungabehlen.setBorderPainted(false);
		btnEinladungabehlen.setContentAreaFilled(false);
		btnEinladungabehlen.setVisible(false);
		cp.add(btnEinladungabehlen);
		rbEinladungen.setBounds(252, 120, 100, 20);
		rbEinladungen.setOpaque(false);
		rbEinladungen.setText("Einladungen");
		buttonGroup1.add(rbEinladungen);
		cp.add(rbEinladungen);
		rbGesendeteEinladungen.setBounds(247, 139, 140, 20);
		rbGesendeteEinladungen.setOpaque(false);
		buttonGroup1.add(rbGesendeteEinladungen);
		rbGesendeteEinladungen.setText("Gesendete Einladungen");
		cp.add(rbGesendeteEinladungen);
		btnWiederrufen.setBounds(252, 244, 123, 25);
		btnWiederrufen.setText("Wiederrufen");
		btnWiederrufen.setMargin(new Insets(2, 2, 2, 2));
		btnWiederrufen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnWiederrufen_ActionPerformed(evt);
			}
		});
		btnWiederrufen.setBorderPainted(false);
		btnWiederrufen.setContentAreaFilled(false);
		btnWiederrufen.setVisible(false);
		cp.add(btnWiederrufen);

		btnZurueck.setBounds(270, 358, 107, 25);
		btnZurueck.setText("Zurück");
		btnZurueck.setMargin(new Insets(2, 2, 2, 2));
		btnZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnZurueck_ActionPerformed(e);
			}
		});
		btnZurueck.setBackground(PrettyColor.LITHEBLUE);
		btnZurueck.setForeground(PrettyColor.WHITE);
		btnZurueck.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		cp.add(btnZurueck);
		
		this.frame.setVisible(true);
	}

	public void btnGruppeertellen_ActionPerformed(ActionEvent evt) {
		String gruppenname = JOptionPane.showInputDialog(this.frame, "Gruppenname:", "Gruppe - Erstellen", JOptionPane.PLAIN_MESSAGE);
		if(gruppenname != null) {
			int id = Geteilte_Einkaufsliste.getUser().createGruppe(gruppenname);
			if(id != -1) {
				this.frame.dispose();
				new GruppenScreen(id,gruppenname);
			}
		}
	}

	public void btnZurueck_ActionPerformed(ActionEvent evt) {
		this.frame.dispose();
		new MainScreen();
	} 

	public void btnEinladungannehmen_ActionPerformed(ActionEvent evt) {
		int row = jTable.getSelectedRow();
		int col = jTable.getSelectedColumn();
		if(row >= 0 && col >= 0) {
			String Gruppenname = String.valueOf(jTable.getModel().getValueAt(row, 2));
			System.out.println(Gruppenname);// Berücksichtige den ersteller der Gruppe den der Name ist undeutlich könnte von mehreren Usern den selben namen haben....
			int grouppenID = Integer.valueOf(22);
			
		}
	} 

	public void btnEinladungabehlen_ActionPerformed(ActionEvent evt) {
		
	} 

	public String buttonGroup1_getSelectedRadioButtonLabel() {
		for (java.util.Enumeration<AbstractButton> e = buttonGroup1.getElements(); e.hasMoreElements();) {
			AbstractButton b = e.nextElement();
			if (b.isSelected())
				return b.getText();
		}
		return "";
	}

	public void btnWiederrufen_ActionPerformed(ActionEvent evt) {
		
	} 
	
	public DefaultTableModel getModel() {
		return (DefaultTableModel) jTable.getModel();
	}
	public JTable getjTable() {
		return jTable;
	}
	
	public void setInvitesInToTable(int UserID) {
		DefaultTableModel tp = new DefaultTableModel(new String[]{"Eingeladen von","Eingeladen","Gruppen Name"}, 0);
		
		for (int i = tp.getRowCount()-1;i>=0;i--) {
			tp.removeRow(i);
		}
		MySQL mysql = Geteilte_Einkaufsliste.getMySQL();
		ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.User_Send_Gruppen_Invite.getName()+" WHERE "+Wert.InvitedUserID.getName()+"="+UserID);
		try {
			while (rs.next()) {
				ResultSet user = mysql.getResult("SELECT * FROM "+Tabellen.User.getName()+" WHERE "+Wert.UserID.getName()+"="+rs.getInt(Wert.UserID.getName()));
				ResultSet inviteduser = mysql.getResult("SELECT * FROM "+Tabellen.User.getName()+" WHERE "+Wert.UserID.getName()+"="+rs.getInt(Wert.InvitedUserID.getName()));
				int GruppenID = rs.getInt(Wert.GruppenID.getName());
				ResultSet gruppe = mysql.getResult("SELECT * FROM "+Tabellen.Gruppen.getName()+" WHERE "+Wert.GruppenID.getName()+"="+GruppenID);
				if(user.next() && inviteduser.next() && gruppe.next()) {
				Object[] row = new Object[]{user.getString(Wert.Benutzername.getName()),inviteduser.getString(Wert.Benutzername.getName()),gruppe.getString(Wert.GruppenName.getName())};
					tp.addRow(row);
				}
			}
			getjTable().setModel(tp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
