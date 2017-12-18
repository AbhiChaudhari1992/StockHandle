package stockHandle.com.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import stockHandle.com.controller.AccountDetailsController;

@ManagedBean
@SessionScoped
public class AccountBean {
  private int uid;
  private String stockName;
  private Double amount;
  private Double balance;
  private List<AccountBean> l ;
  
public List<AccountBean> getL() {
	return l;
}
public void setL(List<AccountBean> l) {
	this.l = l;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getStockName() {
	return stockName;
}
public void setStockName(String stockName) {
	this.stockName = stockName;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}

public String getAccountDetails()
{
	List<AccountBean> l =null;
	AccountDetailsController accountDetailsController = new AccountDetailsController();
    l =	accountDetailsController.getAccountDetails();
    this.l = l;
    return "accountDetails";	
}
}
