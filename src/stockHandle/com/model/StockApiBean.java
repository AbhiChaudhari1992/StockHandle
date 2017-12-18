package stockHandle.com.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import stockHandle.com.connection.ConnectionObj;
import stockHandle.com.controller.AddToWatchListController;
import stockHandle.com.controller.PurchaseStockController;
import stockHandle.com.controller.ViewWatchListController;

@ManagedBean
@SessionScoped
public class StockApiBean {

    private static final long serialVersionUID = 1L;
    static final String API_KEY = "AF93E6L5I01EA39O";

    private String symbol;
    private double price;
    private int qty;
    private double amt;
    private String table1Markup;
    private String table2Markup;

    private String selectedSymbol;
    private List<SelectItem> availableSymbols;

    public String getPurchaseSymbol() {
        if (getRequestParameter("symbol") != null) {
            symbol = getRequestParameter("symbol");
        }
        return symbol;
    }
    
    public void setPurchaseSymbol(String purchaseSymbol) {
        System.out.println("func setPurchaseSymbol()");  //check
    }

    public double getPurchasePrice() {
        if (getRequestParameter("price") != null) {
            price = Double.parseDouble(getRequestParameter("price"));
            System.out.println("price: " + price);
        }
        return price;
    }

    public void setPurchasePrice(double purchasePrice) {
        System.out.println("func setPurchasePrice()");  //check
    }
    
