package de.ge.utils;

public enum Wert {
	
	UserID("UserID"),
	Benutzername("Benutzername"),
	Nachname("Nachname"),
	Vorname("Vorname"),
	Geburtsdatum("Geburtsdatum"),
	Password("Password"),
	GruppenID("GruppenID"),
	GruppenName("GruppenName"),
	ListenID("ListenID"),
	Listenname("Listenname"),
	ArtikelID("ArtikelID"),
	ArtikelName("ArtikelName"),
	Bezeichnung("Bezeichnung"),
	Link("Link"),
	Typ("Typ"),
	Preis("Preis"),
	Menge("Menge"),
	InvitedUserID("InvitedUserID"),
	ErstellerID("ErstellerID");
	
	
	private String name;
	
	private Wert(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
