package de.ge.gui.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import de.ge.gui.screens.MainScreen;
import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.utils.Tabellen;
import de.ge.utils.Wert;

public class ListListener implements ListSelectionListener{

	private static MainScreen ms;
	private static MySQL mysql;
	public ListListener(MainScreen ms) {
		this.ms = ms;
		this.mysql = Geteilte_Einkaufsliste.getMySQL();
	}
	private boolean test = true;
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(test) {
			test = false;
			
		}else if(!test) {
			test = true;
		}
		
	}
	
	public static void setArtikelOutOfListInTabel(String Listenname) {
//		Listenname = Listenname.replace("\\", "\\"+"\\");
		Listenname = Listenname.replace("\'", "\\\\\\"+"\'");
		Listenname = Listenname.replace("\"", "\\"+"\"");
		Listenname = Listenname.replace("[", "\\"+"[");
		Listenname = Listenname.replace("]", "\\"+"]");
		Listenname = Listenname.replace("(", "\\"+"(");
		Listenname = Listenname.replace(")", "\\"+")");
		DefaultTableModel tp = ms.getjTable1Mode();
		
		for (int i = tp.getRowCount()-1;i>=0;i--) {
			tp.removeRow(i);
		}
		int listenid = mysql.getInt("*", Tabellen.Einkaufslisten, Wert.Listenname, Listenname, Wert.ListenID);
		ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.Listen_Inhalte.getName()+" WHERE "+Wert.ListenID.getName()+"="+listenid);
		try {
			while (rs.next()) {
				ResultSet inhalte = mysql.getResult("SELECT * FROM "+Tabellen.Artikel.getName()+" WHERE "+Wert.ArtikelID.getName()+"="+rs.getInt(Wert.ArtikelID.getName()));
				if(inhalte.next()) {
					Object[] row = new Object[]{inhalte.getString(Wert.ArtikelName.getName()),inhalte.getString(Wert.Bezeichnung.getName()),""+rs.getInt(Wert.Menge.getName()),""+inhalte.getDouble(Wert.Preis.getName()),inhalte.getString(Wert.Link.getName())};
					tp.addRow(row);
				}
			}
			ms.getjTable1().setModel(tp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
