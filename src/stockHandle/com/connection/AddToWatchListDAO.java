package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddToWatchListDAO {
   public void addToWatchListDAO(int uid, String stockSymbol, String stockname)
  {
	try
	{
		 Connection con =    ConnectionObj.c.getConnection();      //Calling singleton connection class
		    String sql1 = "select * from watchlist where uid = ? and stocksymbol = ?";
			PreparedStatement st1 = con.prepareStatement(sql1);
			//System.out.println("UID is "+ regUser.getUid());
			st1.setInt(1, uid);
			st1.setString(2, stockSymbol);
			ResultSet rs = st1.executeQuery();
	        if(rs.next())
	        {
		       System.out.println("This stock already exists for given uid");
            con.close();	
	}else{
		Connection con1 = ConnectionObj.c.getConnection();
		String sql = "insert into watchlist(uid, stocksymbol, stockname) values (?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uid);
		st.setString(2, stockSymbol);
		st.setString(3, stockname);
		st.executeUpdate();
		System.out.println("Stock has been added to watchlist successfuly");
		con1.close();
	 }
	
    }
		
	catch (Exception e) {
		e.printStackTrace();
	}
  }
}
