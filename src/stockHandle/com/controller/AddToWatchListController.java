package stockHandle.com.controller;

import stockHandle.com.connection.AddToWatchListDAO;

public class AddToWatchListController {
public void addStockToWatchList(int uid, String stocksymbol, String stockname)
{
  	AddToWatchListDAO addToWatchListDAO = new AddToWatchListDAO();
  	addToWatchListDAO.addToWatchListDAO(uid, stocksymbol, stockname);
}
}
