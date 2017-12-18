package stockHandle.com.controller;

import java.sql.SQLException;
import java.util.List;

import stockHandle.com.connection.UserRequestDAO;
import stockHandle.com.model.UserManagerBean;

public class UserRequestController {
  public List viewUserRequest() throws SQLException
  {
	  UserRequestDAO userRequestDAO = new UserRequestDAO();
	  List<UserManagerBean> l  =  userRequestDAO.viewUsersRequest();
	  return l;
	//  userRequestDAO.
  }
}
