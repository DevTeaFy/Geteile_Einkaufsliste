package de.ge.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

import de.ge.main.Geteilte_Einkaufsliste;
import de.ge.user.User;
import de.ge.utils.Tabellen;
import de.ge.utils.Utils;
import de.ge.utils.Wert;

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
//			st.executeUpdate("DROP TABLE IF EXISTS User_Send_Gruppen_Invite");
//			st.executeUpdate("DROP TABLE IF EXISTS Listen_Inhalte");
//			st.executeUpdate("DROP TABLE IF EXISTS U_IN_G");
//			st.executeUpdate("DROP TABLE IF EXISTS User_hat_listen");
//			st.executeUpdate("DROP TABLE IF EXISTS Gruppe_hat_listen");
//			st.executeUpdate("DROP TABLE IF EXISTS Einkaufslisten");
//			st.executeUpdate("DROP TABLE IF EXISTS Artikel");
//			st.executeUpdate("DROP TABLE IF EXISTS User");
//			st.executeUpdate("DROP TABLE IF EXISTS Gruppen");
			
			st.executeUpdate("CREATE TABLE IF NOT EXISTS User_Send_Gruppen_Invite(SendID int NOT NULL AUTO_INCREMENT,UserID int NOT NULL, InvitetdUserID int NOT NULL, GruppenID int NOT NULL, FOREIGN KEY (UserID) REFERENCES User (UserID), PRIMARY KEY(SendID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS User(UserID int UNIQUE NOT NULL AUTO_INCREMENT, Benutzername VARCHAR(50), Nachname VARCHAR(50), Vorname VARCHAR(50), Geburtsdatum Long, Password VARCHAR(50), PRIMARY KEY(UserID), UNIQUE (Benutzername))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Gruppen(GruppenID int NOT NULL AUTO_INCREMENT, GruppenName VARCHAR(50), ErstellerID int, PRIMARY KEY(GruppenID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS U_IN_G(UserID int NOT NULL, GruppenID int NOT NULL, FOREIGN KEY (UserID) REFERENCES User (UserID), FOREIGN KEY (GruppenID) REFERENCES Gruppen (GruppenID),PRIMARY KEY (UserID,GruppenID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Einkaufslisten(ListenID int NOT NULL AUTO_INCREMENT, GruppenID int NOT NULL, UserID int NOT NULL, Listenname VARCHAR(50),PRIMARY KEY (ListenID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS User_hat_listen(UserID integer NOT NULL,ListenID integer NOT NULL, FOREIGN KEY (UserID) REFERENCES User (UserID), FOREIGN KEY (ListenID) REFERENCES Einkaufslisten (ListenID),PRIMARY KEY (UserID,ListenID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Gruppe_hat_listen(GruppenID integer NOT NULL,ListenID integer NOT NULL,FOREIGN KEY (GruppenID) REFERENCES Gruppen (GruppenID),FOREIGN KEY (ListenID) REFERENCES Einkaufslisten (ListenID),PRIMARY KEY (GruppenID,ListenID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Artikel(ArtikelID integer NOT NULL AUTO_INCREMENT,ArtikelName varchar(255),Bezeichnung TEXT,Link TEXT,Typ varchar(100), Preis DOUBLE, PRIMARY KEY (ArtikelID))");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS Listen_Inhalte(ListenID integer NOT NULL,ArtikelID integer NOT NULL, Menge integer NOT NULL, FOREIGN KEY (ListenID) REFERENCES Einkaufslisten (ListenID), FOREIGN KEY (ArtikelID) REFERENCES Artikel (ArtikelID), PRIMARY KEY (ListenID,ArtikelID))");
			if(Utils.debug)
				System.out.println("Tabels erfolgreich angelegt oder noch existent");
		} catch (SQLException e) {
			if(Utils.debug) {
				System.out.println("Fehler beim Tabels anlegen");
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public String getString(String SelectWerte,Tabellen Tabelle,Wert Where,Object Value,Wert rueckgabewert) {
		try {
			ResultSet rs = getResult("SELECT "+SelectWerte+" FROM "+Tabelle.getName()+" WHERE "+Where.getName()+"='"+Value+"'");
			if(rs.next()) {
			String toreturn = rs.getString(rueckgabewert.getName());
			return toreturn;
			}else {
				return "ERRORRRR";
			}
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			return "Error (WTF)";
		}
	}
	
	public int getInt(String SelectWerte,Tabellen Tabelle,Wert Where,Object Value,Wert rueckgabewert) {
		try {
			ResultSet rs = getResult("SELECT "+SelectWerte+" FROM "+Tabelle.getName()+" WHERE "+Where.getName()+"='"+Value+"'");
			if(rs.next()) {
			int toreturn = rs.getInt(rueckgabewert.getName());
			return toreturn;
			}else {
				return -1;
			}
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			
			return -1;
		}
	}
	
	public void setString(Tabellen Tabelle,Wert setwert,Object setvalue,Wert Where,Object Value) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE "+Tabelle.getName()+" SET "+setwert.getName()+"='"+setvalue+"' WHERE "+Where.getName()+"='"+Value+"'");
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	public void setInt(Tabellen Tabelle,Wert setwert,Object setvalue,Wert Where,Object Value) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("UPDATE "+Tabelle.getName()+" SET "+setwert.getName()+"="+setvalue+" WHERE "+Where.getName()+"="+Value);
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	public void createArtikel(int listenID,String Artikelname,String Bezeichnung,int menge,double preis,String link,String Typ) {
		Artikelname = User.QuoteForMySQL(Artikelname);
		Bezeichnung = User.QuoteForMySQL(Bezeichnung);
		link = User.QuoteForMySQL(link);
		Typ = User.QuoteForMySQL(Typ);
		try {
			Statement st = this.con.createStatement();
			st.executeUpdate("INSERT INTO Artikel(ArtikelID,ArtikelName,Bezeichnung,Link,Typ,Preis) VALUES (Null,'"+Artikelname+"','"+Bezeichnung+"','"+link+"','"+Typ+"',"+preis+")");
			
			int artikelid = getInt("*", Tabellen.Artikel, Wert.ArtikelName, Artikelname, Wert.ArtikelID);
			st.executeUpdate("INSERT INTO "+Tabellen.Listen_Inhalte.getName()+"(ListenID,ArtikelID,Menge) VALUES ("+listenID+","+artikelid+","+menge+")");
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
		}
	}
	
	
	public boolean userIDExists(int ID) {
		ResultSet rs = getResult("SELECT * FROM User WHERE "+Wert.UserID.getName()+"="+ID);
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
	public boolean benutzerNameExists(String benutzername) {
		benutzername = User.QuoteForMySQL(benutzername);
		ResultSet rs = getResult("SELECT * FROM User WHERE "+Wert.Benutzername.getName()+"='"+benutzername+"'");
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
	
	@SuppressWarnings("deprecation")
	public void listIDS() {
		ResultSet rs = getResult("SELECT * FROM User");
		try {
			System.out.println("-------- User ---------");
			while (rs.next()) {
				System.out.println(""+rs.getInt("UserID")+" -> "+rs.getString("Benutzername")+" "+String.valueOf(new Date(rs.getLong("Geburtsdatum")).toLocaleString())+"| -> Nachname: "+ rs.getString("Nachname")+" Vorname: "+ rs.getString("Vorname"));
				
			}
			System.out.println("-------- User ---------");
		} catch (SQLException e) {
			if(Utils.debug)
				e.printStackTrace();
			
			return;
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
