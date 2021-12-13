package de.ge.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.utils.Tabellen;
import de.ge.utils.Utils;
import de.ge.utils.Wert;

public class User {
	
	private int iD;
	private String Benutzername;
	private String Nachname;
	private String vorname;
	private String passwort;

	private MySQL mysql = Geteilte_Einkaufsliste.getMySQL();
	
	
	public User(String benutzername, String pw) {
		login(benutzername, pw);
	}
	
	public User login(String benutzername, String pw) {
		String datenbankpw = mysql.getString("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.Password);
		if(pw.equals(datenbankpw)) {
			this.iD = mysql.getInt("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.UserID);
			this.Nachname = mysql.getString("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.Nachname);
			this.vorname = mysql.getString("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.Vorname);
			this.passwort = pw;
			this.Benutzername = benutzername;
			return this;
		}else {
			System.out.println("Pw falsch!!!");
			return null;
		}
	}
	
	public void inviteInGruppe(int UserIDToInvite, int GruppenID) {
		try {
			ResultSet invites = mysql.getResult("SELECT * FROM "+Tabellen.User_Send_Gruppen_Invite.getName()+" WHERE "+Wert.UserID.getName()+"="+this.iD+"");
			if(invites.next()) {
				while (invites.next()) {
					int UserIDToInvitedb = mysql.getInt("*", Tabellen.User_Send_Gruppen_Invite, Wert.UserID, this.iD, Wert.InvitetdUserID);
					if(UserIDToInvitedb == UserIDToInvite) {
						int gruppenid = mysql.getInt("*", Tabellen.User_Send_Gruppen_Invite, Wert.UserID, this.iD, Wert.GruppenID);
						if(GruppenID != gruppenid) {
							if(Utils.debug)
								System.out.println("User hat eine Anfrage an "+UserIDToInvite+" für die Gruppe "+GruppenID+" gesendet");
							mysql.getCon().createStatement().executeUpdate("INSERT INTO User_Send_Gruppen_Invite(UserID,InvitetdUserID,GruppenID) VALUES ("+this.iD+","+UserIDToInvite+","+GruppenID+")");
						}else {
							JOptionPane.showMessageDialog(null, "Du hast den Benutzer schon in diese Gruppe Eingeladen.", "Gruppen - Einladung", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {
						if(Utils.debug)
							System.out.println("User hat eine Anfrage an "+UserIDToInvite+" für die Gruppe "+GruppenID+" gesendet");
						mysql.getCon().createStatement().executeUpdate("INSERT INTO User_Send_Gruppen_Invite(UserID,InvitetdUserID,GruppenID) VALUES ("+this.iD+","+UserIDToInvite+","+GruppenID+")");
					}
				}
			}else {
				mysql.getCon().createStatement().executeUpdate(
						"INSERT INTO User_Send_Gruppen_Invite(UserID,InvitetdUserID,GruppenID) VALUES ("+ this.iD + "," + UserIDToInvite + "," + GruppenID + ")");
			}
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	public void acceptGruppenInvite() {
		
	}
	
	public void denyGruppenInvite() {
		
	}
	
	public void revokeGruppenInvite() {
		
	}
	
	public ArrayList<String> getListenname(){
		ArrayList<Integer> idliste = new ArrayList<>();
		ArrayList<String> nameliste = new ArrayList<>();
		try {
			ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.User_hat_listen.getName()+" WHERE "+Wert.UserID.getName()+"="+this.iD);
			while (rs.next()) {
				idliste.add(rs.getInt(Wert.ListenID.getName()));
			}
			for (int i = 0; i < idliste.size(); i++) {
				nameliste.add(mysql.getString("*", Tabellen.Einkaufslisten, Wert.ListenID, idliste.get(i), Wert.Listenname));
			}
			return nameliste;
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			
			return null;
		}
	}
	public ArrayList<String> getGruppenListenname(){
		ArrayList<Integer> gruppenidliste = getGruppenIDs();
		ArrayList<String> nameliste = new ArrayList<>();
		ArrayList<Integer> listederlistenids = new ArrayList<>();
		for (int i = 0; i < gruppenidliste.size(); i++) {
			listederlistenids.add(mysql.getInt("*", Tabellen.Gruppe_hat_listen, Wert.GruppenID, gruppenidliste.get(i), Wert.ListenID));
		}
		for (int i = 0; i < listederlistenids.size(); i++) {
			nameliste.add(mysql.getString("*", Tabellen.Einkaufslisten, Wert.ListenID, listederlistenids.get(i), Wert.Listenname));
		}
		return nameliste;
	}
	
	public ArrayList<String> getGruppenName(){
		ArrayList<Integer> gruppenidliste = getGruppenIDs();
		ArrayList<String> nameliste = new ArrayList<>();
		for (int i = 0; i < gruppenidliste.size(); i++) {
			nameliste.add(mysql.getString("*", Tabellen.Gruppen, Wert.GruppenID, gruppenidliste.get(i), Wert.GruppenName));
		}
		return nameliste;
	}
	
	
	public ArrayList<Integer> getGruppenIDs(){
		ArrayList<Integer> gruppenidliste = new ArrayList<>();
		try {
			ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.U_IN_G.getName()+" WHERE "+Wert.UserID.getName()+"="+this.iD);
			while (rs.next()) {
				gruppenidliste.add(rs.getInt(Wert.GruppenID.getName()));
			}
			return gruppenidliste;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public boolean hasListname(String listenname) {
//		listenname = listenname.replace("\\", "\\"+"\\");
		listenname = listenname.replace("\'", "\\"+"\'");
		listenname = listenname.replace("\"", "\\"+"\"");
		listenname = listenname.replace("[", "\\"+"[");
		listenname = listenname.replace("]", "\\"+"]");
		listenname = listenname.replace("(", "\\"+"(");
		listenname = listenname.replace(")", "\\"+")");
		ResultSet rs = mysql.getResult("SELECT * FROM Einkaufslisten WHERE "+Wert.Listenname.getName()+"='"+listenname+"' AND UserID="+this.iD);
		try {
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			return false;
		}
		
	}
	
	public static String QuoteForMySQL(String toQuote) {
		toQuote = toQuote.replace("\'", "\\\\"+"\'");
		toQuote = toQuote.replace("\"", "\\"+"\"");
		toQuote = toQuote.replace("[", "\\"+"[");
		toQuote = toQuote.replace("]", "\\"+"]");
		toQuote = toQuote.replace("(", "\\"+"(");
		toQuote = toQuote.replace(")", "\\"+")");
		toQuote = toQuote.replace("\\", "\\"+"\\");
		return toQuote;
	}
	
	public void createEinkaufsliste(String listenname) {
		listenname = QuoteForMySQL(listenname);
		try {
			Statement st = mysql.getCon().createStatement();
			st.executeUpdate("INSERT INTO Einkaufslisten(ListenID, GruppenID, UserID, Listenname) VALUES (Null,-1,"+this.iD+",'"+listenname+"')");
			
			int lastid = mysql.getInt("*", Tabellen.Einkaufslisten, Wert.Listenname, listenname, Wert.ListenID);
			st.executeUpdate("INSERT INTO "+Tabellen.User_hat_listen.getName()+"(UserID, ListenID) VALUES ("+this.iD+","+lastid+")");
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	public void createGruppenEinkaufsliste(String Gruppenlistenname,int GruppenID) {
		Gruppenlistenname = QuoteForMySQL(Gruppenlistenname);
		try {
			Statement st = mysql.getCon().createStatement();
			st.executeUpdate("INSERT INTO Einkaufslisten(ListenID, GruppenID, UserID, Listenname) VALUES (Null,"+GruppenID+","+this.iD+",'"+Gruppenlistenname+"')");
			ResultSet rs = mysql.getResult("SELECT * FROM Einkaufslisten ORDER BY ListenID DESC LIMIT 1");
			if(rs.next()) {
				int listenid = rs.getInt("ListenID");
				Statement st2 = mysql.getCon().createStatement();
				st2.executeUpdate("INSERT INTO Gruppe_hat_listen(GruppenID,ListenID) VALUES ("+GruppenID+","+listenid+")");
			}
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	
	public int createGruppe(String Gruppenname) {
		Gruppenname = QuoteForMySQL(Gruppenname);
		try {
			ResultSet rs = mysql.getResult("SELECT * FROM Gruppen WHERE "+Wert.GruppenName.getName()+"='"+Gruppenname+"'");
			boolean isok = true;
			if(rs.next()) {
				while (rs.next()) {
					int userid = rs.getInt(Wert.ErstellerID.getName());
					if(userid == this.iD) {
						JOptionPane.showMessageDialog(null,"Du hast bereits eine Gruppe mit dem Namen:"+Gruppenname+" ertsellt.", "Gruppen", JOptionPane.INFORMATION_MESSAGE);
						isok = false;
						if(Utils.debug)
							System.out.println("Der User hat bereits eine Gruppe mit dem Namen:"+Gruppenname+" ertsellt.");
						
						break;
					}
				}
			}
			if(isok) {
				try {
					Statement st = mysql.getCon().createStatement();
					st.executeUpdate("INSERT INTO Gruppen(GruppenID, GruppenName, ErstellerID) VALUES (Null,'"+Gruppenname+"','"+this.iD+"')");
					ResultSet rs1 = mysql.getResult("SELECT * FROM Gruppe ORDER BY GruppenID DESC LIMIT 1");
					int toreturn = -1;
					if(rs1.next()) {
						toreturn = rs.getInt("GruppenID");
					}
					st.executeUpdate("INSERT INTO U_IN_G(UserID, GruppenID) VALUE ("+this.iD+","+toreturn+")");
					return toreturn;
				} catch (SQLException e) {
					if(Utils.debug)
						e.printStackTrace();
					return -1;
				}
			}else {
				return rs.getInt("GruppenID");
			}
		} catch (SQLException e1) {
			if(Utils.debug)
				e1.printStackTrace();
			return -1;
		}
	}
	
	public static void createUser(String Benutzername, String name , String vorname, long geburtsdatum, String pw) {
		Benutzername = QuoteForMySQL(Benutzername);
		name = QuoteForMySQL(name);
		vorname = QuoteForMySQL(vorname);
		pw = QuoteForMySQL(pw);
		MySQL mysql = Geteilte_Einkaufsliste.getMySQL();
		int id = new Random().nextInt(999999);
		do {
			id = new Random().nextInt(999999);
		}while(id < 99999);
			
		if(!mysql.userIDExists(id)) {
			if(Utils.debug) {
				System.out.println("First Try Neue ID");
			}
			mysql.update("INSERT INTO User(UserID,Benutzername,Nachname,Vorname,Geburtsdatum,Password) VALUES ("+id+",'"+Benutzername+"','"+name+"','"+vorname+"','"+geburtsdatum+"','"+pw+"')");
		}else {
			do {
				do {
					id = new Random().nextInt(999999);
				}while(id < 99999);
			}while(mysql.userIDExists(id));
			if(Utils.debug) {
				System.out.println("Secound Try Neue ID");
			}
			mysql.update("INSERT INTO User(UserID,Benutzername,Nachname,Vorname,Geburtsdatum,Password) VALUES ("+id+",'"+Benutzername+"','"+name+"','"+vorname+"','"+geburtsdatum+"','"+pw+"')");
			
		}
		mysql.listIDS();
	}
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getNachname() {
		return Nachname;
	}
	public void setNachname(String Nachname) {
		this.Nachname = Nachname;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getBenutzername() {
		return Benutzername;
	}

	public void setBenutzername(String benutzername) {
		Benutzername = benutzername;
	}

	
	
}
