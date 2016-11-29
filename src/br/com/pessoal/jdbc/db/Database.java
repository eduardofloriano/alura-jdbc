package br.com.pessoal.jdbc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Connection connection;
	
	
	public static Connection getConnection() throws SQLException{		
		
		if(connection == null || connection.isClosed()){
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		}
		
		return connection;
		
	}
	
	
}
