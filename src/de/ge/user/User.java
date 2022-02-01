package de.ge.user;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import de.ge.gui.screens.LoginScreen;
import de.ge.gui.screens.MainScreen;
import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.utils.Tabellen;
import de.ge.utils.Utils;
import de.ge.utils.Verschluesselung;
import de.ge.utils.Wert;

public class User {

	private int iD;
	private String Benutzername;
	private String Nachname;
	private String vorname;
	private String passwort;
	private HashMap<String, Integer> listenid = new HashMap<>();
	private HashMap<String, Integer> gruppenlistenid = new HashMap<>();
	private HashMap<String, Integer> gruppenid = new HashMap<>();
	private static Verschluesselung vs = new Verschluesselung();

	private MySQL mysql = Geteilte_Einkaufsliste.getMySQL();

	public User(String benutzername, String pw) {
		login(benutzername, pw);
	}

	public User login(String benutzername, String pw) {
		if (Utils.debug)
			System.out.println("------------- Login versuch -------------");

		if (!Utils.hasInternetConnection()) {
			JOptionPane.showMessageDialog(null, "Du hast kein Internet.", "Internet", JOptionPane.ERROR_MESSAGE);
		}

		if (Geteilte_Einkaufsliste.getMySQL().getCon() == null) {
			JOptionPane.showMessageDialog(null, "Du hast keine verbindung zur Datenbank", "Datenbank",
					JOptionPane.ERROR_MESSAGE);
		}

		String datenbankpw = mysql.getString("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.Password);
		System.out.println(datenbankpw);
		System.out.println(pw);
		if (vs.checkPassword(pw, datenbankpw)) {
			this.iD = mysql.getInt("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.UserID);
			this.Nachname = mysql.getString("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.Nachname);
			this.vorname = mysql.getString("*", Tabellen.User, Wert.Benutzername, benutzername, Wert.Vorname);
			this.passwort = pw;
			this.Benutzername = benutzername;
			this.gruppenid = getGruppenNameHashMap();
			this.gruppenlistenid = getGruppenListenname();
			this.listenid = getListenname();
			if (Utils.debug)
				System.out.println("------------- Login Ende -------------");
			return this;
		} else {
			System.out.println("Pw falsch!!!");
			if (Utils.debug)
				System.out.println("------------- Login Ende -------------");
			return null;
		}

	}

	public int getGruppenIDByName(String GruppenName) {
		if (gruppenid.containsKey(GruppenName)) {
			return gruppenid.get(GruppenName);
		} else {
			return -1;
		}
	}

	public int getListenIDByName(String Listenname) {
		if (listenid.containsKey(Listenname)) {
			return listenid.get(Listenname);
		} else {
			return -1;
		}
	}

	public int getGruppenListenIDByName(String Listenname) {
		if (gruppenlistenid.containsKey(Listenname)) {
			return gruppenlistenid.get(Listenname);
		} else {
			return -1;
		}
	}

	public void inviteInGruppe(int UserIDToInvite, int GruppenID) {
		try {
			ResultSet invites1 = mysql.getResult("SELECT * FROM " + Tabellen.User_Send_Gruppen_Invite.getName()
					+ " WHERE " + Wert.UserID.getName() + "=" + this.iD + "");
			if (!invites1.next()) {
				creatinviteinDB(UserIDToInvite, GruppenID);
				return;
			}

			ResultSet invites = mysql.getResult("SELECT * FROM " + Tabellen.User_Send_Gruppen_Invite.getName()
					+ " WHERE " + Wert.UserID.getName() + "=" + this.iD + "");
			while (invites.next()) {
				ResultSet UserIDToInvitedb = mysql
						.getResult("SELECT * FROM " + Tabellen.User_Send_Gruppen_Invite.getName() + " WHERE "
								+ Wert.UserID.getName() + "=" + this.iD);
				HashMap<Integer, Integer> map = new HashMap<>();
				while (UserIDToInvitedb.next()) {
					map.put(UserIDToInvitedb.getInt(Wert.InvitedUserID.getName()),
							UserIDToInvitedb.getInt(Wert.GruppenID.getName()));
				}
				if (map.containsKey(UserIDToInvite)) {
					int gruppenid = map.get(UserIDToInvite);
					if (GruppenID != gruppenid) {
						creatinviteinDB(UserIDToInvite, GruppenID);
					} else {
						JOptionPane.showMessageDialog(null, "Du hast den Benutzer schon in diese Gruppe Eingeladen.",
								"Gruppen - Einladung", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					creatinviteinDB(UserIDToInvite, GruppenID);
				}
			}
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();
		}
	}

	private void creatinviteinDB(int UserIDToInvite, int GruppenID) {
		if (Utils.debug)
			System.out.println(
					"User hat eine Anfrage an " + UserIDToInvite + " für die Gruppe " + GruppenID + " gesendet");
		JOptionPane.showMessageDialog(null, "Du hast den Benutzer " + UserIDToInvite + " in diese Gruppe Eingeladen.",
				"Gruppen - Einladung", JOptionPane.INFORMATION_MESSAGE);
		try {
			mysql.getCon().createStatement()
					.executeUpdate("INSERT INTO " + Tabellen.User_Send_Gruppen_Invite.getName()
							+ "(SendID,UserID,InvitedUserID,GruppenID) VALUES (Null," + this.iD + "," + UserIDToInvite
							+ "," + GruppenID + ")");
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();
		}
	}

	public void deleteEinkaufsliste(String Listenname) {
		Listenname = QuoteForMySQL(Listenname);
		try {
			String wtf = "SELECT * FROM " + Tabellen.Einkaufslisten.getName() + " WHERE " + Wert.Listenname.getName()
					+ "='" + Listenname + "' AND " + Wert.UserID.getName() + "=" + this.iD;
			ResultSet rs = mysql.getResult(wtf);
			if (rs.next()) {
				int id = rs.getInt(Wert.ListenID.getName());
//				ResultSet rs1 = mysql.getResult("SELECT * FROM "+Tabellen.Listen_Inhalte.getName()+" WHERE "+Wert.ListenID.getName()+"="+id);
//				while(rs1.next()) {
//					mysql.update("DELETE FROM "+Tabellen.Listen_Inhalte.getName()+" WHERE "+Wert.ListenID.getName()+"="+rs1.getInt(Wert.ListenID.getName()));
//				} GEHT DAS SO=?
				mysql.update("DELETE FROM " + Tabellen.User_hat_listen.getName() + " WHERE " + Wert.ListenID.getName()
						+ "=" + id);
				mysql.update("DELETE FROM " + Tabellen.Einkaufslisten.getName() + " WHERE " + Wert.ListenID.getName()
						+ "=" + id);
			} else {
				return;
			}
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();
		}
	}

	public void acceptGruppenInvite(int GruppenID) {
		ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.User_Send_Gruppen_Invite.getName()+" WHERE "+Wert.InvitedUserID.getName()+"="+getiD()+" AND "+Wert.GruppenID.getName()+"="+GruppenID);
		try {
			if(rs.next()) {
				int gruppenid = rs.getInt(Wert.GruppenID.getName());
				JoinGroup(gruppenid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//	public void acceptGruppenInvite(int GruppenID, int DerderEingeladenHatUserID) {
//		try {
//			mysql.getCon().createStatement().executeUpdate(
//			"INSERT INTO " + Tabellen.U_IN_G.getName() + "(UserID, GruppenID) VALUES (" + this.getiD() + "," + GruppenID + ")");
//			try {
//			mysql.getCon().createStatement().executeUpdate(
//			"DELETE FROM " + Tabellen.User_Send_Gruppen_Invite.getName() + " WHERE " + Wert.InvitedUserID.getName() + "=" + this.getiD() + " AND " + Wert.UserID.getName() + "=" + DerderEingeladenHatUserID + " AND " + Wert.GruppenID.getName() + "=" + GruppenID);
//			} catch (SQLException e) {
//				if(Utils.debug) {
//					System.out.println("Invite wurde nicht deletet!");
//					e.printStackTrace();
//				}
//				return;
//			}
//		} catch (SQLException e) {
//			if(Utils.debug) {
//				System.out.println("Gruppe konnte nicht beigetreten werden!");
//				e.printStackTrace();
//			}
//			return;
//		}

	}

	public void denyGruppenInvite(int GruppenID, int DerderEingeladenHatUserID) {
		mysql.update("DELETE FROM " + Tabellen.User_Send_Gruppen_Invite.getName() + " WHERE "
				+ Wert.InvitedUserID.getName() + "=" + this.getiD() + " AND " + Wert.UserID.getName() + "="
				+ DerderEingeladenHatUserID + " AND " + Wert.GruppenID.getName() + "=" + GruppenID);
	}


	public void revokeGruppenInvite(int invitedID,int GruppenID) {
		ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.User_Send_Gruppen_Invite.getName()+" WHERE "+Wert.InvitedUserID.getName()+"="+invitedID+" AND "+Wert.GruppenID.getName()+"="+GruppenID);
		try {
			if(rs.next()) {
				mysql.update("DELETE FROM "+Tabellen.User_Send_Gruppen_Invite.getName()+" WHERE "+Wert.InvitedUserID.getName()+"="+invitedID+" AND "+Wert.GruppenID.getName()+"="+GruppenID);
			}else {
				JOptionPane.showMessageDialog(null, "Du hast einen Error in deinem Syntax!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public void revokeGruppenInvite(int GruppenID, int InvitedUserID) {
//		mysql.update("DELETE FROM " + Tabellen.User_Send_Gruppen_Invite.getName() + " WHERE " + Wert.UserID.getName()
//				+ "=" + this.getiD() + " AND " + Wert.InvitedUserID.getName() + "=" + InvitedUserID + " AND "
//				+ Wert.GruppenID.getName() + "=" + GruppenID);

//	}

	public ArrayList<String> getListennameAsArrayList() {
		ArrayList<Integer> idliste = new ArrayList<>();
		ArrayList<String> nameliste = new ArrayList<>();
		try {
			ResultSet rs = mysql.getResult("SELECT * FROM " + Tabellen.User_hat_listen.getName() + " WHERE "
					+ Wert.UserID.getName() + "=" + this.iD);
			while (rs.next()) {
				idliste.add(rs.getInt(Wert.ListenID.getName()));
			}
			for (int i = 0; i < idliste.size(); i++) {
				nameliste.add(
						mysql.getString("*", Tabellen.Einkaufslisten, Wert.ListenID, idliste.get(i), Wert.Listenname));
			}
			return nameliste;
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();

			return null;
		}
	}

	public ArrayList<String> getGruppenListennameAsArrayList() {
		ArrayList<Integer> gruppenidliste = getGruppenIDs();
		ArrayList<String> nameliste = new ArrayList<>();
		ArrayList<Integer> listederlistenids = new ArrayList<>();
		for (int i = 0; i < gruppenidliste.size(); i++) {
			listederlistenids.add(mysql.getInt("*", Tabellen.Gruppe_hat_listen, Wert.GruppenID, gruppenidliste.get(i),
					Wert.ListenID));
		}
		for (int i = 0; i < listederlistenids.size(); i++) {
			nameliste.add(mysql.getString("*", Tabellen.Einkaufslisten, Wert.ListenID, listederlistenids.get(i),
					Wert.Listenname));
		}
		return nameliste;
	}

	private HashMap<String, Integer> getListenname() {
		ArrayList<Integer> idliste = new ArrayList<>();
		HashMap<String, Integer> nameliste = new HashMap<>();
		try {
			ResultSet rs = mysql.getResult("SELECT * FROM " + Tabellen.User_hat_listen.getName() + " WHERE "
					+ Wert.UserID.getName() + "=" + this.iD);
			while (rs.next()) {
				idliste.add(rs.getInt(Wert.ListenID.getName()));
			}
			for (int i = 0; i < idliste.size(); i++) {
				nameliste.put(
						mysql.getString("*", Tabellen.Einkaufslisten, Wert.ListenID, idliste.get(i), Wert.Listenname),
						idliste.get(i));
			}
			return nameliste;
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();

			return null;
		}
	}

	private HashMap<String, Integer> getGruppenListenname() {
		ArrayList<Integer> gruppenidliste = getGruppenIDs();
		HashMap<String, Integer> nameliste = new HashMap<>();
		ArrayList<Integer> listederlistenids = new ArrayList<>();
		for (int i = 0; i < gruppenidliste.size(); i++) {
			listederlistenids.add(mysql.getInt("*", Tabellen.Gruppe_hat_listen, Wert.GruppenID, gruppenidliste.get(i),
					Wert.ListenID));
		}
		for (int i = 0; i < listederlistenids.size(); i++) {
			nameliste.put(mysql.getString("*", Tabellen.Einkaufslisten, Wert.ListenID, listederlistenids.get(i),
					Wert.Listenname), listederlistenids.get(i));
		}
		return nameliste;
	}

	private HashMap<String, Integer> getGruppenNameHashMap() {
		ArrayList<Integer> gruppenidliste = getGruppenIDs();
		HashMap<String, Integer> nameliste = new HashMap<>();
		for (int i = 0; i < gruppenidliste.size(); i++) {
			String gruppenName = mysql.getString("*", Tabellen.Gruppen, Wert.GruppenID, gruppenidliste.get(i),
					Wert.GruppenName);
			nameliste.put(gruppenName, gruppenidliste.get(i));
		}
		return nameliste;
	}

	public ArrayList<Integer> getGruppenIDs() {
		ArrayList<Integer> gruppenidliste = new ArrayList<>();
		try {
			ResultSet rs = mysql.getResult(
					"SELECT * FROM " + Tabellen.U_IN_G.getName() + " WHERE " + Wert.UserID.getName() + "=" + this.iD);
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
		listenname = listenname.replace("\'", "\\" + "\'");
		listenname = listenname.replace("\"", "\\" + "\"");
		listenname = listenname.replace("[", "\\" + "[");
		listenname = listenname.replace("]", "\\" + "]");
		listenname = listenname.replace("(", "\\" + "(");
		listenname = listenname.replace(")", "\\" + ")");
		ResultSet rs = mysql.getResult("SELECT * FROM " + Tabellen.Einkaufslisten.getName() + " WHERE "
				+ Wert.Listenname.getName() + "='" + listenname + "' AND " + Wert.UserID.getName() + "=" + this.iD);
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();
			return false;
		}

	}

	public static String QuoteForMySQL(String toQuote) {
		toQuote = toQuote.replace("\'", "\\\\" + "\'");
		toQuote = toQuote.replace("\"", "\\" + "\"");
		toQuote = toQuote.replace("[", "\\" + "[");
		toQuote = toQuote.replace("]", "\\" + "]");
		toQuote = toQuote.replace("(", "\\" + "(");
		toQuote = toQuote.replace(")", "\\" + ")");
		toQuote = toQuote.replace("\\", "\\" + "\\");
		return toQuote;
	}

	public void createEinkaufsliste(String listenname) {
		listenname = QuoteForMySQL(listenname);
		if (listenid.containsKey(listenname)) {
			System.out.println("Die einkaufsliste Existiert beretis.");
			return;
		} else {
			try {
				Statement st = mysql.getCon().createStatement();
				st.executeUpdate("INSERT INTO " + Tabellen.Einkaufslisten.getName()
						+ "(ListenID, GruppenID, UserID, Listenname) VALUES (Null,-1," + this.iD + ",'" + listenname
						+ "')");

				int lastid = mysql.getInt("*", Tabellen.Einkaufslisten, Wert.Listenname, listenname, Wert.ListenID);
				listenid.put(listenname, lastid);
				st.executeUpdate("INSERT INTO " + Tabellen.User_hat_listen.getName() + "(UserID, ListenID) VALUES ("
						+ this.iD + "," + lastid + ")");
			} catch (SQLException e) {
				if (Utils.debug)
					e.printStackTrace();
			}
		}
	}

	public void createGruppenEinkaufsliste(String Gruppenlistenname, int GruppenID) {
		Gruppenlistenname = QuoteForMySQL(Gruppenlistenname);
		if (gruppenlistenid.containsKey(Gruppenlistenname)) {
			System.out.println("Gruppeneinkaufsliste gibt es schon.");
			return;
		} else {
			try {
				Statement st = mysql.getCon().createStatement();
				st.executeUpdate("INSERT INTO Einkaufslisten(ListenID, GruppenID, UserID, Listenname) VALUES (Null,"
						+ GruppenID + "," + this.iD + ",'" + Gruppenlistenname + "')");
				ResultSet rs = mysql.getResult("SELECT * FROM Einkaufslisten ORDER BY ListenID DESC LIMIT 1");
				if (rs.next()) {
					int listenid = rs.getInt("ListenID");
					Statement st2 = mysql.getCon().createStatement();
					gruppenlistenid.put(Gruppenlistenname, listenid);
					st2.executeUpdate("INSERT INTO Gruppe_hat_listen(GruppenID,ListenID) VALUES (" + GruppenID + ","
							+ listenid + ")");
				}
			} catch (SQLException e) {
				if (Utils.debug)
					e.printStackTrace();
			}
		}
	}

	private void JoinGroup(int GruppenID) {
		ResultSet rs = mysql.getResult("SELECT * FROM "+Tabellen.U_IN_G.getName()+" WHERE "+Wert.UserID.getName()+"="+getiD()+" AND "+Wert.GruppenID.getName()+"="+GruppenID);
		try {
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Du bist schon in dieser Gruppe seltsam...");
			}else {
				mysql.update("DELETE FROM "+Tabellen.User_Send_Gruppen_Invite.getName()+" WHERE "+Wert.GruppenID.getName()+"="+GruppenID+" AND "+Wert.UserID.getName()+"="+getiD());
				mysql.update("INSERT INTO "+Tabellen.U_IN_G.getName()+"(UserID,GruppenID) VALUES ('"+getiD()+"','"+GruppenID+"')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int createGruppe(String Gruppenname) {
		Gruppenname = QuoteForMySQL(Gruppenname);
		try {
			ResultSet rs = mysql.getResult("SELECT * FROM " + Tabellen.Gruppen.getName() + " WHERE "
					+ Wert.GruppenName.getName() + "='" + Gruppenname + "'");
			boolean isok = true;
			if (rs.next()) {
				while (rs.next()) {
					int userid = rs.getInt(Wert.ErstellerID.getName());
					if (userid == this.iD) {
						JOptionPane.showMessageDialog(null,
								"Du hast bereits eine Gruppe mit dem Namen:" + Gruppenname + " ertsellt.", "Gruppen",
								JOptionPane.INFORMATION_MESSAGE);
						isok = false;
						if (Utils.debug)
							System.out.println(
									"Der User hat bereits eine Gruppe mit dem Namen:" + Gruppenname + " ertsellt.");

						break;
					}
				}
			}
			if (isok) {
				try {
					Statement st = mysql.getCon().createStatement();
					st.executeUpdate("INSERT INTO " + Tabellen.Gruppen.getName()
							+ "(GruppenID, GruppenName, ErstellerID) VALUES (Null,'" + Gruppenname + "','" + this.iD
							+ "')");
					ResultSet rs1 = mysql.getResult("SELECT * FROM Gruppen ORDER BY GruppenID DESC LIMIT 1");
					int toreturn = -1;
					if (rs1.next()) {
						toreturn = rs1.getInt(Wert.GruppenID.getName());
					}
					gruppenid.put(Gruppenname, toreturn);
					st.executeUpdate("INSERT INTO " + Tabellen.U_IN_G.getName() + "(UserID, GruppenID) VALUE ("
							+ this.iD + "," + toreturn + ")");

					return toreturn;
				} catch (SQLException e) {
					if (Utils.debug)
						e.printStackTrace();
					return -1;
				}
			} else {
				return rs.getInt(Wert.GruppenID.getName());
			}
		} catch (SQLException e1) {
			if (Utils.debug)
				e1.printStackTrace();
			return -1;
		}
	}

	public static void createUser(String Benutzername, String name, String vorname, long geburtsdatum, String pw) {
		Benutzername = QuoteForMySQL(Benutzername);
		name = QuoteForMySQL(name);
		vorname = QuoteForMySQL(vorname);
		System.out.println(pw);
		pw = vs.hash(pw);
		System.out.println(pw);

		MySQL mysql = Geteilte_Einkaufsliste.getMySQL();
		int id = new Random().nextInt(999999);
		do {
			id = new Random().nextInt(999999);
		} while (id < 99999);

		if (!User.userIDExists(id)) {
			if (Utils.debug) {
				System.out.println("First Try Neue ID");
			}
			mysql.update("INSERT INTO User(UserID,Benutzername,Nachname,Vorname,Geburtsdatum,Password) VALUES (" + id
					+ ",'" + Benutzername + "','" + name + "','" + vorname + "','" + geburtsdatum + "','" + pw + "')");
		} else {
			do {
				do {
					id = new Random().nextInt(999999);
				} while (id < 99999);
			} while (User.userIDExists(id));
			if (Utils.debug) {
				System.out.println("Secound Try Neue ID");
			}
			mysql.update("INSERT INTO User(UserID,Benutzername,Nachname,Vorname,Geburtsdatum,Password) VALUES (" + id
					+ ",'" + Benutzername + "','" + name + "','" + vorname + "','" + geburtsdatum + "','" + pw + "')");

		}
		listIDS();
	}

	public void createArtikel(int listenID, String Artikelname, String Bezeichnung, int menge, double preis,
			String link, String Typ) {
		Artikelname = User.QuoteForMySQL(Artikelname);
		Bezeichnung = User.QuoteForMySQL(Bezeichnung);
		link = User.QuoteForMySQL(link);
		Typ = User.QuoteForMySQL(Typ);
		try {
			Statement st = mysql.getCon().createStatement();
			st.executeUpdate("INSERT INTO Artikel(ArtikelID,ArtikelName,Bezeichnung,Link,Typ,Preis) VALUES (Null,'"
					+ Artikelname + "','" + Bezeichnung + "','" + link + "','" + Typ + "'," + preis + ")");

			int artikelid = mysql.getInt("*", Tabellen.Artikel, Wert.ArtikelName, Artikelname, Wert.ArtikelID);
			st.executeUpdate("INSERT INTO " + Tabellen.Listen_Inhalte.getName() + "(ListenID,ArtikelID,Menge) VALUES ("
					+ listenID + "," + artikelid + "," + menge + ")");
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();
		}
	}

	public static boolean userIDExists(int ID) {
		ResultSet rs = Geteilte_Einkaufsliste.getMySQL()
				.getResult("SELECT * FROM User WHERE " + Wert.UserID.getName() + "=" + ID);
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();
			return false;
		}
	}

	public static boolean benutzerNameExists(String benutzername) {
		benutzername = User.QuoteForMySQL(benutzername);
		ResultSet rs = Geteilte_Einkaufsliste.getMySQL()
				.getResult("SELECT * FROM User WHERE " + Wert.Benutzername.getName() + "='" + benutzername + "'");
		try {
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();

			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public static void listIDS() {
		ResultSet rs = Geteilte_Einkaufsliste.getMySQL().getResult("SELECT * FROM User");
		try {
			System.out.println("-------- User ---------");
			while (rs.next()) {
				System.out.println("" + rs.getInt("UserID") + " -> " + rs.getString("Benutzername") + " "
						+ String.valueOf(new Date(rs.getLong("Geburtsdatum")).toLocaleString()) + "| -> Nachname: "
						+ rs.getString("Nachname") + " Vorname: " + rs.getString("Vorname"));

			}
			System.out.println("-------- User ---------");
		} catch (SQLException e) {
			if (Utils.debug)
				e.printStackTrace();

			return;
		}
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
