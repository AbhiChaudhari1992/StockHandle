package stockHandle.com.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

public class ViewWatchListDAO {
  public List viewWatchList()
  {
	  List<String> l = new ArrayList<String>();
	  try {
		   Connection con =  ConnectionObj.c.getConnection();
		   int userid =  (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
           String sql = "select * from watchlist where uid ="+userid;
           PreparedStatement p1 = con.prepareStatement(sql);
           ResultSet rs = p1.executeQuery();
           while(rs.next())
           {
        	   l.add(rs.getString("stocksymbol"));
           }
           con.close();
           
	} catch (Exception e) {
		e.printStackTrace();
	}
	  return l;
  }
}
