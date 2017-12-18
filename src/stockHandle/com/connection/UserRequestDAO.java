package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import stockHandle.com.model.UserManagerBean;

public class UserRequestDAO {
     public List viewUsersRequest() throws SQLException
     {
    	  UserManagerBean userManagerBean = null;
    	 List<UserManagerBean> l  = new  ArrayList<UserManagerBean>();
         int mid = 	(int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
    	 Connection con = ConnectionObj.c.getConnection();
         String sql = "select * from usersmanager where mid ="+mid;
         PreparedStatement ps  = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         while(rs.next())
         {
        	 userManagerBean = new UserManagerBean();
        	 userManagerBean.setStatus(rs.getString("status"));
        	 userManagerBean.setMid(mid);
        	 userManagerBean.setUid(rs.getInt("uid"));
        	 userManagerBean.setPurchaseamt(rs.getDouble("purchaseamt"));
        	 l.add(userManagerBean);
        	 
         }
         return l;
     }
}
