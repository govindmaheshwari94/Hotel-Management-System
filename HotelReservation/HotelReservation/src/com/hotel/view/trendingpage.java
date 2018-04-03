package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import com.hotel.libraries.*;
import com.hotel.model.*;
import java.sql.*;
import java.io.*;

public class trendingpage{
	private int userId;

	public trendingpage(int usrId) throws IOException{
		userId = usrId;
	}

	private String getBody(){
		String content = "<div class='container'>\n"+
                "        <div class='container'>\n"+
                "        <div class='col-sm-12'>\n"+
                "                <h3 class='text-uppercase'><em>Trending Part</em></h3></div><div class='col-sm-12'>\n"+
				" 				<a href='table1page' class='btn btn-info' role='button'>Top 5 Hotel based on cheap rate</a>\n"+
				"				<a href='table2page' class='btn btn-info' role='button'>Most popular hotel based on the rating</a>\n"+
                "        </div>\n"+
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
