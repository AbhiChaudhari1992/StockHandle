package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.context.FacesContext;

import stockHandle.com.model.UserBean;

public class CheckLoginDAO {
    
    	public String checkLogin(UserBean userBean)
    	{
    		try{
    			System.out.println("Code is reachable at checkLogin");
    		    Connection con =    ConnectionObj.c.getConnection();      //Calling singleton connection class
    		    String sql1 = "select * from users where uname = ? and password= ?";
    			PreparedStatement st1 = con.prepareStatement(sql1);
    			//System.out.println("UID is "+ regUser.getUid());
    			st1.setString(1, userBean.getUname());
    			st1.setString(2, userBean.getPassword());
    			ResultSet rs = st1.executeQuery();
    			
    			if(rs.next())
    			{
    				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserID", rs.getInt("uid"));
    			    String role  =	rs.getString("role");
    			    if(role.equals("user"))
    			    {
    				String s= "successUser";
    				return s;
    			    }
    			    else
    			    {
    			    	String s = "managerWait";
    			    	if(rs.getString("status").equals("true"))
    			    	{	
    			    	s = "successManagerApproved";
    			    	return s;
    			    	}
    			    	return s;
    			    }
    			    
    				
    			}
    			else
    			{
    				String s = "failure";
    				return s;
    			}
    		}catch (Exception e) {
				e.printStackTrace();
			}
    		return null;
    		
    	}
      
	 
   
}
