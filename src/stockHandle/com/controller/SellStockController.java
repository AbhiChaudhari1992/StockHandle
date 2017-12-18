package stockHandle.com.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import stockHandle.com.connection.SellStockByUserDAO;
import stockHandle.com.model.SellStockBean;
import stockHandle.com.model.StockApiBean;

public class SellStockController {
   public List sellStockByUser() throws SQLException
   {
	    SellStockByUserDAO sellStockByUserDAO = new SellStockByUserDAO();
	    List<SellStockBean> l = sellStockByUserDAO.sellStockTransaction();
	    return l;
   }
   
   public void doTransactions(String stkname, int quantity) throws MalformedURLException, IOException, SQLException
   {
	   SellStockByUserDAO stockByUserDAO = new SellStockByUserDAO();
	   StockApiBean stockApiBean = new StockApiBean();
	   String close =  stockApiBean.timeseriesWatchListView(stkname);
	   stockByUserDAO.sellStockByUserTransaction(stkname, quantity , close);
   }
}
