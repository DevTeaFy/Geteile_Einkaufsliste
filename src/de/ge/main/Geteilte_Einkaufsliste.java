package de.ge.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.ge.mysql.MySQL;

public class Geteilte_Einkaufsliste {

	private static MySQL mySQL;
	
	public static void main(String[] args) {
		mySQL = new MySQL();
		mySQL.setDatabase("");
		mySQL.setHost("");
		mySQL.setPort("");
		mySQL.setUsername("");
		mySQL.setPassword("");
		
		
		mySQL.ConnectMySQL();
	}

	
}
