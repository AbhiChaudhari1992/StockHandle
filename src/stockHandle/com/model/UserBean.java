package stockHandle.com.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import stockHandle.com.connection.ConnectionObj;
import stockHandle.com.controller.EditProfileController;
import stockHandle.com.controller.SelectManagerController;
import stockHandle.com.controller.UserController;
import stockHandle.com.controller.ViewManagersController;
import stockHandle.com.controller.ViewUserProfileController;
import stockHandle.com.controller.ViewWatchListController;
import sun.applet.Main;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	
   public UserBean() {
	
}	
	
   private int uid ;
   private String uname;
   private Boolean existing;
   private List<String> stock;
   private String stockName;	
   public List<String> getStock() {
	return stock;
}
public String getStockName() {
	return stockName;
}
public void setStockName(String stockName) {
	this.stockName = stockName;
}
public void setStock(List<String> stock) {
	this.stock = stock;
}

private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();
private Map<String, Boolean> checked1 = new HashMap<String, Boolean>();
private String close;
   public String getClose() {
	return close;
}
public void setClose(String close) {
	this.close = close;
}
public Map<String, Boolean> getChecked1() {
	return checked1;
}
public void setChecked1(Map<String, Boolean> checked1) {
	this.checked1 = checked1;
}

private int fee;
   public int getFee() {
	return fee;
}
public void setFee(int fee) {
	this.fee = fee;
}
public Map<Integer, Boolean> getChecked() {
	return checked;
}
public void setChecked(Map<Integer, Boolean> checked) {
	this.checked = checked;
}

private List<UserBean> list; 
   public List<UserBean> getList() {
	return list;
}
public void setList(List<UserBean> list) {
	this.list = list;
}
public Boolean getExisting() {
   return existing;
}
public void setExisting(Boolean existing) {
	this.existing = existing;
	if(existing == true)
	{
		setRole("manager");
	}
	else
	{
		setRole("user");
	}
}

