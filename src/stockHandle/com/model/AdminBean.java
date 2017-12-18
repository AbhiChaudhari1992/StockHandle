package stockHandle.com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import stockHandle.com.controller.AdminController;
import stockHandle.com.controller.ApproveOrDeclineController;

@ManagedBean (name="adminBean")
@SessionScoped
public class AdminBean {
private String username;
private String password;
private  List<UserBean> list; 
private List<String> items ;
private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();

public List<String> getItems() {
	return items;
}


public void setItems(List<String> items) {
	this.items = items;
}


public Map<Integer, Boolean> getChecked() {
	return checked;
}


public void setChecked(Map<Integer, Boolean> checked) {
	this.checked = checked;
}


public AdminBean() {
	AdminController adminC = new AdminController();
    List<UserBean> l= adminC.getManagers();
    for(int i =0 ; i < l.size(); i++)
    {
    	
    	System.out.println("Details of first Record "+ l.get(i).getName());
    	System.out.println(l.get(i).getEmail());
    	System.out.println(l.get(i).getUid());
    }
    this.list = l;
}


public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String validateAdmin()
{
	
   if(this.username.equals("admin") && this.password.equals("admin"))
   {
	   return "adminProfilePage";
   }
   return "adminFailure";
}


public List<UserBean> getList() {
	return list;
}

public void setList(List<UserBean> list) {
	this.list = list;
}

public void approveOrDeclineManager()
{    
	System.out.println("Name of the manager is ");
	 List<Integer> checkedItems = new ArrayList<Integer>();
	 						
	/* for (String name : items) {
         if (checked.get(name) != null) {*/
          System.out.println(checked);
          
         /*}*/
          
          for(Map.Entry<Integer, Boolean> a : checked.entrySet()){
        	  System.out.println("Person name : "+a.getKey() +" True or false "+a.getValue());
        	  if(a.getValue())
        	  {
        		  System.out.println("Name of the manager with true values "+a.getKey());
        		  checkedItems.add(a.getKey());
        	  }
          }
       ApproveOrDeclineController aodc = new ApproveOrDeclineController();
       aodc.approveOrDecline(checkedItems);
     }
public String logout()
{
	 FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	
	 System.out.println("Session has been invalidated");
	 return "firstPage";	
}

}
