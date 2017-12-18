package stockHandle.com.controller;

import java.util.List;

import stockHandle.com.connection.ViewWatchListDAO;

public class ViewWatchListController {
   public List viewWatchList()
   {
	   ViewWatchListDAO viewWatchListDAO = new ViewWatchListDAO();
	  List<String> l =  viewWatchListDAO.viewWatchList();
	  return l;
   }
}
