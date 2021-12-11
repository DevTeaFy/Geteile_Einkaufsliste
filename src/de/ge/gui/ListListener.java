package de.ge.gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.user.User;
import de.ge.utils.Tabellen;
import de.ge.utils.Wert;

public class ListListener implements ListSelectionListener{

	private MainScreen ms;
	private MySQL mysql;
	public ListListener(MainScreen ms) {
		this.ms = ms;
		this.mysql = Geteilte_Einkaufsliste.getMySQL();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		setArtikelOutOfListInTabel(ms.getlListen().getSelectedValue().toString());
	}
	
	private void setArtikelOutOfListInTabel(String Listenname) {
//		Listenname = Listenname.replace("\\", "\\"+"\\");
		Listenname = Listenname.replace("\'", "\\\\\\"+"\'");
		Listenname = Listenname.replace("\"", "\\"+"\"");
		Listenname = Listenname.replace("[", "\\"+"[");
		Listenname = Listenname.replace("]", "\\"+"]");
		Listenname = Listenname.replace("(", "\\"+"(");
		Listenname = Listenname.replace(")", "\\"+")");
		DefaultTableModel tp = ms.getjTable1Mode();
		int listenid = mysql.getInt("*", Tabellen.Einkaufslisten, Wert.Listenname, Listenname, Wert.ListenID);
		ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.Listen_Inhalte.getName()+" WHERE "+Wert.ListenID.getName()+"="+listenid);
		try {
			while (rs.next()) {
				ResultSet inhalte = mysql.getResult("SELECT * FROM "+Tabellen.Artikel.getName()+" WHERE "+Wert.ArtikelID.getName()+"="+rs.getInt(Wert.ArtikelID.getName()));
				
				Object[] row = new Object[]{inhalte.getString(Wert.ArtikelName.getName()),inhalte.getString(Wert.Bezeichnung.getName())};
				tp.addRow(row);
				System.out.println(row.toString());
			}
		
			ms.getjTable1().setModel(tp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
