package com.hotel.libraries;

import com.hotel.config.*;
import java.util.*;  

public class BookinInfoLib{
	private int bookingId;
	private String hotelName;
	private String checkInDate;
	private String checkOutDate;
	private String roomName;
	private int roomCount;
	private double price;
	private double discount;
	private int redeemedPrice;
	private double totalAmount;
	private String guestName;
	private String guestMobile;

	public BookinInfoLib(int bookingId, String hotelName, String checkInDate, String checkOutDate, String roomName, int roomCount, double price, double discount, int redeemedPrice, double totalAmount, String guestName, String guestMobile){
		this.bookingId = bookingId;
		this.hotelName = hotelName;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomName = roomName;
		this.roomCount = roomCount;
		this.price = price;
		this.discount = discount;
		this.redeemedPrice = redeemedPrice;
		this.totalAmount = totalAmount;
		this.guestName = guestName;
		this.guestMobile = guestMobile;
	}

	public String getTableData(){
		String content = "<tr><td>"+hotelName+"</td><td>"+checkInDate+"</td><td>"+checkOutDate+"</td><td>"+roomName+"</td>";
        content += "<td>"+String.valueOf(roomCount)+"</td><td>"+Double.valueOf(price)+"</td><td>"+Double.valueOf(discount)+"</td><td>"+String.valueOf(redeemedPrice)+"</td><td>"+String.valueOf(totalAmount)+"</td></tr>";

        return content;
	}

	public String getAdminTableData(){
		String content = "<tr><td>"+hotelName+"</td><td>"+checkInDate+"</td><td>"+checkOutDate+"</td><td>"+roomName+"</td>";
        content += "<td>"+String.valueOf(roomCount)+"</td><td>"+Double.valueOf(price)+"</td><td>"+Double.valueOf(discount)+"</td><td>"+String.valueOf(redeemedPrice)+"</td><td>"+String.valueOf(totalAmount)+"</td><td>"+guestName+"</td><td>"+guestMobile+"</td><td><form action = 'ManageBooking' method = 'post'><input type='hidden' class='form-control' id='operation' name='operation' value='delete'/><input type='hidden' class='form-control' id='id' name='id' value='"+String.valueOf(bookingId)+"'/><button type='submit' class='btn btn-primary btn-block'>Delete Booking</button></form></td></tr>";

        return content;
	}

}