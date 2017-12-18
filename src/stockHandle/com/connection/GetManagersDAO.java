package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import stockHandle.com.model.UserBean;

public class GetManagersDAO {
public List getManagers()
{
	 List<UserBean> list = new ArrayList<UserBean>();
	try{
	Connection con  = ConnectionObj.c.getConnection();
	 String sql1 = "select * from users where role = 'manager'";
		PreparedStatement st1 = con.prepareStatement(sql1);
		//System.out.println("UID is "+ regUser.getUid());
        
		ResultSet rs = st1.executeQuery(sql1);
		while(rs.next())
		{
			
			 UserBean ub  = new UserBean();
			 Integer s =  (Integer) rs.getObject("uid");
	         ub.setUid(s);
			 ub.setName(rs.getString("name"));
			 ub.setUname(rs.getString("uname"));
			 ub.setEmail(rs.getString("email"));
			 ub.setAddress(rs.getString("address"));
			 ub.setPhone(rs.getInt("phone"));
			 list.add(ub);
		}
	} catch(Exception e)
	 {
	  e.printStackTrace();	 
	 }
	 
return list;
}
}
