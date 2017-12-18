package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

public class ChangeStatusDAO {
   public void changeStatus() throws SQLException
   {
	   Connection con = ConnectionObj.c.getConnection();
	   int userid= (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	   String sql = "update usersmanager set status='purchase' where uid = "+userid;
	   PreparedStatement ps = con.prepareStatement(sql);
       ps.executeUpdate();
   }
   
   public void addAmount(double amt) throws SQLException
   {
	   Connection con = ConnectionObj.c.getConnection();
	   int userid= (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	   String sql = "update usersmanager set purchaseamt=? where uid =?";
	   PreparedStatement ps = con.prepareStatement(sql);
	   ps.setDouble(1, amt);
	   ps.setInt(2, userid);
       ps.executeUpdate();
   }
}
