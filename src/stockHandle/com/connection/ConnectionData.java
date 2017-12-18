package stockHandle.com.connection;

import java.sql.Connection;

public interface ConnectionData {                           //Interface for database connection
	    public  String hostName = "localhost";
	    public  String portNumber = "3306";
	    public  String databaseName = "stockdb";
	    public  String userName = "root";
	    public  String password = "root";
	    public Connection getConnection();
}
