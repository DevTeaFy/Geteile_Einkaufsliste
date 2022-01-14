package de.ge.main;

import de.ge.gui.screens.LoginScreen;
import de.ge.mysql.MySQL;
import de.ge.user.User;

public class Geteilte_Einkaufsliste {

	private static MySQL mySQL;
	private static User user;
	
	public static void main(String[] args) {
		mySQL = new MySQL();
		mySQL.setDatabase("Geteilte_Einkaufsliste");
        mySQL.setHost("test.abendspieler.de");
        mySQL.setPort("3306");
        mySQL.setUsername("test");
        mySQL.setPassword("hDGvXGYMfkHTI0T5k0eu");
		new LoginScreen();
	}
	
	public static MySQL getMySQL() {
		return mySQL;
	}

	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Geteilte_Einkaufsliste.user = user;
	}
	
}
