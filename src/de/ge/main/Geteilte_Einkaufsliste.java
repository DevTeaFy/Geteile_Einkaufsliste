package de.ge.main;

import de.ge.gui.LoginScreen;
import de.ge.mysql.MySQL;

public class Geteilte_Einkaufsliste {

	private static MySQL mySQL;
	
	public static void main(String[] args) {
		new LoginScreen();
		mySQL = new MySQL();
        mySQL.setDatabase("Geteilte_Einkaufsliste");
        mySQL.setHost("test.abendspieler.de");
        mySQL.setPort("3306");
        mySQL.setUsername("test");
        mySQL.setPassword("hDGvXGYMfkHTI0T5k0eu");
      
	}

	public static MySQL getMySQL() {
		return mySQL;
	}
	
	
	
}
