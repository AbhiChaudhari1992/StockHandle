package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.faces.bean.ManagedBean;

import stockHandle.com.model.UserBean;
@ManagedBean(name = "editUserProfile")
public class EditProfileDAO {
 public void editUserProfile(UserBean ub)
 {
	 try{
		  Connection con = ConnectionObj.c.getConnection();
		  String sql = "update users set name =?, uname = ?, password = ?, address = ?, phone=? , email=? where uid="+ub.getUid();
	      PreparedStatement ps = con.prepareStatement(sql);
	      ps.setString(1, ub.getName());
	      ps.setString(2, ub.getUname());
	      ps.setString(3, ub.getPassword());
	      ps.setString(4, ub.getAddress());
	      ps.setInt(5, ub.getPhone());
	      ps.setString(6, ub.getEmail());
	      ps.executeUpdate();
	      con.close();
		 
	 }catch (Exception e) {
		 e.printStackTrace();
	}
	 
 }
}
