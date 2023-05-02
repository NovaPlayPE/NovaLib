package net.novatech.library.sql.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import net.novatech.library.sql.Database;
import net.novatech.library.sql.SQLConnectionInfo;

public class MySQLDatabase extends Database {

	public MySQLDatabase(SQLConnectionInfo info) {
		super(info);
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(this.connectInfo.getConnectionString());
			this.connection.createStatement();
			System.out.printf("[sqlNukkitLib] Connected to MySQL database");
		} catch(SQLException sqle) {
			System.out.printf("[sqlNukkitLib] Database not found: " + sqle.getErrorCode());
		} catch (ClassNotFoundException ex) {
			System.out.printf("[sqlNukkitLib] Class not found");
		}
	}
	
	

}
