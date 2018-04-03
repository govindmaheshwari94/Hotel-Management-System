package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class trending1model{
	
	public HashMap<String, Integer> trending1() throws SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "select name, price from hotels order by price asc limit 5; ";
		
		HashMap<String,Integer> trending1=new HashMap<String, Integer>();

		
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  
			
			rs = stmt.executeQuery();  

			while(rs.next()){
				String name=rs.getString("name");
 		int price=rs.getInt("price");
 		
 		trending1.put(name, price);
			}

		} catch (SQLException e){
			throw new SQLException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new SQLException(e.getMessage());
		} 

		return trending1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
