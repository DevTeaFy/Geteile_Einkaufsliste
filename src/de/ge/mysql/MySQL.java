package de.ge.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.ge.utils.Tabellen;
import de.ge.utils.Utils;

public class MySQL {
	
	private String username = "";
	private String password = "";
	private String database = "";
	private String host = "";
	private String port = "";
	private Connection con = null;
	
	public MySQL() {}

	public void connectMySQL() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
			if(Utils.debug)
			System.out.println("Datenbank erfolgreich verbunden!");
			
			createTables();
		} catch (SQLException e) {
			if(Utils.debug) {
				System.out.println("Datenbank nicht verbunden!");
				e.printStackTrace();
			}
			return;
		} 
	}
	
	public void closeMySQL() {
		if(Utils.debug)
			System.out.println("Versuche die Datenbank verbindung zu trennen.");
		
		try {
			con.close();
			if(Utils.debug)
				System.out.println("Datenbank verbindung erfolgreich getrennt.");
			
		} catch (SQLException e) {
			if(Utils.debug) {
				System.out.println("Datenbank verbindung konnte NICHT getrennt werden.");
				e.printStackTrace();
			}
		}
	}
	
	public void createTables() {
		if(con == null) {
			return;
		}
		try {
			Statement st = con.createStatement();
			if(Utils.debug)
				System.out.println("Versuche Tabels anzulegen");
		
			st.executeUpdate("CREATE TABLE IF NOT EXISTS User(ID int NOT NULL AUTO_INCREMENT, Name VARCHAR(50), Vorname VARCHAR(50), Geburtsdatum Long, Password VARCHAR(50), PRIMARY KEY(ID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS U_IN_G(UserID int NOT NULL, GruppenID int NOT NULL)");
//			st.executeUpdate("CREATE TABLE IF NOT EXISTS Gruppe(GruppenID int NOT NULL AUTO_INCREMENT, Name VARCHAR(50), PRIMARY KEY(ID))");

			if(Utils.debug)
				System.out.println("Tabels erfolgreich angelegt oder noch existent");
		} catch (SQLException e) {
			if(Utils.debug) {
				System.out.println("Fehler beim Tabels anlegen");
				e.printStackTrace();
			}
		}
	}
	
	
	public String getString(String Werte,Tabellen Tabelle,String Seletion,String Value,String Typ) {
		ResultSet rs = getResult("SELECT "+Werte+" FORM "+Tabelle.getName()+" WHERE "+Seletion+"="+Value);
		try {
			String toreturn = rs.getString(Typ);
			return toreturn;
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			
			return "Error";
		}
	}
	
	
	public boolean userIDExists(int ID) {
		ResultSet rs = getResult("SELECT * FROM User WHERE ID="+ID);
		try {
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			
			return false;
		}
	}
	
	
	
	public void update(String query) {
		PreparedStatement ps = null;
		try {
			ps = this.con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				if(Utils.debug)
					e.printStackTrace();
			}
		}
	}

	public ResultSet getResult(String query) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = this.con.prepareStatement(query);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
		return null;
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
}
