package de.ge.utils;

public enum Tabellen {
	
	User("User"),
	Gruppen("Gruppen"),
	U_IN_G("U_IN_G"),
	User_hat_Listen("User_hat_listen"),
	Einkaufslisten("Einkaufslisten"),
	User_hat_listen("User_hat_listen"),
	Gruppe_hat_listen("Gruppe_hat_listen"),
	Artikel("Artikel"),
	Listen_Inhalte("Listen_Inhalte");

	private String name;
	
	Tabellen(String Tabellenname) {
		this.name = Tabellenname;
	}
	
	public String getName() {
		return name;
	}
	
	

}
