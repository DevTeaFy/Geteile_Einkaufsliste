package de.ge.utils;

public enum Tabellen {
	
	User("User"),
	Gruppen("Gruppen"),
	U_IN_G("U_IN_G"),
	Einkaufslisten("Einkaufslisten"),
	User_hat_listen("User_hat_listen"),
	Gruppe_hat_listen("Gruppe_hat_listen"),
	Artikel("Artikel"),
	Listen_Inhalte("Listen_Inhalte"),
	User_Send_Gruppen_Invite("User_Send_Gruppen_Invite");

	private String name;
	
	Tabellen(String Tabellenname) {
		this.name = Tabellenname;
	}
	
	public String getName() {
		return name;
	}
	
	

}
