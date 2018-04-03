package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class chart1model{
	
	public HashMap<String, Integer> chart1() throws SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "select hotels.name, hotel_price.room_count from hotels inner join hotel_price on hotels.id=hotel_price.id_hotel group by hotels.name ";
		
		HashMap<String,Integer> charts=new HashMap<String, Integer>();

		
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  
			
			rs = stmt.executeQuery();  

			while(rs.next()){
				String name=rs.getString("name");
 		int room_count=rs.getInt("room_count");
 		
 		charts.put(name, room_count);
			}

		} catch (SQLException e){
			throw new SQLException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new SQLException(e.getMessage());
		} 

		return charts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
