
import java.io.*;
import java.util.*;
import java.sql.*;

import java.sql.Connection; 
import java.sql.DriverManager;
 import java.sql.ResultSet; 
 import java.sql.SQLException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class AjaxUtility {
	public static HashMap<String,Product> getData()
	{ 
	HashMap<String,Product> hm=new HashMap<String,Product>();
	try
	{ 
      String driver= "com.mysql.jdbc.Driver";
       String url="jdbc:mysql://localhost:336/HotelReservations";
        String USER = "root";
      String PASS = "root";
 Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection(url, USER, PASS);

Statement stmt = conn.createStatement();
	String selectCustomerQuery="select * from hotels";
	ResultSet rs = stmt.executeQuery(selectCustomerQuery);
	while(rs.next())
	{
		
	Hotels p = new Hotels(rs.getString("Id"), rs.getString("Name"), null, 0, 0.0, null, null, null, null, null);
	
	
	hm.put(rs.getString("Id"), p);
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return hm;
	}

	
	public StringBuffer readdata(String searchId)
	{
		StringBuffer sb=new StringBuffer();
	HashMap<String,Hotels> data;
	data=getData();
	Iterator it = data.entrySet().iterator();
	while (it.hasNext())
	{
	Map.Entry pi = (Map.Entry)it.next();
	Product p=(Product)pi.getValue();
	if (p.getName().toLowerCase().startsWith(searchId.toLowerCase()))
	{
	sb.append("<product>");
	sb.append("<id>" + p.getId() + "</id>");
	sb.append("<productName>" + p.getName() + "</productName>");
	sb.append("</product>");
	}
	}
	return sb;
	}
	
	
}
