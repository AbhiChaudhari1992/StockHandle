package stockHandle.com.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import stockHandle.com.controller.UserManagerController;
import stockHandle.com.controller.UserRequestController;

@ManagedBean
@SessionScoped
public class UserManagerBean {
  public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

  private int uid;
 
  private String username;
  private String status;    
  private double purchaseamt;
  public double getPurchaseamt() {
	return purchaseamt;
}
public void setPurchaseamt(double purchaseamt) {
	this.purchaseamt = purchaseamt;
}

private List<UserManagerBean> list;
  public List<UserManagerBean> getList() {
	return list;
}
public void setList(List<UserManagerBean> list) {
	this.list = list;
}

private UserManagerBean userManagerBean;
  public UserManagerBean getUserManagerBean() {
	return userManagerBean;
}
public void setUserManagerBean(UserManagerBean userManagerBean) {
	this.userManagerBean = userManagerBean;
}

private int mid;
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}

 public String requestManager()
 {  
	 List<UserManagerBean > l =  new ArrayList<UserManagerBean>();
	 UserManagerBean userManagerBean = new UserManagerBean();
	 int userid = (int)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
   UserManagerController userManagerController = new UserManagerController();
   userManagerBean = userManagerController.getUserManager();
    l.add(userManagerBean);
    this.list = l;
   return "userAssignedManager";
 }
 
 public String viewRequessts() throws SQLException
 {
	UserRequestController userRequestController = new UserRequestController();
	List<UserManagerBean> l =   userRequestController.viewUserRequest();
	this.list = l;
	 return "displayRequests";
 }
 
 public String purchaseStock(int uid,  double amount) throws MalformedURLException, IOException
 {
	 StockApiBean stockApiBean = new StockApiBean();
	// stockApiBean.timeseriesManager(uid);
	 System.out.println("User id is "+uid);
	 System.out.println("Amount is "+ amount);
	 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uidForManager", uid);
	 return "purchaseStkManager";
 }
}
