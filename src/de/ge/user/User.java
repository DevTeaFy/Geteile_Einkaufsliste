package de.ge.user;

import java.util.Random;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.utils.Tabellen;
import de.ge.utils.Utils;

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
		String datenbankpw = Geteilte_Einkaufsliste.getMySQL().getString("*", Tabellen.USER, "Benutzername", benutzername, "Password");
		if(pw.equals(datenbankpw)) {
			this.iD = mysql.getInt("*", Tabellen.USER, "Benutzername", benutzername, "ID");
			this.Nachname = mysql.getString("*", Tabellen.USER, "Benutzername", benutzername, "Name");
			this.vorname = mysql.getString("*", Tabellen.USER, "Benutzername", benutzername, "Vorname");
			this.passwort = pw;
			this.Benutzername = benutzername;
			return this;
		}else {
			System.out.println("Pw falsch!!!");
			return null;
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
			mysql.update("INSERT INTO User(ID,Benutzername,Name,Vorname,Geburtsdatum,Password) VALUES ("+id+",'"+Benutzername+"','"+name+"','"+vorname+"','"+geburtsdatum+"','"+pw+"')");
		}else {
			do {
				do {
					id = new Random().nextInt(999999);
				}while(id < 99999);
			}while(mysql.userIDExists(id));
			if(Utils.debug) {
				System.out.println("Secound Try Neue ID");
			}
			mysql.update("INSERT INTO User(ID,Benutzername,Name,Vorname,Geburtsdatum,Password) VALUES ("+id+",'"+Benutzername+"','"+name+"','"+vorname+"','"+geburtsdatum+"','"+pw+"')");
			
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
