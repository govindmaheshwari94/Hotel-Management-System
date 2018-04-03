package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import com.hotel.libraries.*;
import com.hotel.model.*;
import java.sql.*;
import java.io.*;

public class analytics{
	private int userId;

	public analytics(int usrId) throws IOException{
		userId = usrId;
	}

	private String getBody(){
		String content = "<div class='container'>\n"+
                "        <div class='container'>\n"+
                "        <div class='col-sm-12'>\n"+
                "                <h3 class='text-uppercase'><em>Analytics Part</em></h3></div><div class='col-sm-12'>\n"+
				" 				<a href='chart1page' class='btn btn-info' role='button'>Chart 1 - Hotel Name vs Room Count</a>\n"+
				"				<a href='chart2page' class='btn btn-info' role='button'>Chart 2 - Total Sales of Hotels</a>\n"+
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
