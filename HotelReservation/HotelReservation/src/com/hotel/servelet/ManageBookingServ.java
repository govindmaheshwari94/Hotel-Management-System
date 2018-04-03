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
import com.hotel.libraries.*;

public class ManageBookingServ extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		int userId = -1;
        String operation = request.getParameter("operation");
        String error = "";
       
        if(request.getSession(false) != null){
			userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
		}

		ReservationView ho = new ReservationView(userId);


		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        pw.println(ho.getContent());
    
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		int userId = -1;
		String operation = request.getParameter("operation");
		String error = "";
		String staff = "";
		
		if(request.getSession(false) != null){
			userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
		}

		
		if(operation.equals("delete")){
	
			int reserId = Integer.parseInt(request.getParameter("id"));
			
			BookingModel.deleteBooking(reserId);
			response.sendRedirect(Constants.baseUri+"/ManageBooking");	
		}
	}

}
