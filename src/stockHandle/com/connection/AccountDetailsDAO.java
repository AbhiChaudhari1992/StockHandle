package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import stockHandle.com.model.AccountBean;
import stockHandle.com.model.UserBean;

public class AccountDetailsDAO {
  public List getAccountDetails()
  {
	  List<AccountBean> l = new ArrayList<AccountBean>();
	  try {
		Connection con = ConnectionObj.c.getConnection();
	int userid=(int)	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
		String sql1 = "select * from usertransactions where uid ="+userid;
		PreparedStatement st1 = con.prepareStatement(sql1);
		//System.out.println("UID is "+ regUser.getUid());
        
		ResultSet rs = st1.executeQuery(sql1);
		while(rs.next())
		{
			
			 AccountBean ub  = new AccountBean();
			 ub.setUid(userid);
			 ub.setStockName(rs.getString("stockname"));
			 ub.setBalance(rs.getDouble("balance"));
			 ub.setAmount(rs.getDouble("amount"));	         
			 l.add(ub);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return l;
  }
}
