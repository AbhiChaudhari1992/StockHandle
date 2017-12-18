package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ApproveOrDeclineManagerDAO {
  public void changeManagersStatus(List<Integer> status)                              //changing managers status to true of admin Approves
  {
	try{  
	 Connection con  = ConnectionObj.c.getConnection();
	 PreparedStatement p = null;
	 
	 for(int i = 0 ; i < status.size() ; i ++)
	 {
		 System.out.println("Values inside the list are "+status);
		 String sql = "update users set status = 'true' where uid = ?";
		 p = con.prepareStatement(sql);
		 p.setInt(1, status.get(i));
		 p.executeUpdate();
	 }
	 con.close();
	 
	}catch (Exception e) {
		e.printStackTrace();
	} 
  }
}
