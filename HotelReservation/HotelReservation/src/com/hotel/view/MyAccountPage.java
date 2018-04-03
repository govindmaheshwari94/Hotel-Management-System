package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import com.hotel.libraries.*;
import com.hotel.model.*;
import java.io.*;

public class MyAccountPage{
	private int userId;
	private String error;

	public MyAccountPage(int usrId){
		error = "";
		if(usrId > 0){
			userId = usrId;
		}else{
			userId = 0;
		}

	}

	

	private String getBody() throws IOException{
		String content = "<div class='container'>\n"+
		"	<div class='col-sm-12'>\n"+
		" 		<h2>Available Redeemed Points</h2>{redeemed_point}<br />"+
		"<h2>Bookings</h2>{error}"+
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
		"      </tr>"+
		"    </thead>"+
		"    <tbody> {inner_body} </tbody></table>"+
		"	</div>"+
		"    </div>\n";

		content = content.replace("{redeemed_point}", String.valueOf(UserModel.getRedemptionPoint(userId)));
		content = content.replace("{error}", error);
		content = content.replace("{inner_body}", getBookingList());
		return content;
	}


	private String getBookingList() throws IOException{
		String content = "";
		
		try{
			ArrayList<BookinInfoLib> bookingInfo = BookingModel.getUserBooking(userId);

			if(bookingInfo.isEmpty()){
				addError("No pending booking");
			}

			for(BookinInfoLib booking : bookingInfo){
	 		 			content += booking.getTableData();
	 		}
 		}catch (Exception e){
 			addError(e.getMessage());
 		}
		
		if(!error.isEmpty()){
			return error;
		}

		return content;
	}

	public void setError(String err){
		error += "<div class='alert alert-danger'><strong>Error!</strong>"+err+"</div>";
	}

	private void addError(String err){
		error += "<div class='alert alert-danger'><strong>Error!</strong>"+err+"</div>";
	}

	public String getContent() throws IOException{
		String content = Constants.pageHeader()+Constants.pageNav(userId)+getBody()+Constants.pageFooter();
		
		return content;
	}

}

