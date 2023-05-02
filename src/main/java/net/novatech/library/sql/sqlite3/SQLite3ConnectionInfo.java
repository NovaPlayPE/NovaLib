package net.novatech.library.sql.sqlite3;

import java.io.File;

import net.novatech.library.sql.SQLConnectionInfo;

public class SQLite3ConnectionInfo implements SQLConnectionInfo {

	
	private File file = null;
	
	public SQLite3ConnectionInfo(File path) {
		this.file = path;
	}
	
	
	@Override
	public String getConnectionString() {
		return "jdbc:sqlite:"+this.file.getAbsolutePath();
	}

}
