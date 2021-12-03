package de.ge.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
	
	private String username = "";
	private String password = "";
	private String database = "";
	private String host = "";
	private String port = "";
	private Connection con = null;
	
	
	
	public MySQL() {
		
	}

	public void ConnectMySQL() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
			System.out.println("Datenbank erfolgreich verbunden!");
		} catch (SQLException e) {
			System.out.println("Datenbank nicht verbunden!");
			e.printStackTrace();
			return;
		} 
	}

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Connection getCon() {
		return con;
	}
	private void setCon(Connection con) {
		this.con = con;
	}
}
