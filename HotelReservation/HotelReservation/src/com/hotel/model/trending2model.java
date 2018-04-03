package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class trending2model{
	
	public HashMap<String, Integer> trending2() throws SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "select name, avg_rating from hotels order by price asc limit 5; ";
		
		HashMap<String,Integer> trending2=new HashMap<String, Integer>();

		
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  
			
			rs = stmt.executeQuery();  

			while(rs.next()){
				String hname=rs.getString("name");
 		int rating=rs.getInt("avg_rating");
 		
 		trending2.put(hname, rating);
			}

		} catch (SQLException e){
			throw new SQLException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new SQLException(e.getMessage());
		} 

		return trending2;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
