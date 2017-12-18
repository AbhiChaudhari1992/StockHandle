package stockHandle.com.controller;

import java.util.ArrayList;
import java.util.List;

import stockHandle.com.connection.ApproveOrDeclineManagerDAO;

public class ApproveOrDeclineController {
    public void approveOrDecline(List<Integer> checkedItems)
    {
    	 ApproveOrDeclineManagerDAO approveOrDeclineManagerDAO = new ApproveOrDeclineManagerDAO();
    	 approveOrDeclineManagerDAO.changeManagersStatus(checkedItems);
    }
}
