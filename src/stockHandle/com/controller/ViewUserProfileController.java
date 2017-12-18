package stockHandle.com.controller;

import stockHandle.com.connection.ViewProfileDAO;
import stockHandle.com.model.UserBean;

public class ViewUserProfileController {
   public UserBean getUserDetails(int uid)
   {
	   
	   ViewProfileDAO viewProfileDAO = new ViewProfileDAO();
	   UserBean u= viewProfileDAO.getUserDetails(uid);
	   return u;
   }
   
   public UserBean getManagerDetails(int uid)
   {
	   ViewProfileDAO viewProfileDAO = new ViewProfileDAO();
	   UserBean u= viewProfileDAO.getManagersDetails(uid);
	   return u;
   }
}
