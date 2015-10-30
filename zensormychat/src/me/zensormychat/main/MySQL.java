package me.zensormychat.main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



public class MySQL {
	
	//-- Variablen definition
	
	public static String host;
	public static String port;
	public static String username;
	public static String password;
	public static String database;
	public static Connection conn;
	
	//-- Verbinden
	
	public static void connect() {
		if(!isConnected()) {
			try {
				conn = (Connection) DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?autoRecconect=true", username, password);
				System.out.println("[Zensormychat][MySQL] Verbindung aufgebaut!");
			} catch (SQLException e) {
				System.out.println("[Zensormychat][MySQL] Verbindung fehlgeschlagen bitte bearbeite die MySQL.yml!");
			}
		}
	}
	
	//-- Trennen
	public static void disconnect() {
		if (isConnected()) {
			try {
				conn.close();
				System.out.println("[Zensormychat][MySQL] Verbindung geschlossen!");
			} catch (SQLException e) {
				System.out.println("[Zensormychat][MySQL] Verbindung besteht weiterhin!");
			}
		}
	}
	
	//-- Verbindung prüfen
	public static boolean isConnected() {
		return (conn == null ? false : true);
	}
	public static Connection getConnection() {
		return conn;
	}
	public void queryUpdate(String query) {
		Connection conn = MySQL.conn;
		PreparedStatement st = null;
		try {
			st = (PreparedStatement) conn.prepareStatement(query);
			st.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Failed to send Update '" + query + "'.");
		} finally {
			MySQL.closeRessources(null, st);			

		}
	}
	public static void closeRessources(ResultSet rs, PreparedStatement st) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
