package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.context.FacesContext;

import stockHandle.com.model.StockApiBean;

public class PurchaseStockDAO {
   public void purchaseStock(StockApiBean stockApiBean)
   {
	   try {
		    double balance = 0;
		    int uid = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
		    Connection con  = ConnectionObj.c.getConnection();
		    String sql1 ="select balance from users where uid ="+uid;
		    PreparedStatement st1 = con.prepareStatement(sql1);
			ResultSet rs = st1.executeQuery(sql1);
			if(rs.next())
			{
			   balance  = rs.getDouble("balance");
			}
			else
			{
				System.out.println("record does not exists in database");
			}
			
			      balance = balance - stockApiBean.getAmt();
	              String sql = "update users set balance = ? where uid= ?";
			      PreparedStatement ps = con.prepareStatement(sql);
			      ps.setDouble(1 , balance);
			      ps.setInt(2, uid);
			      ps.executeUpdate();
			      
			      String sql3 = "insert into usertransactions (uid, stockname, amount, balance) values(?,?,?,?)";
			      PreparedStatement ps3 = con.prepareStatement(sql3);
			      ps3.setInt(1, uid);
			      ps3.setString(2, stockApiBean.getSymbol());
			      ps3.setDouble(3, stockApiBean.getAmt());
			      ps3.setDouble(4, balance);
			      ps3.executeUpdate();
			      
			      
			        String sql5 ="select * from userstock where uid ="+uid+" and stockname ='"+stockApiBean.getSymbol()+"'";
				    PreparedStatement st4 = con.prepareStatement(sql5);
					ResultSet rs4 = st4.executeQuery(sql5);
			        if(rs4.next())
			        {
			        	  int quantity  = rs4.getInt("quantity");
			        	  quantity = quantity + stockApiBean.getQty();
			        	  String sql6 = "update userstock set quantity=? where uid =? and stockname=?";
					      PreparedStatement ps5 = con.prepareStatement(sql6);
					      ps5.setInt(1,quantity);
					      ps5.setInt(2,uid);
					      ps5.setString(3,stockApiBean.getSymbol());
					      ps5.executeUpdate();
			        }
			        
			        else{
			      String sql4 = "insert into userstock (uid, stockname,quantity) values(?,?,?)";
			      PreparedStatement ps4 = con.prepareStatement(sql4);
			      ps4.setInt(1, uid);
			      ps4.setString(2, stockApiBean.getSymbol());
			      ps4.setDouble(3, stockApiBean.getQty());
			      ps4.executeUpdate();
			        }
			      con.close();
	        
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
   
   
   public void purchaseStockManager(StockApiBean stockApiBean)
   {
	   try {
		    double balance = 0;
		    int uid = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uidForManager");
		    Connection con  = ConnectionObj.c.getConnection();
		    String sql1 ="select balance from users where uid ="+uid;
		    PreparedStatement st1 = con.prepareStatement(sql1);
			ResultSet rs = st1.executeQuery(sql1);
			if(rs.next())
			{
			   balance  = rs.getDouble("balance");
			}
			else
			{
				System.out.println("record does not exists in database");
			}
			
			      balance = balance - stockApiBean.getAmt();
	              String sql = "update users set balance = ? where uid= ?";
			      PreparedStatement ps = con.prepareStatement(sql);
			      ps.setDouble(1 , balance);
			      ps.setInt(2, uid);
			      ps.executeUpdate();
			      
			      String sql3 = "insert into usertransactions (uid, stockname, amount, balance) values(?,?,?,?)";
			      PreparedStatement ps3 = con.prepareStatement(sql3);
			      ps3.setInt(1, uid);
			      ps3.setString(2, stockApiBean.getSymbol());
			      ps3.setDouble(3, stockApiBean.getAmt());
			      ps3.setDouble(4, balance);
			      ps3.executeUpdate();
			      
			      
			        String sql5 ="select * from userstock where uid ="+uid+" and stockname ='"+stockApiBean.getSymbol()+"'";
				    PreparedStatement st4 = con.prepareStatement(sql5);
					ResultSet rs4 = st4.executeQuery(sql5);
			        if(rs4.next())
			        {
			        	  int quantity  = rs4.getInt("quantity");
			        	  quantity = quantity + stockApiBean.getQty();
			        	  String sql6 = "update userstock set quantity=? where uid =? and stockname=?";
					      PreparedStatement ps5 = con.prepareStatement(sql6);
					      ps5.setInt(1,quantity);
					      ps5.setInt(2,uid);
					      ps5.setString(3,stockApiBean.getSymbol());
					      ps5.executeUpdate();
			        }
			        
			        else{
			      String sql4 = "insert into userstock (uid, stockname,quantity) values(?,?,?)";
			      PreparedStatement ps4 = con.prepareStatement(sql4);
			      ps4.setInt(1, uid);
			      ps4.setString(2, stockApiBean.getSymbol());
			      ps4.setDouble(3, stockApiBean.getQty());
			      ps4.executeUpdate();
			        }
			      con.close();
	        
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
}
