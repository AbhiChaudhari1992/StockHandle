package stockHandle.com.controller;

import stockHandle.com.connection.PurchaseStockDAO;
import stockHandle.com.model.StockApiBean;

public class PurchaseStockController {
   public void purchaseStock(StockApiBean stockApiBean)
   {
	   PurchaseStockDAO purchaseStockDAO = new PurchaseStockDAO();
	   purchaseStockDAO.purchaseStock(stockApiBean);
   }
   
   public void purchaseStockController(StockApiBean stockApiBean)
   {
	   PurchaseStockDAO purchaseStockDAO = new PurchaseStockDAO();
	   purchaseStockDAO.purchaseStockManager(stockApiBean);
   }
}
