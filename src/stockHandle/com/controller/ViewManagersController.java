package stockHandle.com.controller;

import java.util.List;

import stockHandle.com.connection.GetManagersDAO;

public class ViewManagersController {
public List viewManagers()
{
	GetManagersDAO getManagersDAO = new GetManagersDAO();
    List l = getManagersDAO.getManagers();
	return l;
   	

}
}
