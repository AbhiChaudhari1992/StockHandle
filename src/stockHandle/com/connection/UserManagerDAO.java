package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.context.FacesContext;

import stockHandle.com.model.UserManagerBean;

public class UserManagerDAO {
  public UserManagerBean getUserManager()
  { int s = 0;
    UserManagerBean userManagerBean = new UserManagerBean();
	  try{
	  int userid = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	  Connection con = ConnectionObj.c.getConnection();
	  String sql = "select * from usersmanager where uid="+userid;
	  PreparedStatement ps = con.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
    	  userManagerBean.setUid(userid);
    	  String name = rs.getString("username");
    	  s = rs.getInt("mid");
    	  String status = rs.getString("status");
    	  userManagerBean.setUsername(name);
    	  userManagerBean.setMid(s);
    	  userManagerBean.setStatus(status);
      }
	  }catch (Exception e) {
	    e.printStackTrace();
	}
	  return userManagerBean;
  }
}
