package stockHandle.com.connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import stockHandle.com.model.SellStockBean;
import stockHandle.com.model.StockApiBean;

public class SellStockByUserDAO {
     public List sellStockTransaction() throws SQLException
     {
    	 List<SellStockBean > l = new ArrayList<>();
    	 SellStockBean sellStockBean = null;
    	 int userid = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
    	 Connection con = ConnectionObj.c.getConnection();
    	 String sql1 ="select * from userstock where uid ="+userid;
		    PreparedStatement st1 = con.prepareStatement(sql1);
			ResultSet rs = st1.executeQuery(sql1);
			while(rs.next())
			{
			sellStockBean = new SellStockBean();
			sellStockBean.setUid(userid);
			sellStockBean.setQuantity(rs.getInt("quantity"));
			sellStockBean.setStockname(rs.getString("stockname"));
			l.add(sellStockBean);
			}
		return l;
     }
     
     public void sellStockByUserTransaction(String stkName, int quantity ,String close) throws MalformedURLException, IOException, SQLException
     {
    	 StockApiBean stockApiBean = new StockApiBean();
    	 double a = Double.parseDouble(close);
    	 int userid = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
    	 System.out.println("Close price is "+a);
    	 Double balance = quantity*a;
    	 Double bal = null;
    	 
    	 Connection con = ConnectionObj.c.getConnection();
    	 String sql1= "select balance from users where uid="+userid;
    	 PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
    	 ResultSet rs = preparedStatement1.executeQuery();
    	 if(rs.next())
    	 {
    		bal = rs.getDouble("balance"); 
    	 }
    	 bal = bal + balance;
    	 
    	 String sql = "update users set balance = ? where uid =?";
    	 PreparedStatement st = con.prepareStatement(sql);
    	 st.setDouble(1, bal );
    	 st.setInt(2, userid);
    	 st.executeUpdate();
    	 
    	 String sql2 = "delete from userstock where uid = ? and stockname = ?";
    	 PreparedStatement st3 = con.prepareStatement(sql2);
    	 st3.setInt(1, userid);
    	 st3.setString(2, stkName);
    	 st3.execute();
    	 
    	  String sql3 = "insert into usertransactions (uid, stockname, amount, balance) values(?,?,?,?)";
	      PreparedStatement ps3 = con.prepareStatement(sql3);
	      ps3.setInt(1, userid);
	      ps3.setString(2, stkName);
	      ps3.setDouble(3, a );
	      ps3.setDouble(4, bal);
	      ps3.executeUpdate();
     }
}
