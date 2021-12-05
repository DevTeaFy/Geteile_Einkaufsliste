package de.ge.utils;

public enum Tabellen {
	
	USER("User"),
	U_IN_G("U_IN_G");

	private String name;
	
	Tabellen(String Tabellenname) {
		this.name = Tabellenname;
	}
	
	public String getName() {
		return name;
	}
	
	

}
