package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class chart2model{
	
	public HashMap<String, Integer> chart2() throws SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "select hotels.name, sum(booking_info.total_amount) from booking_info inner join hotels on booking_info.id_hotel=hotels.id; ";
		
		HashMap<String,Integer> charts2=new HashMap<String, Integer>();

		
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  
			
			rs = stmt.executeQuery();  

			while(rs.next()){
				String name=rs.getString(1);
 		int total_price=rs.getInt(2);
 		
 		charts2.put(name, total_price);
			}

		} catch (SQLException e){
			throw new SQLException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new SQLException(e.getMessage());
		} 

		return charts2;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
