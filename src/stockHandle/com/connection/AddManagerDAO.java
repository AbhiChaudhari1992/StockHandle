package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.context.FacesContext;

public class AddManagerDAO {
 public String addManager(int num)
 {
	 String uname = null; 
   try {
	   Connection con =    ConnectionObj.c.getConnection();      //Calling singleton connection class
	   int userid  = 	(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	   System.out.println("User id from session is "+userid);
	   String sql1 = "select * from users where uid ="+userid;
	   PreparedStatement st1 = con.prepareStatement(sql1);
	   ResultSet rs = st1.executeQuery();
	   if(rs.next())
	   {
		   uname =  rs.getString("uname"); 
	   }
	   int uID = 0 ;
	   int uID1  = 	(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	   String sql2 = "select * from usersmanager where uid = "+uID1;
	   PreparedStatement st2 = con.prepareStatement(sql2);
	   ResultSet rs2 = st2.executeQuery();
	   
	   if(rs2.next())
	   {
		   
		   System.out.println("User is already assigned an manager");
		   return "assigned";
	   }
	   else
	   {   
	  //  Connection con = ConnectionObj.c.getConnection();
	    String sql = "insert into usersmanager(uid, username, mid ,status) values (?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
	//	st.setString(1, );
	    int userid1  = 	(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	    st.setInt(1, userid1);
	    st.setString(2, uname);
	    st.setInt(3, num);
	    st.setString(4, "false");
		st.executeUpdate();
		System.out.println("Successfully inserted inside database");
		return "abc";
	   } 
	
} catch (Exception e) {
	e.printStackTrace();
}	 
   return "abc";
 }
}