    private String getRequestParameter(String name) {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);
    }

    @PostConstruct
    public void init() {
        //initially populate stock list
        availableSymbols = new ArrayList<SelectItem>();
        availableSymbols.add(new SelectItem("AAPL", "Apple"));
        availableSymbols.add(new SelectItem("AMZN", "Amazon LLC"));
        availableSymbols.add(new SelectItem("AR", "Antero Resources"));
        availableSymbols.add(new SelectItem("EBAY", "Ebay"));
        availableSymbols.add(new SelectItem("FB", "Facebook, Inc."));
        availableSymbols.add(new SelectItem("GOLD", "Gold"));
        availableSymbols.add(new SelectItem("GOOGL", "Google"));
        availableSymbols.add(new SelectItem("MSFT", "Microsoft"));
        availableSymbols.add(new SelectItem("SLV", "Silver"));
        availableSymbols.add(new SelectItem("TWTR", "Twitter, Inc."));

        //initially populate intervals for stock api
        availableIntervals = new ArrayList<SelectItem>();
        availableIntervals.add(new SelectItem("1min", "1min"));
        availableIntervals.add(new SelectItem("5min", "5min"));
        availableIntervals.add(new SelectItem("15min", "15min"));
        availableIntervals.add(new SelectItem("30min", "30min"));
        availableIntervals.add(new SelectItem("60min", "60min"));
    }

    private String selectedInterval;
    private List<SelectItem> availableIntervals;

    public String getSelectedInterval() {
        return selectedInterval;
    }

    public void setSelectedInterval(String selectedInterval) {
        this.selectedInterval = selectedInterval;
    }

    public List<SelectItem> getAvailableIntervals() {
        return availableIntervals;
    }

    public void setAvailableIntervals(List<SelectItem> availableIntervals) {
        this.availableIntervals = availableIntervals;
    }

    public String getSelectedSymbol() {
        return selectedSymbol;
    }

    public void setSelectedSymbol(String selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    public List<SelectItem> getAvailableSymbols() {
        return availableSymbols;
    }

    public void setAvailableSymbols(List<SelectItem> availableSymbols) {
        this.availableSymbols = availableSymbols;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getTable1Markup() {
        return table1Markup;
    }

    public void setTable1Markup(String table1Markup) {

    }

    public String getTable2Markup() {
        return table2Markup;
    }

    public void setTable2Markup(String table2Markup) {
        this.table2Markup = table2Markup;
    }

    
    public String createDbRecordWatchList(String symbol, double price, int qty, double amt)
    {
    	 
    	     System.out.println("we are in create Db Record Watchlit");
    	     
    	 
              //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
              //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");
            /*  Connection conn = ConnectionObj.c.getConnection();
              Statement statement = conn.createStatement();*/
              
              //get userid
             int uid =(int)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserID");
              
              System.out.println(uid);
              System.out.println("symbol:" + symbol);
              System.out.println("price:" + price);
              System.out.println("qty:" + qty);
              System.out.println("amt:" + amt);
            /*  String sql = "insert into watchlist(uid,stockname) values (uid,symbol)";
              statement.executeUpdate("INSERT INTO `watchlist` (`id`, `uid`, `stock_symbol`, `qty`, `price`, `amt`) "
                      + "VALUES (NULL,'" + uid + "','" + symbol + "','" + qty + "','" + price + "','" + amt +"')");
              statement.executeUpdate(sql);
              statement.close();
              conn.close();*/
              String name = null ;
              if(symbol.equals("AAPL"))
              {
            	  name = "Apple";
              }
              else
              if(symbol.equals("AMZN"))
              {
            	  name = "Amazon LLC";
              }  
              
              else
                  if(symbol.equals("AR"))
                  {
                	  name = "Antero Resources";
                  }  
              
                  else
                      if(symbol.equals("EBAY"))
                      {
                    	  name = "Ebay";
                      }  
              
                      else
                          if(symbol.equals("FB"))
                          {
                        	  name = "Facebook, Inc.";
                          }  
              
                          else
                              if(symbol.equals("GOLD"))
                              {
                            	  name = "Gold";
                              }  
              
                              else
                                  if(symbol.equals("GOOGL"))
                                  {
                                	  name = "Google";
                                  }  
              
                                  else
                                      if(symbol.equals("MSFT"))
                                      {
                                    	  name = "Microsoft";
                                      } 
                                      else
                                          if(symbol.equals("SLV"))
                                          {
                                        	  name = "Silver";
                                          } 
              
                                          else
                                              if(symbol.equals("TWTR"))
                                              {
                                            	  name = "Twitter, Inc.";
                                              } 
              AddToWatchListController addToWatchListController = new AddToWatchListController();
              addToWatchListController.addStockToWatchList(uid, symbol,name);
              FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Added stock to watchlist",""));
         
          return "purchase";
    	
    }
    
    
    public String createDbRecord(String symbol, double price, int qty, double amt) {
    
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            /*Connection conn = ConnectionObj.c.getConnection();
            Statement statement = conn.createStatement();
            */
            //get userid
    	    PurchaseStockController purchaseStockController = new PurchaseStockController();
    	    StockApiBean stockApiBean = new StockApiBean();
    	    stockApiBean.setAmt(amt);
    	    stockApiBean.setSymbol(symbol);
    	    stockApiBean.setQty(qty);
            purchaseStockController.purchaseStock(stockApiBean);         
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock",""));
            return "purchase";
    }
    
    public String createDbRecordFromManager(String symbol, double price, int qty, double amt) {
        System.out.println("We are in manager db records");
	    PurchaseStockController purchaseStockController = new PurchaseStockController();
	    StockApiBean stockApiBean = new StockApiBean();
	    stockApiBean.setAmt(amt);
	    stockApiBean.setSymbol(symbol);
	    stockApiBean.setQty(qty);
        purchaseStockController.purchaseStock(stockApiBean);         
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock",""));
        return "purchase";
}

    public void installAllTrustingManager() {
        TrustManager[] trustAllCerts;
        trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return;
    }

    public void timeseries() throws MalformedURLException, IOException {
      System.out.println("We are in stockapi bean");
        installAllTrustingManager();

        //System.out.println("selectedItem: " + this.selectedSymbol);
        //System.out.println("selectedInterval: " + this.selectedInterval);
        String symbol = this.selectedSymbol;
        String interval = this.selectedInterval;
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

        this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
        InputStream inputStream = new URL(url).openStream();

        // convert the json string back to object
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject mainJsonObj = jsonReader.readObject();
        for (String key : mainJsonObj.keySet()) {
            if (key.equals("Meta Data")) {
                this.table1Markup = null; // reset table 1 markup before repopulating
                JsonObject jsob = (JsonObject) mainJsonObj.get(key);
                this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                this.table1Markup += "<table>";
                this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
                this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                this.table1Markup += "</table>";
            } else {
                this.table2Markup = null; // reset table 2 markup before repopulating
                JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                this.table2Markup += "<table class='table table-hover'>";
                this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                this.table2Markup += "<tbody>";
                int i = 0;
                for (String subKey : dataJsonObj.keySet()) {
                    JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                    this.table2Markup
                            += "<tr>"
                            + "<td>" + subKey + "</td>"
                            + "<td>" + subJsonObj.getString("1. open") + "</td>"
                            + "<td>" + subJsonObj.getString("2. high") + "</td>"
                            + "<td>" + subJsonObj.getString("3. low") + "</td>"
                            + "<td>" + subJsonObj.getString("4. close") + "</td>"
                            + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                    if (i == 0) {
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                        this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/faces/purchase.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Buy Stock</a></td>";
                      
                    }
                    this.table2Markup += "</tr>";
                    i++;
                }
                this.table2Markup += "</tbody></table>";
            }
        }
        return;
    }
    
    
    public void timeseriesManager() throws MalformedURLException, IOException {
        System.out.println("We are in stockapi bean");
          installAllTrustingManager();
          System.out.println("We are in timeseries manager");
          //System.out.println("selectedItem: " + this.selectedSymbol);
          //System.out.println("selectedInterval: " + this.selectedInterval);
          String symbol = this.selectedSymbol;
          String interval = this.selectedInterval;
          String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

          this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
          InputStream inputStream = new URL(url).openStream();

          // convert the json string back to object
          JsonReader jsonReader = Json.createReader(inputStream);
          JsonObject mainJsonObj = jsonReader.readObject();
          for (String key : mainJsonObj.keySet()) {
              if (key.equals("Meta Data")) {
                  this.table1Markup = null; // reset table 1 markup before repopulating
                  JsonObject jsob = (JsonObject) mainJsonObj.get(key);
                  this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                  this.table1Markup += "<table>";
                  this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
                  this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                  this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                  this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                  this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                  this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                  this.table1Markup += "</table>";
              } else {
                  this.table2Markup = null; // reset table 2 markup before repopulating
                  JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                  this.table2Markup += "<table class='table table-hover'>";
                  this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                  this.table2Markup += "<tbody>";
                  int i = 0;
                  for (String subKey : dataJsonObj.keySet()) {
                      JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                      this.table2Markup
                              += "<tr>"
                              + "<td>" + subKey + "</td>"
                              + "<td>" + subJsonObj.getString("1. open") + "</td>"
                              + "<td>" + subJsonObj.getString("2. high") + "</td>"
                              + "<td>" + subJsonObj.getString("3. low") + "</td>"
                              + "<td>" + subJsonObj.getString("4. close") + "</td>"
                              + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                      if (i == 0) {
                          String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                          this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/faces/purchaseManager.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Buy Stock</a></td>";
                        
                      }
                      this.table2Markup += "</tr>";
                      i++;
                  }
                  this.table2Markup += "</tbody></table>";
              }
          }
          return;
      }
    
    
   
    public String timeseriesWatchListView(String name) throws MalformedURLException, IOException
    {
     	 List<UserBean> l1 = new ArrayList<UserBean>();
     	 String close = null;
       	 StockApiBean stockApiBean = new StockApiBean();
       	 ViewWatchListController viewWatchListController = new ViewWatchListController();
       	 List<String> l =  viewWatchListController.viewWatchList(); 
       
    	
        
    	System.out.println("We are in stockapi bean");
        installAllTrustingManager();

        //System.out.println("selectedItem: " + this.selectedSymbol);
        //System.out.println("selectedInterval: " + this.selectedInterval);
        String symbol = name;
        String interval = "1min";
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

        this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
        InputStream inputStream = new URL(url).openStream();

        UserBean u  = null;
 
        
        // convert the json string back to object
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject mainJsonObj = jsonReader.readObject();
        for(int j = 0 ; j<l.size(); j++)
        for (String key : mainJsonObj.keySet()) {
            if (!key.equals("Meta Data")) {
               
             
                this.table2Markup = null; // reset table 2 markup before repopulating
                JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                this.table2Markup += "<table class='table table-hover'>";
                this.table2Markup += "<thead><tr><th>Close</th></tr></thead>";
                this.table2Markup += "<tbody>";
                int i = 0;
                for (String subKey : dataJsonObj.keySet()) {
                    JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                    this.table2Markup
                            += "<tr>"
                            + "<td>" + name + "</td>"
                            + "<td>" + subJsonObj.getString("4. close") + "</td>";
                    if (i == 0) {
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                     //   this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/faces/watchList.xhtml?symbol=" + l.get(j) + "&price=" + subJsonObj.getString("4. close") + "'>Add To Watch List</a></td>";
                      close =     subJsonObj.getString("4. close");
                    }
                    this.table2Markup += "</tr>";
                    i++;
                    return close;
                }
                this.table2Markup += "</tbody></table>";
            }  
        }
        return null;
    }
    
    
    public void timeseriesWatchList() throws MalformedURLException, IOException {
        System.out.println("We are in stockapi bean");
          installAllTrustingManager();

          //System.out.println("selectedItem: " + this.selectedSymbol);
          //System.out.println("selectedInterval: " + this.selectedInterval);
          String symbol = this.selectedSymbol;
          String interval = this.selectedInterval;
          String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

          this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
          InputStream inputStream = new URL(url).openStream();

          // convert the json string back to object
          JsonReader jsonReader = Json.createReader(inputStream);
          JsonObject mainJsonObj = jsonReader.readObject();
          for (String key : mainJsonObj.keySet()) {
              if (key.equals("Meta Data")) {
                  this.table1Markup = null; // reset table 1 markup before repopulating
                  JsonObject jsob = (JsonObject) mainJsonObj.get(key);
                  this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                  this.table1Markup += "<table>";
                  this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
                  this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                  this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                  this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                  this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                  this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                  this.table1Markup += "</table>";
              } else {
                  this.table2Markup = null; // reset table 2 markup before repopulating
                  JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                  this.table2Markup += "<table class='table table-hover'>";
                  this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                  this.table2Markup += "<tbody>";
                  int i = 0;
                  for (String subKey : dataJsonObj.keySet()) {
                      JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                      this.table2Markup
                              += "<tr>"
                              + "<td>" + subKey + "</td>"
                              + "<td>" + subJsonObj.getString("1. open") + "</td>"
                              + "<td>" + subJsonObj.getString("2. high") + "</td>"
                              + "<td>" + subJsonObj.getString("3. low") + "</td>"
                              + "<td>" + subJsonObj.getString("4. close") + "</td>"
                              + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                      if (i == 0) {
                          String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                          this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/faces/watchList.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Add To Watch List</a></td>";
                        
                      }
                      this.table2Markup += "</tr>";
                      i++;
                  }
                  this.table2Markup += "</tbody></table>";
              }
          }
          return;
      }

    public void purchaseStock() {
        System.out.println("Calling function purchaseStock()");
        System.out.println("stockSymbol: " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockSymbol"));
        System.out.println("stockPrice" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockPrice"));
        return;
    }
}
