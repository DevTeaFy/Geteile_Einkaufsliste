package de.ge.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.cj.xdevapi.PreparableStatement;

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
	
	
	public boolean hasListname(String listenname) {
		ResultSet rs = mysql.getResult("SELECT * FROM Einkaufslisten WHERE "+Wert.Listenname.getName()+"="+listenname+" AND UserID="+this.iD);
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
	
	public void createEinkaufsliste(String Listenname) {
		try {
			Statement st = mysql.getCon().createStatement();
			st.executeUpdate("INSERT INTO Einkaufslisten(ListenID, GruppenID, UserID, Listenname) VALUES (Null,-1,"+this.iD+","+Listenname+")");
			
			int lastid = mysql.getInt("*", Tabellen.Einkaufslisten, Wert.Listenname, Listenname, Wert.ListenID);
			st.executeUpdate("INSERT INTO "+Tabellen.User_hat_listen.getName()+"(UserID, ListenID) VALUES ("+this.iD+","+lastid+")");
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	public static void createUser(String Benutzername, String name , String vorname, long geburtsdatum, String pw) {
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
