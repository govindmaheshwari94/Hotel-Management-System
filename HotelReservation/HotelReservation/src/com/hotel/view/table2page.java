package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import com.hotel.libraries.*;
import com.hotel.model.*;
import java.sql.*;
import java.io.*;

public class table1page{
	private int userId;

	public table1page(int usrId) throws IOException{
		userId = usrId;
	}

	private String getBody(){
	
		trendig2model obj= new trendig2model();
			HashMap<String, Integer> prodd =new HashMap<String, Integer>();
			prodd=obj.trending2();
		
		
		
	
		String content = "<div class='container'>\n"+
                "        <div class='container'>\n"+
                "        <div class='col-sm-12'>\n"+
                "<h3 class='text-uppercase'><em>Top 5 Hotel based on cheap rate</em></h3></div><div class='col-sm-12'>\n"+
					
				"<style>\n"+
				"table, th, td\n"+
				"{\n"+
				"border: 1px solid black;\n"+
"}\n"+
"</style>\n"+
"</head>\n"+
"<body>\n"+

"<table>\n"+
  "<tr>\n"+
   " <th>Hotel Name</th>\n"+
    "<th>Hotel Rating</th>\n"+
  "</tr>\n"+
  "<tr>\n"+

  for ( Map.Entry<String, Integer> entry : prodd.entrySet())

  {
    String key = entry.getKey();
    int tab = entry.getValue();
   "<td>+key+</td>\n"+
	"<td>"+tab+</td>\n"+
  
  "</tr>\n"+

  
  
"</table>\n"+
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
                "</div>\n"+
                "</div>\n"+
                "</ hr>\n"+
                "</div>\n";

		return content;
	}


	public String getContent() throws IOException{
		String content = Constants.pageHeader()+Constants.pageNav(userId)+getBody()+Constants.pageFooter();
		return content;
	}
}