private String name;
   private String password;
   private Double balance;
   private String role;
   private String email;
   private String address;
   private int phone;
   private String status;
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	status = "false";
	this.status = status;
}
public int getUid() {
	return uid;
}
public void setUid(int uid) {
	this.uid = uid;
}
public String getUname() {
	return uname;
}
public void setUname(String uname) {
	this.uname = uname;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getPhone() {
	return phone;
}
public void setPhone(int phone) {
	this.phone = phone;
}



public String validateUser() throws SQLException
{
	 UserController uc = new UserController();
	 String s= uc.loginCheck(this);
	 System.out.println("Role for this user is "+this.role);
	 if(s.equals("successUser"))
	 {	 
	 return "userProfilePage";
	 }
	 else if(s.equals("successManager") )
	 {
		 
		 return "managerProfilePage";
	 }
	 else if (s.equals("managerWait"))
	 {
		 return "managerWait";
	 }
	 else if (s.equals("successManagerApproved"))
	 {
		 return "managerProfileSuccessPage";
	 }
	 return "failure.xhtml";			
	 
}

public String addManagerDetails()
{
	return "enterManagerDetails";
}

public String manReqSent()
{
   
   return "managerProfileSuccessPage";	
}

public void editProfile()
{
   	
}

public String registerUser()
{
  UserController uc = new UserController();
  String s = uc.storeRecord(this);
  if(s.equals("inserted"))
  return "otherPage";
  else
  return "failure";
}

public String logout()
{
	 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	
	 System.out.println("Session has been invalidated");
	 return "firstPage";
}

public String viewManagers()
{
	 ViewManagersController vmc = new ViewManagersController();
	 List<Integer> checkedItems = new ArrayList<Integer>();
	 List l = vmc.viewManagers();
	 this.setList(l);
	 System.out.println("Elements inside the list for user are"+l);
	
	 for(Map.Entry<Integer, Boolean> a : checked.entrySet()){
   	 System.out.println("Person name : "+a.getKey() +" True or false "+a.getValue());
   	  if(a.getValue())
   	  {
   		  System.out.println("Name of the manager with true values "+a.getKey());
   		  checkedItems.add(a.getKey());
   	  }
     }
	
	return "selectManager"; 
}




 public void updateProfile()
 {
	 int userid =  (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	 UserBean ub = new UserBean();
	 ub.setUname(this.uname);
	 ub.setAddress(this.address);
	 ub.setEmail(this.email);
	 ub.setName(this.name);
	 ub.setPhone(this.phone);
	 ub.setPassword(this.password);
     ub.setUid(userid);
	 EditProfileController editProfileController = new EditProfileController();
	 editProfileController.editProfile(ub);
	 
 }
 
 public String viewWatchList()
 {
	 UserBean u  = null;
	 List<UserBean> l1 = new ArrayList<UserBean>();
	 StockApiBean stockApiBean = new StockApiBean();
	 ViewWatchListController viewWatchListController = new ViewWatchListController();
	 List<String> l =  viewWatchListController.viewWatchList();
	 this.stock = l;
     for(int i = 0 ; i< l.size() ; i++)
     {
    	 u = new UserBean();
    	 u.setStockName(l.get(i));
    	 l1.add(u);
     }
     
     
     
     this.list = l1;
     
     
	 return "watchlistdisplay";	 
 }
 
 public void sellOrPurchaseStock()
 {
	 System.out.println("Inside sell or purchase");
	 List<String> checkedItems = new ArrayList<String>();
     for(Map.Entry<String, Boolean> a : checked1.entrySet()){
  	  System.out.println("Person name : "+a.getKey() +" True or false "+a.getValue());
  	  if(a.getValue())
  	  {
  		  checkedItems.add(a.getKey());
  		  String num = a.getKey(); 
  	  }
  	  
} 
	 
 }
 
 
 public String viewProfile()
 {

	 ViewUserProfileController viewUserProfileController = new ViewUserProfileController();
	int userid =  (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
	System.out.println("User id from session map is "+userid);
	 UserBean ub =  viewUserProfileController.getUserDetails(userid);
     this.name  = ub.getName();
     this.uname = ub.getUname();
     this.balance = ub.getBalance();
     this.address = ub.getAddress();
     this.password = ub.getPassword();
     System.out.println("Password is "+ub.getPassword());
     this.role = ub.getRole();
     this.email = ub.getEmail();
     this.phone = ub.getPhone();
     System.out.println("Name is "+ub.getUname());
     System.out.println("Email is "+ub.getEmail());
     return "editUserProfile";
     
  }
 

 
 public String viewManagerProfile()
 {
	 ViewUserProfileController viewUserProfileController = new ViewUserProfileController();
		int userid =  (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
		System.out.println("User id from session map is "+userid);
		 UserBean ub =  viewUserProfileController.getManagerDetails(userid);
	     this.name  = ub.getName();
	     this.uname = ub.getUname();
	     this.balance = ub.getBalance();
	     this.address = ub.getAddress();
	     this.password = ub.getPassword();
	     System.out.println("Password is "+ub.getPassword());
	     this.role = ub.getRole();
	     this.email = ub.getEmail();
	     this.phone = ub.getPhone();
	     System.out.println("Name is "+ub.getUname());
	     System.out.println("Email is "+ub.getEmail());
	 return "editManagerProfile";
 }
 
 
 public String addWatchList()
 {
	 
	 return "addToWatchList";
 }
 
 public String timeseriesWatList() throws MalformedURLException, IOException
 {
	 UserBean ub ;
	 List<UserBean> l1 = new ArrayList<UserBean>();
   	 ViewWatchListController viewWatchListController = new ViewWatchListController();
   	 List<String> l =  viewWatchListController.viewWatchList(); 
   	 List<String> close = new ArrayList<String>();
   	 StockApiBean stockApiBean = new StockApiBean();
   	 for(String s : l )
   	 {	 
   		 ub = new UserBean();
   		 String p = stockApiBean.timeseriesWatchListView(s);
   		 ub.setStockName(s);
   		 ub.setClose(p);
   		 l1.add(ub);
   	close.add(p)  ;
   	System.out.println("Stock "+s+" close "+p) ;
   	 }
    this.list = l1;
    System.out.println(l1);
	 return "timeseriesWatList";
 }
 
 public String selectManager()
 {
	int  num = 0; 
	      System.out.println("We are inside select managers");
	      List<Integer> checkedItems = new ArrayList<Integer>();
	      for(Map.Entry<Integer, Boolean> a : checked.entrySet()){
	   	  System.out.println("Person name : "+a.getKey() +" True or false "+a.getValue());
	   	  if(a.getValue())
	   	  {
	   		  System.out.println("ID of the manager with true values "+a.getKey());
	   		  checkedItems.add(a.getKey());
	   		  num = a.getKey();
	   	  }
	   	  
	 }
	    SelectManagerController selectManagerController = new SelectManagerController();
	  String status =   selectManagerController.addManager(num);
	  if(status.equals("assigned"))
	  {
		  return "assigned";
	  }
	  return null;
 }

public String stockTransaction()
{
	 return "salePurchaseStock";
}
 
public static void main(String[] args) {
	UserBean u = new UserBean();
	try {
		u.validateUser();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
