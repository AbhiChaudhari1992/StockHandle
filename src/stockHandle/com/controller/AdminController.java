package stockHandle.com.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import stockHandle.com.connection.AdminDAO;
import stockHandle.com.model.UserBean;

@ManagedBean
@SessionScoped
public class AdminController {
  public List getManagers()
  {
	  AdminDAO ad = new AdminDAO();
	  List<UserBean> l = ad.accessManager();
	  System.out.println("Records inside the list of controller");
	  for(int i=0 ; i<l.size(); i++ )
		  System.out.println(l.get(i).getName());
	  return l;
  }
}
