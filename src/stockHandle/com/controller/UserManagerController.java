package stockHandle.com.controller;

import stockHandle.com.connection.UserManagerDAO;
import stockHandle.com.model.UserManagerBean;

public class UserManagerController {
  public UserManagerBean getUserManager()
  {
	  int s=0;
	  UserManagerBean userManagerBean = new UserManagerBean();
	  UserManagerDAO userManagerDAO = new UserManagerDAO();
	  userManagerBean= userManagerDAO.getUserManager();
	  return userManagerBean;
  }
}
