package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	Connection connect = null;
	
	private final String USER = "roott";
	private final String PASSWORD = "root";
	
	public ConnectionManager() {
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost/software_consrtuct" + "?user="+USER+"&password="+PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	public Connection getConnection() {
		return connect;
	}
	
	public void close() throws SQLException {
		connect.close();
	}
}
