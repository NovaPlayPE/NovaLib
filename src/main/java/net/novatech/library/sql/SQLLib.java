package net.novatech.library.sql;

import net.novatech.library.sql.mysql.MySQLDatabase;
import net.novatech.library.sql.sqlite3.SQLite3Database;

public class SQLLib {
	
	public static Database init(SQLType type, SQLConnectionInfo info) {
		switch(type) {
		case MySQL:
			return new MySQLDatabase(info);
		case SQLite3:
			return new SQLite3Database(info);
		}
		return null;
	}
}