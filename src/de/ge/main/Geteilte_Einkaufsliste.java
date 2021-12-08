package de.ge.main;

import de.ge.gui.LoginScreen;
import de.ge.mysql.MySQL;

public class Geteilte_Einkaufsliste {

	private static MySQL mySQL;
	
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
	
	
	
}
