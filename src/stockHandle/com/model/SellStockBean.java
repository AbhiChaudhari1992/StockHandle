package stockHandle.com.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import stockHandle.com.controller.ChangeStatusController;
import stockHandle.com.controller.SellStockController;

@ManagedBean
@SessionScoped
public class SellStockBean {
 private int uid;
 private String stockname;
 private double purchaseAmoun;
 public double getPurchaseAmoun() {
	return purchaseAmoun;
}
public void setPurchaseAmoun(double purchaseAmoun) {
	this.purchaseAmoun = purchaseAmoun;
}
private List<SellStockBean> list;
 public List<SellStockBean> getList() {
	return list;
}
public void setList(List<SellStockBean> list) {
	this.list = list;
}
private int quantity;
 public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getStockname() {
	return stockname;
}
public void setStockname(String stockname) {
	this.stockname = stockname;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
private double balance;
 private double amount;
 
 public String sellStockByManager() throws SQLException
 {
	     List<SellStockBean> l = new ArrayList<SellStockBean>();
	 	 SellStockController sellStockController = new SellStockController();
	 	 l =  sellStockController.sellStockByUser();
	 	 System.out.println(l);
	 	 this.list = l;
	 	 for(int i = 0 ; i< l.size(); i ++)
	 	 {
	 		 System.out.println("Name of stock "+l.get(i).stockname);
	 	 }
	 	 return "userPurchasedStock";
 }
 
 public void sellStock(String name , int quantity) throws MalformedURLException, IOException, SQLException
 {
	 SellStockController sellStockController = new SellStockController();
	 sellStockController.doTransactions(name,quantity);
	 StockApiBean stockApiBean = new StockApiBean();
	 
	 System.out.println("Stock name is "+name);
	 
 }
 
 public String sellRequest(int mid)
 {
	  
	 return "sellAmountForManager";
 }
 
 public String purchaseRequest(int mid ) throws SQLException
 {
	     ChangeStatusController changeStatusController = new ChangeStatusController();
	     changeStatusController.changeStatus();
	 	 return "purchaseAmountForManager";
 }
 
 public void putAmount() throws SQLException
 {
	 
	 System.out.println("Purchase amunt is "+this.purchaseAmoun);
	 double amt= this.purchaseAmoun;
	  ChangeStatusController changeStatusController = new ChangeStatusController();
	  changeStatusController.addAmt(amt);
	 
 }
 
 public void purchasByManager()
 {
	 
 }
}
