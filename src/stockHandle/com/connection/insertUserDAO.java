package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import stockHandle.com.model.UserBean;

public class insertUserDAO {
public String insertUser(UserBean userBean)                    //CRUD operations inside DAO
{
 try
 {
    Connection con =    ConnectionObj.c.getConnection();      //Calling singleton connection class
    String sql1 = "select name from users where uname = ?";
	PreparedStatement st1 = con.prepareStatement(sql1);
	//System.out.println("UID is "+ regUser.getUid());
	st1.setString(1, userBean.getUname());
	ResultSet rs = st1.executeQuery();
	//System.out.println("after rs");
	//System.out.println("First name is "+rs.getString(1));
	//System.out.println("REsult set is"+rs);
	
	if(rs.next())
	{ 
     System.out.println("User already exists in database");
     String s= "exists";
     return s;
	}
	else 
	{	
	// Get a prepared SQL statement
	String sql = "insert into users(uname, name, password, balance, role , email , address , phone, status) values (?,?,?,?,?,?,?,?,?)";
	PreparedStatement st = con.prepareStatement(sql);
	st.setString(1, userBean.getUname());
	st.setString(2, userBean.getName());
	st.setString(3, userBean.getPassword());
	st.setDouble(4,  100000.00);
	st.setString(5, userBean.getRole());
	st.setString(6, userBean.getEmail());
	st.setString(7, userBean.getAddress());
	st.setInt(8, userBean.getPhone());
    st.setString(9, "false");
	// Execute the statement
	 st.executeUpdate();
	 System.out.println("User has been inserted in database");
	 String s = "inserted";
	 return s;
	 }
    }catch (Exception e) {
	e.printStackTrace();
  }
return null;	

}
}
