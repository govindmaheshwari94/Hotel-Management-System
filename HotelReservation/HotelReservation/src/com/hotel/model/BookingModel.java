package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.*;  


public class BookingModel{
	
	public static double getDays(String day1, String day2) throws IOException{
		try{
			Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse(day1);
			Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse(day2);

			return (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
		}catch (ParseException e){
			throw new IOException(e.getMessage());
		}
	}

	public static ArrayList<BookinInfoLib> getUserBooking(int userId) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT b.id, h.name, b.check_in, b.check_out, p.name, b.room_count, b.totalPrice, b.discount_amount, b.redeemed_point, b.total_amount, b.guest_name, b.guest_mobile  FROM booking_info b, hotels h, hotel_price p WHERE b.id_hotel = h.id and b.id_price = p.id and id_user = ? order by check_out desc";
		ArrayList<BookinInfoLib> bookingList = new ArrayList<BookinInfoLib>();

		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  
			stmt.setInt(1, userId);
			rs = stmt.executeQuery();  

			while(rs.next()){
				int tempId = rs.getInt(1);
				BookinInfoLib oneInfo = new BookinInfoLib(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getDouble(10), rs.getString(11), rs.getString(12)); 
			
				bookingList.add(oneInfo);
			}

		} catch (SQLException e){
			throw new IOException(stmt.toString() + e.getMessage());  
		}catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		}

		return bookingList;
	}

	public static ArrayList<BookinInfoLib> getAllBooking() throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT b.id, h.name, b.check_in, b.check_out, p.name, b.room_count, b.totalPrice, b.discount_amount, b.redeemed_point, b.total_amount, b.guest_name, b.guest_mobile  FROM booking_info b, hotels h, hotel_price p WHERE b.id_hotel = h.id and b.id_price = p.id  order by check_out desc";
		ArrayList<BookinInfoLib> bookingList = new ArrayList<BookinInfoLib>();

		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  

			rs = stmt.executeQuery();  

			while(rs.next()){
				BookinInfoLib oneInfo = new BookinInfoLib(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getDouble(10), rs.getString(11), rs.getString(12)); 
			
				bookingList.add(oneInfo);
			}

		} catch (SQLException e){
			throw new IOException(stmt.toString() + e.getMessage());  
		}catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		}

		return bookingList;
	}


	public static int addBooking(int userId, int searchId, int roomId, int hotelId, String guestName, String guestMobile, int redemptionPoint, String promo) throws IOException, SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		int autoId = -1;
		int discount = 0;
		double finalPrice = 0;
		double totalPrice = 0;

		String insertQuery = "INSERT INTO `booking_info`(`id_user`, `id_hotel`, `id_price`, `room_count`, `check_in`, `check_out`, `totalPrice`, `bookingDate`, `guest_name`, `guest_mobile`, `redeemed_point`, `promo_code`, `discount_amount`, `total_amount`) VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?)";
		
		if(promo != null && !promo.isEmpty()){
			discount = PromoModel.getDiscount(promo);
		}

		

		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);  
			stmt.setInt(1, userId);
			stmt.setInt(2, hotelId);
			stmt.setInt(3, roomId);
			
			SearchLib tempRslt = SearchModel.getResult(searchId);

			totalPrice = tempRslt.getRoomCount()*PriceModel.getPrice(roomId) * BookingModel.getDays(tempRslt.getCheckIn(), tempRslt.getCheckOut());
			finalPrice = totalPrice - redemptionPoint - discount;

			if(finalPrice < 0){
				finalPrice = 0;
			}

			stmt.setInt(4, tempRslt.getRoomCount());
			stmt.setString(5, tempRslt.getCheckIn());
			stmt.setString(6, tempRslt.getCheckOut());
			stmt.setDouble(7, totalPrice);
			stmt.setString(8, guestName);
			stmt.setString(9, guestMobile);
			stmt.setInt(10, redemptionPoint);
			stmt.setString(11, promo);
			stmt.setInt(12, discount);
			stmt.setDouble(13, finalPrice);

			int affectedRows = stmt.executeUpdate();

			if(affectedRows > 0) {
 				ResultSet generatedKeys = stmt.getGeneratedKeys();
    			
    			if (generatedKeys.next()) {
                	autoId = generatedKeys.getInt(1);
            	}else{
            		throw new SQLException("Unable to retrieve key"); 
            	}
			}

			UserModel.updateRedemption(userId, redemptionPoint);

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} catch (IOException e){
			throw new IOException(e.getMessage());
		}

		return autoId;
	}

	public static void deleteBooking(int id) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		
		String insertQuery = "delete from `booking_info` where id = ? ";
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(insertQuery);  
			stmt.setInt(1, id);

			int affectedRows = stmt.executeUpdate();

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 

	}
}
