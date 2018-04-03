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

public class ManageCustomerServ extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		int userId = -1;
        String operation = request.getParameter("operation");
        String error = "";
       
        if(request.getSession(false) != null){
			userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
		}

		
		if(operation.equals("delete")){
				CustomerView ho = new CustomerView(userId);
				String customerId = "";
				ho.setOperation("delete");
				
				if(request.getParameterMap().containsKey("id")){
					customerId = request.getParameter("id");	
				}

				if(request.getParameterMap().containsKey("staff")){
					if(request.getParameter("staff").equals("1")){
						ho.setStaffOnly();
					}
				}

				if(customerId.isEmpty()){
					response.setContentType("text/html");
					PrintWriter pw = response.getWriter();
			        pw.println(ho.getContent());
		        }else{
		        	doPost(request, response);
		        }
		}else if(operation.equals("view")){
				CustomerView ho = new CustomerView(userId);
				String customerId = "";
				ho.setOperation("view");

				if(request.getParameterMap().containsKey("staff")){
					if(request.getParameter("staff").equals("1")){
						ho.setStaffOnly();
					}
				}

				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
		        pw.println(ho.getContent());
		}else if(operation.equals("update")){
				
				String customerId = request.getParameter("id");	
				CustomerUpdatePage ho = new CustomerUpdatePage(userId, customerId);

				if(request.getParameterMap().containsKey("staff")){
					if(request.getParameter("staff").equals("1")){
						ho.setStaffOnly();
					}
				}

				PrintWriter pw=response.getWriter();
			    pw.println(ho.getContent());
		}

		
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

		if(request.getParameterMap().containsKey("staff")){
					staff = request.getParameter("staff");	
		}

		
		if(operation.equals("delete")){
	
			int customerId = Integer.parseInt(request.getParameter("id"));
			
			UserModel.deleteUser(customerId);

			if(staff.isEmpty()){
				response.sendRedirect(Constants.baseUri+"/ManageCustomer?operation=delete&error=Hotel deleted succesfully");	
			}else{
				response.sendRedirect(Constants.baseUri+"/ManageCustomer?operation=delete&error=Hotel deleted succesfully&staff="+staff);	
			}
		}else if(operation.equals("update")){
			UserLib user = new UserLib(Integer.parseInt(request.getParameter("id")), request.getParameter("email"), Integer.parseInt(request.getParameter("role")), request.getParameter("mobile"), request.getParameter("password")); 
			
			UserModel.updateUser(user);

			response.sendRedirect(Constants.baseUri+"/ManageCustomer?operation=view&error=User updated succesfully");	
		}else if(operation.equals("add")){
			UserLib user = new UserLib(-1, request.getParameter("email"), Integer.parseInt(request.getParameter("role")), request.getParameter("mobile"), request.getParameter("password")); 
			
			UserModel.addUser(user.getEmail(), user.getPassword(), user.getRoleId(), user.getMobile());

			response.sendRedirect(Constants.baseUri+"/ManageCustomer?operation=view&error=User added succesfully");	
		}

	}

}
