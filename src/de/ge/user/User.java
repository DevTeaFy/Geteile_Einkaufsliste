package de.ge.user;

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
