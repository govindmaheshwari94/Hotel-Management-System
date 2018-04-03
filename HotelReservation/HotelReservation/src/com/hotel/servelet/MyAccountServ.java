package com.hotel.servelet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import com.hotel.config.*;
import com.hotel.view.*;
import com.hotel.model.*;

public class MyAccountServ extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
    {
        int userId = -1;
        MyAccountPage accountPage = null;
        
        if(request.getSession(false) != null){
            userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
        }


        if(userId < 1){
            response.sendRedirect(Constants.baseUri+"/Login?error=Please Login before checking account");
        }

        accountPage = new MyAccountPage(userId);
   
        response.setContentType("text/html");       
        PrintWriter pw=response.getWriter();
        pw.println(accountPage.getContent());
       
    }
}
