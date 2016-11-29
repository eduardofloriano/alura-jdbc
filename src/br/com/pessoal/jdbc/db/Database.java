package br.com.pessoal.jdbc.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.ds.PGConnectionPoolDataSource;

public class Database {
	
	private static Connection connection;
	private static javax.sql.ConnectionPoolDataSource dataSource;
	
	private static void createConnectionPool(){		

		PGConnectionPoolDataSource pool = new PGConnectionPoolDataSource();
		pool.setUrl("jdbc:postgresql://localhost/alura");
		pool.setUser("postgres");
		pool.setPassword("postgres");
		dataSource = pool;
	}
	
	
	public static Connection getConnection() throws SQLException{		
	
		if(dataSource == null){
			createConnectionPool();
		}
		
		if(connection == null || connection.isClosed()){
			//connection = DriverManager.getConnection("jdbc:postgresql://localhost/alura", "postgres", "postgres");			
			connection = dataSource.getPooledConnection().getConnection();
			//connection.setAutoCommit(false);
		}
		
		return connection;
		
	}
	
	
}
