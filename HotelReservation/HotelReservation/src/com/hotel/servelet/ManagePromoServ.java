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


public class ManagePromoServ extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		int userId = -1;
		int role = 3;
        String operation = request.getParameter("operation");
        String error = "";
        
        if(request.getSession(false) != null){
			userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
			role = UserModel.getUserRole(userId);
		}

		PromoAdminView ho = new PromoAdminView(userId);
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
       	pw.println(ho.getContent());

		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException
	{
		int userId = -1;
		String operation = request.getParameter("operation");
		String error = "";

		if(request.getSession(false) != null){
			userId = Integer.parseInt(request.getSession(false).getAttribute("userId").toString());
		}

		
		if(operation.equals("add")){
			PromoLib promo = new PromoLib(-1, Integer.parseInt(request.getParameter("discount")), request.getParameter("code"), "");

			PromoModel.addPromo(promo);

			response.setContentType("text/html;charset=UTF-8");

			// Create path components to save the file
			String path = request.getServletContext().getRealPath("");
			Part filePart = request.getPart("imgfile");
			String fileName = promo.getImage();

			OutputStream out = null;
			InputStream filecontent = null;

			try {
				out = new FileOutputStream(new File(path + File.separator + fileName));
				filecontent = filePart.getInputStream();

				int read = 0;
				final byte[] bytes = new byte[1024];

				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				response.sendRedirect(Constants.baseUri+"/ManagePromo"+"#"+promo.getId());
			} catch (FileNotFoundException fne) {
				response.sendRedirect(Constants.baseUri+"/ManagePromo?error="+fne.getMessage());

			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}
		}else if(operation.equals("delete")){
			int imageId = Integer.parseInt(request.getParameter("id"));
			
			PromoModel.deletePromo(imageId);
			response.sendRedirect(Constants.baseUri+"/ManagePromo");
			
		}

	}

}
