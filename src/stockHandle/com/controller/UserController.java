package stockHandle.com.controller;

import stockHandle.com.connection.CheckLoginDAO;
import stockHandle.com.connection.insertUserDAO;
import stockHandle.com.model.UserBean;

public class UserController {                                         //controller with business logic for registration
  public String storeRecord(UserBean userBean){
	  System.out.println("Name of the user is "+userBean.getName());
	  System.out.println("Role of the user is "+userBean.getRole());
	  if(userBean.getRole().equals("manager"));
	  insertUserDAO ins = new insertUserDAO();
	  String s= ins.insertUser(userBean);
	  return s;
  }
  
  public String loginCheck(UserBean userBean)                        //controller with business logic for Login 
  {
	 CheckLoginDAO cld = new CheckLoginDAO();
	 String s= cld.checkLogin(userBean); 
	 
	 return s;
  }
  
  public String statusCheck()
  {
	  
	return null;
	  
  }
}
