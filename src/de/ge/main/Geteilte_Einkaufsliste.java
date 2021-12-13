package de.ge.main;

import de.ge.gui.screens.LoginScreen;
import de.ge.mysql.MySQL;
import de.ge.user.User;

public class Geteilte_Einkaufsliste {

	private static MySQL mySQL;
	private static User user;
	
	public static void main(String[] args) {
		new LoginScreen();
		mySQL = new MySQL();
		mySQL.setDatabase("");
        mySQL.setHost("");
        mySQL.setPort("");
        mySQL.setUsername("");
        mySQL.setPassword("");
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
