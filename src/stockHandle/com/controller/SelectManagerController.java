package stockHandle.com.controller;

import stockHandle.com.connection.AddManagerDAO;

public class SelectManagerController {
  public String addManager(int num)
  {
	  AddManagerDAO addManagerDAO = new AddManagerDAO();
	 String check =  addManagerDAO.addManager(num);
	 return check;
  }
}
