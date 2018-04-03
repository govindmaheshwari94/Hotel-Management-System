package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import java.io.*;
import com.hotel.libraries.*;
import com.hotel.model.*;

public class ReservationView{
	private String error;
	private int userId;


	public ReservationView(int usrId){

		if(usrId > 0){
			userId = usrId;
		}else{
			userId = 0;
		}

		error = "";

	}


	private String getBody() throws IOException{
		String content = "<div class='container-fluid'>\n"+
		"	<div class='col-sm-2 bg-info' style='border:1px solid #cecece;'>\n"+
		"	<div class='col-sm-12'>\n"+
		"	{admin_panel}\n"+
		"	</div></div>\n"+
		"	<div class='col-sm-10 bg-dark text-white'>\n"+
		"	<div class='col-sm-12'>\n"+
		"		<div class='well well-lg'>\n"+
		"                       	<h1>Upcoming Bookings</h1>    \n"+
		"                </div>\n"+
		"		<hr />\n"+
		"		<div class='col-sm-12'>"+
		"        <table class='table table-striped'>"+
		"    <thead>"+
		"      <tr>"+
		"        <th>Hotel</th>"+
		"        <th>Check In Date</th>"+
		"        <th>Check Out Date</th>"+
		"        <th>Room Type</th>"+
		"        <th>No of Rooms</th>"+
		"        <th>Price</th>"+
		"        <th>Discount</th>"+
		"        <th>Redeemed Price</th>"+
		"        <th>Billing Amount</th>"+
		"        <th>Guest Name</th>"+
		"        <th>Guest Mobile</th>"+
		"        <th>Action</th>"+
		"      </tr>"+
		"    </thead>"+
		"    <tbody> {inner_body} </tbody></table>\n"+
		"		</div>"+
		"	</div>\n"+
		"       </div>\n"+
		"    </div>\n";

		content = content.replace("{admin_panel}", Constants.getAdminPanel(userId));
		content = content.replace("{inner_body}", getBookingList());


		return content;
	}



	private String getBookingList() throws IOException{
		String content = "";
		
		try{
			ArrayList<BookinInfoLib> bookingInfo = BookingModel.getAllBooking();


			for(BookinInfoLib booking : bookingInfo){
	 		 			content += booking.getAdminTableData();
	 		}
 		}catch (Exception e){
 			
 		}
	
		return content;
	}

	
	

	public String getContent() throws IOException{
		String content = Constants.pageHeader()+Constants.pageNav(userId)+getBody()+Constants.pageFooter();
		
		return content;
	}

	
}

