package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.util.*;  

public class UserModel{
	
	public static String authenticateUser(String email, String password) throws IOException, Exception{
		String userId = null;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT id FROM users WHERE email = ? and password = ?";
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(selectSQL);  
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			rs=stmt.executeQuery();  

			while(rs.next()){
				userId = Integer.toString(rs.getInt(1));  
			}

		} catch (Exception e){
			throw new Exception(e.getMessage());  
		}

		return userId;
	}

	public static int getRedemptionPoint(int userId) throws IOException{
		int redeem = 0;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT redeem_value FROM users WHERE id = ?";
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(selectSQL);  
			stmt.setInt(1, userId);
			
			rs=stmt.executeQuery();  

			while(rs.next()){
				redeem = rs.getInt(1);  
			}

		} catch (Exception e){
			throw new IOException(e.getMessage());  
		}

		return redeem;
	}

	public static int getUserRole(int userId) throws IOException{
		int role = 3;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT role FROM users WHERE id = ?";
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(selectSQL);  
			stmt.setInt(1, userId);
			
			rs=stmt.executeQuery();  

			while(rs.next()){
				role = rs.getInt(1);  
			}

		} catch (Exception e){
			throw new IOException(e.getMessage());  
		}

		return role;	
	}

	public static ArrayList<UserLib> getAllUsers(int staff) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL  = "SELECT  id, role, mobile, email, password FROM users ";
		ArrayList<UserLib> users = new ArrayList<UserLib>();

		if(staff == 1){
			selectSQL += " where role < 3";
		}
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(selectSQL);  
			rs=stmt.executeQuery();  

			while(rs.next()){
				users.add(new UserLib(rs.getInt(1), rs.getString(4), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}

		} catch (Exception e){
			throw new IOException(e.getMessage());  
		}

		return users;	
	}

	public static UserLib getUser(int userId) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL  = "SELECT  id, role, mobile, email, password FROM users where id = ? ";
		UserLib users = null;
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(selectSQL);  
			stmt.setInt(1, userId);
			
			rs=stmt.executeQuery();  

			while(rs.next()){
				users = new UserLib(rs.getInt(1), rs.getString(4), rs.getInt(2), rs.getString(3), rs.getString(5));
			}

		} catch (Exception e){
			throw new IOException(e.getMessage());  
		}

		return users;	
	}

	public static int addUser(String email, String password, int role, String mobile) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		int autoId = -1;

		String insertQuery = "INSERT INTO `users`(`email`, `password`, `mobile`, `role`) VALUES (?, ?, ?, ?)";
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);  
			stmt.setString(1, email);
			stmt.setString(2, password);
			stmt.setString(3, mobile);
			stmt.setInt(4, role);
			
			int affectedRows = stmt.executeUpdate();

			if(affectedRows > 0) {
 				ResultSet generatedKeys = stmt.getGeneratedKeys();
    			
    			if (generatedKeys.next()) {
                	autoId = generatedKeys.getInt(1);
            	}else{
            		throw new IOException("Unable to retrieve key"); 
            	}
			}

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 

		return autoId;
	}

	public static void updateUser(UserLib user) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		int autoId = -1;

		String insertQuery = " update `users` set email = ? , password = ? , mobile = ? , role = ? where id = ? ";
		
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(insertQuery);  
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getMobile());
			stmt.setInt(4, user.getRoleId());
			stmt.setInt(5, user.getId());
			
			int affectedRows = stmt.executeUpdate();


		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 
	}

	public static void addRedemption(int userId) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;

		String updateQuery = "update `users` set redeem_value = redeem_value + 1 where id = ?";

		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);  
			stmt.setInt(1, userId);
			
			stmt.executeUpdate();

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 
		
	}

	public static void updateRedemption(int userId, int points) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;

		String updateQuery = "update `users` set redeem_value = redeem_value - ? where id = ?";

		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);  
			stmt.setInt(1, points);
			stmt.setInt(2, userId);
			
			stmt.executeUpdate();

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 
		
	}

	public static void deleteUser(int userId) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;

		String updateQuery = "delete from `users` where id = ?";

		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);  
			stmt.setInt(1, userId);
			
			stmt.executeUpdate();

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 
		
	}
}
