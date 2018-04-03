package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import com.hotel.libraries.*;
import com.hotel.model.*;
import java.sql.*;
import java.io.*;

public class BookingSummaryPage{
	private int userId;
	private int searchId;
    private int hotelId;
    private int roomId;
    private int roomCount;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;
    private String success;
    private String error;
    private HotelLib hotel;
    private boolean redeem;
    private String promo;

	public BookingSummaryPage(int usrId, int srchId, int htlId, int rmId) throws IOException{
		userId = usrId;
		searchId = srchId;
		hotelId = htlId;
		roomId = rmId;
		success = "";
		error = "";
		promo = "";
		redeem = false;

		try{
			hotel = HotelModel.getHotel(hotelId);
			SearchLib tempRslt = SearchModel.getResult(searchId);
			roomCount = tempRslt.getRoomCount();
			checkInDate = tempRslt.getCheckIn();
			checkOutDate = tempRslt.getCheckOut();
			totalPrice = tempRslt.getRoomCount() * PriceModel.getPrice(roomId) * BookingModel.getDays(tempRslt.getCheckIn(), tempRslt.getCheckOut());
		}catch (SQLException e){
			throw new IOException(e.getMessage());
		}
	}

	public void availRedeem(){
		redeem = true;
	}

	public void setPromo(String pr){
		promo = pr;
	}

	private String getBody() throws IOException{
		String content = "<div class='container'>\n"+
			"<form class='form-horizontal' action='Booking'>\n";

			if(!success.isEmpty()){
				content += success;
			}

			content += "    <div class='form-group'>{error}\n"+
			"	<div class='col-sm-12'>\n"+
			"        <input type='hidden' class='form-control' id='hotelid' name='hotelid' value='{hotelid}'>\n"+
			"	</div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-12'>\n"+
			"        <input type='hidden' class='form-control' id='searchid' name='searchid' value='{searchid}'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-12'>\n"+
			"        <input type='hidden' class='form-control' id='roomtype' name='roomtype' value='{roomid}'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"\n"+
			"    <div class='form-group'>\n"+
			"      <div class='col-sm-6'>\n"+
			"                    <label>Hotel</label>\n"+
			"                    <input type='text' class='form-control' id='hotelname' name='hotelname' value='{hotelname}' readonly/>\n"+
			"      </div>\n"+
			"        <div class='col-sm-6'>\n"+
			"                    <label>Rooms</label>\n"+
			"                    <input type='text' class='form-control' id='roomcount' name='roomcount' value='{roomcount}' readonly/>\n"+
			"      </div>\n"+
			"    </div>\n"+
			"\n"+
			"    <div class='form-group'>\n"+
			"      <div class='col-sm-6'>          \n"+
			"		    <label>Check In</label>\n"+
			"                    <input type='text' class='form-control' id='checkin' name='checkin' value='{checkin}' readonly/>\n"+
			"      </div>\n"+
			"	<div class='col-sm-6'>          \n"+
			"                    <label>Check Out</label>\n"+
			"                    <input type='text' class='form-control' id='checkout' name='checkout' value='{checkout}' readonly/>\n"+
			"      </div>\n"+
			"    </div>\n"+
			"\n"+
			"    <div class='form-group'>\n"+
			"      <div class='col-sm-6'>          \n"+
			"                    <label>Name</label>\n"+
			"                    <input type='text' class='form-control' id='guest_name' name='guest_name' placeholder='Enter guest name'/>\n"+
			"      </div>\n"+
			"        <div class='col-sm-6'>\n"+
			"                    <label>Mobile</label>\n"+
			"                    <input type='number' class='form-control' id='guest_mobile' name='guest_mobile' placeholder='Enter Mobile number'/>\n"+
			"      </div>\n"+
			"    </div>\n"+
			"\n"+
			
			"	<div class='form-group'>        \n"+
			"    <div class='col-sm-6'>\n"+
			"        <label>Promo Code</label>"+
			"		 <input type='text' value='' name='promo_code' id='promo_code' placeholder='Get Discount'/>\n"+
			"    </div>\n"+
			"    <div class='col-sm-6'>\n{redeem_block}"+
			"    </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>        \n"+
			"    <div class='col-sm-6'>\n"+
			"        <label>Total Price</label>"+
			"		 <input type='text' value='{total_price}' readonly/>\n"+
			"      <div class='col-sm-6'>\n"+
			"		 <button type='submit' class='btn btn-primary btn-block'>Confirm Booking</button>\n"+
			"      	</div>\n"+
			"    </div>\n"+
			"    </div>\n"+
			"  </form>\n"+
			"    </div>\n";


		int redemptionPoint = UserModel.getRedemptionPoint(userId);

		String redeemBlock = "<label>Redeem points ("+String.valueOf(redemptionPoint)+")</label> <input type='checkbox' value='redeem' name='redeem' id='redeem'/>\n";
		
		if(redemptionPoint >= Constants.redeemMin){
			content = content.replace("{redeem_block}", redeemBlock);
		}else{
			content = content.replace("{redeem_block}", "");	
		}

		content = content.replace("{hotelid}", String.valueOf(hotelId));
		content = content.replace("{searchid}", String.valueOf(searchId));
		content = content.replace("{roomid}", String.valueOf(roomId));
		content = content.replace("{hotelname}", this.hotel.getName());
		content = content.replace("{checkin}", checkInDate);
		content = content.replace("{checkout}", checkOutDate);
		content = content.replace("{total_price}", String.valueOf(totalPrice));
		content = content.replace("{roomcount}", String.valueOf(roomCount));
		content = content.replace("{error}", error);
		
		return content;
	}

	public void setSuccess(String err){
		success = "<div class='alert alert-success'><strong>Success!</strong>"+err+"</div>";
	}

	public void setError(String err){
		error += "<div class='alert alert-danger'><strong>Error!</strong>"+err+"</div>";
	}

	private void addError(String err){
		error += "<div class='alert alert-danger'><strong>Error!</strong>"+err+"</div>";
	}

	public String getContent() throws IOException{
		String content = Constants.pageHeader()+Constants.pageNav(userId)+getBody()+Constants.getPromoCarausel()+Constants.pageFooter();
		return content;
	}

	public void book(String name, String mobile){
		
		try{
			if(!HotelModel.checkAvailability(roomId, roomCount)){
				addError("No rooms available");
				return;
			}
			int redemptionPoint = UserModel.getRedemptionPoint(userId);

			if(redeem && (redemptionPoint >= Constants.redeemMin)){

			}else{
				redemptionPoint = 0;
			}

			int id = BookingModel.addBooking(userId, searchId, roomId, hotelId, name, mobile, redemptionPoint, promo);

			UserModel.addRedemption(userId);
			
			if(id > 0){
				setSuccess("Booking confirmed");
			}else{
				addError("Booking not done");
			}
		}catch (SQLException e){
				addError(e.getMessage());
		}catch (IOException e){
			addError(e.getMessage());
		}
	}
}

