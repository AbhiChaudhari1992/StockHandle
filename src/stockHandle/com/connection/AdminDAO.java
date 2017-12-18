package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import stockHandle.com.model.UserBean;

public class AdminDAO {
 public List accessManager()                                                                  //getting list of users with role as a manager 
 {
	 List<UserBean> list = new ArrayList<UserBean>();
	 
	 try
	 {
	Connection con =  ConnectionObj.c.getConnection();
	String sql = "select * from users where role = 'manager' and status='false'";
	PreparedStatement st1 = con.prepareStatement(sql);
	ResultSet rs = st1.executeQuery();
	while(rs.next())
	{
		// ResultSetMetaData resultSetMetaData = rs.getMetaData();
		 /* for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
		  {*/
		UserBean ub = new UserBean();
		         System.out.println("We are in Admin DAO ");
		         System.out.println("Email id is "+rs.getString("email"));
		         System.out.println("Name is "+rs.getString("name"));
		         Integer s =  (Integer) rs.getObject("uid");
		         System.out.println("Fetched id is "+s );
		         ub.setUid(s);
			     ub.setEmail(rs.getString("email"));
			     ub.setName(rs.getString("name"));  
			     list.add(ub);
		  //}
		
	}
	System.out.println("List inside admin dao is ");
	for(int i =0;  i < list.size() ;  i++)
	{
		System.out.println(list.get(i).getName());
	}
	 }catch (Exception e) {
		e.printStackTrace();
	}
	 return list;
 }
}
