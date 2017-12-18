package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import stockHandle.com.model.UserBean;

public class ViewProfileDAO {
 public UserBean getUserDetails(int uid)
 {
	 UserBean ub  = new UserBean();
	 try
	 {
		
	 Connection con  = ConnectionObj.c.getConnection();
	 String sql1 = "select * from users where uid="+uid;
		PreparedStatement st1 = con.prepareStatement(sql1);
		//System.out.println("UID is "+ regUser.getUid());
		ResultSet rs = st1.executeQuery(sql1);
		if(rs.next())
		{
			ub.setName(rs.getString("name"));
			ub.setUname(rs.getString("uname"));
			ub.setAddress(rs.getString("address"));
			ub.setPhone(rs.getInt("phone"));
			ub.setBalance(rs.getDouble("balance"));
			ub.setEmail(rs.getString("email"));
			ub.setPassword(rs.getString("password"));
			System.out.println("Password of user inside dao "+rs.getString("password"));
		}
	
	 }catch (Exception e) {
		e.printStackTrace();
	} 
	 return ub;
 }
 
 public UserBean getManagersDetails(int uid)
 {
	 UserBean ub  = new UserBean();
	 try
	 {
		
	 Connection con  = ConnectionObj.c.getConnection();
	 String sql1 = "select * from users where uid="+uid;
		PreparedStatement st1 = con.prepareStatement(sql1);
		//System.out.println("UID is "+ regUser.getUid());
		ResultSet rs = st1.executeQuery(sql1);
		if(rs.next())
		{
			ub.setName(rs.getString("name"));
			ub.setUname(rs.getString("uname"));
			ub.setAddress(rs.getString("address"));
			ub.setPhone(rs.getInt("phone"));
			ub.setBalance(rs.getDouble("balance"));
			ub.setEmail(rs.getString("email"));
			ub.setPassword(rs.getString("password"));
			System.out.println("Password of user inside dao "+rs.getString("password"));
		}
	
	 }catch (Exception e) {
		e.printStackTrace();
	} 
	 return ub;
 }
 
}
