package de.ge.user;

import java.util.Random;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.mysql.MySQL;
import de.ge.utils.Utils;

public class User {
	
	private int iD;
	private String name;
	private String vorname;
	private String passwort;
	
	
	public User(int id, String pw) {
		login(id, pw);
	}
	
	public User login(int id, String pw) {
		
		
		return this;
	}
	
	public static void createUser(String name , String vorname, long geburtsdatum, String pw) {
		MySQL mysql = Geteilte_Einkaufsliste.getMySQL();
		int id = new Random().nextInt(999999);
		do {
			id = new Random().nextInt(999999);
		}while(id < 99999);
			
		if(!mysql.userIDExists(id)) {
			if(Utils.debug) {
				System.out.println("First Try Neue ID");
			}
			mysql.update("INSERT INTO User(ID,Name,Vorname,Geburtsdatum,Password) VALUES ("+id+",'"+name+"','"+vorname+"','"+geburtsdatum+"','"+pw+"')");
			
		}else {
			do {
				do {
					id = new Random().nextInt(999999);
				}while(id < 99999);
			}while(mysql.userIDExists(id));
			if(Utils.debug) {
				System.out.println("Secound Try Neue ID");
			}
			mysql.update("INSERT INTO User(ID,Name,Vorname,Geburtsdatum,Password) VALUES ("+id+",'"+name+"','"+vorname+"','"+geburtsdatum+"','"+pw+"')");
			
		}
	}
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

	
	
}
