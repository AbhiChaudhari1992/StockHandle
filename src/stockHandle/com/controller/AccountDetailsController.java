package stockHandle.com.controller;

import java.util.List;

import stockHandle.com.connection.AccountDetailsDAO;
import stockHandle.com.model.AccountBean;

public class AccountDetailsController {
  public List getAccountDetails()
  {
	  AccountDetailsDAO accountDetailsDAO = new AccountDetailsDAO();
	List<AccountBean> l = accountDetailsDAO.getAccountDetails();
	  return l;
  }
}
