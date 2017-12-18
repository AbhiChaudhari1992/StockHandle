package stockHandle.com.controller;

import java.sql.SQLException;

import stockHandle.com.connection.ChangeStatusDAO;

public class ChangeStatusController {
    public void changeStatus() throws SQLException
    {
    	ChangeStatusDAO changeStatusDAO = new ChangeStatusDAO();
    	changeStatusDAO.changeStatus();
    }
    
    public void addAmt(double amt) throws SQLException
    {
    	ChangeStatusDAO changeStatusDAO = new ChangeStatusDAO();
    	changeStatusDAO.addAmount(amt);
    }
}
