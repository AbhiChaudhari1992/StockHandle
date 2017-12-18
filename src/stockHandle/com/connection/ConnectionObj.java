package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionObj implements ConnectionData{                       //Singleton class for connection
   public static final ConnectionObj c = new ConnectionObj();
   private ConnectionObj() {
		// TODO Auto-generated constructor stub
	}
   
	@Override
	public Connection getConnection() {
		Connection con = null;
		 try {
	           /* Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNumber + "/" + databaseName, userName, password);
	            */
			 int port  = Integer.parseInt(System.getenv("ICSI518_PORT"));
				String servername = System.getenv("ICSI518_SERVER");
				String databasename = System.getenv("ICSI518_DB");
			    String user = System.getenv("ICSI518_USER");
			    String password = System.getenv("ICSI518_PASSWORD");
			    System.out.println("port number is "+System.getenv("ICSI518_PORT"));
				System.out.println("server name is "+System.getenv("ICSI518_SERVER"));
				System.out.println("database name is"+System.getenv("ICSI518_DB"));
				System.out.println("user is "+System.getenv("ICSI518_USER"));
				MysqlDataSource ds  = new MysqlDataSource();
				ds.setServerName(servername);
				ds.setPortNumber(port);
				ds.setDatabaseName(databasename);
				ds.setUser(user);
				ds.setPassword(password);
				con = ds.getConnection();
	            return con;
	        }   catch (Exception ex) {
	            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
	            return null;
	        }
		
	}

}
