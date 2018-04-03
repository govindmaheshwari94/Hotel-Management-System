package com.hotel.servelet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import com.hotel.config.*;
import com.hotel.view.*;
import com.hotel.model.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

public class ManageStaffServ extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		int userId = -1;
        String operation = request.getParameter("operation");
        String error = "";
       
        if(request.getSession(false) != null){
			userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
		}

		
		if(operation.equals("view")){
				CustomerView ho = new CustomerView(userId);
				String customerId = "";
				ho.setOperation("view");
				ho.setStaffOnly();

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
		        pw.println(ho.getContent());
		}	
	}

}
