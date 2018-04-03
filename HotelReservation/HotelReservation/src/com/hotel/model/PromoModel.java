package com.hotel.model;
import com.hotel.config.*;
import com.hotel.libraries.*;
import java.sql.*;
import java.io.*;
import java.util.*;  

public class PromoModel{
	

	public static ArrayList<PromoLib> getPromo() throws IOException{
		String userId = null;
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT `id`, `code`, `image_src`, `discount_amount` FROM promo_code ";
		String whereClause = "";

		ArrayList<PromoLib> promoList = new ArrayList<PromoLib>();
	
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(selectSQL);  
			
			rs = stmt.executeQuery();  

			while(rs.next()){
				PromoLib onePromo = new PromoLib(rs.getInt(1), rs.getInt(4), rs.getString(2), rs.getString(3)); 
				
				
				promoList.add(onePromo);
			}

		} catch (SQLException e){
			throw new IOException(stmt.toString() + e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		}

		return promoList;
	}


	public static int addPromo(PromoLib promo) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		int autoId = -1;

		String insertQuery = "INSERT INTO `promo_code`( `code`, `image_src`, `discount_amount`) VALUES ( ?, ?, ?)";
	
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);  
		
			stmt.setString(1, promo.getCode());
			stmt.setString(2, promo.getImage());
			stmt.setInt(3, promo.getDiscount());

			int affectedRows = stmt.executeUpdate();

			if(affectedRows > 0) {
 				ResultSet generatedKeys = stmt.getGeneratedKeys();
    			
    			if (generatedKeys.next()) {
                	autoId = generatedKeys.getInt(1);
                	promo.setId(autoId);	

                	insertQuery = "update promo_code set image_src ='"+promo.getImage()+"' where id ="+promo.getId();

                	conn = Database.getConnection();
					stmt = conn.prepareStatement(insertQuery);
					stmt.executeUpdate();				
            	}else{
            		throw new SQLException("Unable to retrieve key" + insertQuery ); 
            	}
			}

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		}

		return autoId;
	}


	public static void deletePromo(int id) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;

		String insertQuery = "delete from `promo_code` where id = ? ";
	
		try{
			conn = Database.getConnection();

			stmt = conn.prepareStatement(insertQuery);  
			stmt.setInt(1, id);

			stmt.executeUpdate();

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		}

	}

	public static boolean validPromo(String promo) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT code FROM promo_code where code = ?";
		boolean present = false;
	
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);

			stmt.setString(1, promo);

			rs = stmt.executeQuery();  

			while(rs.next()){
				present = true; 
			}

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 	

		return present;
	}

	public static int getDiscount(String promo) throws IOException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT discount_amount FROM promo_code where code = ?";
		int discount = 0;	
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);

			stmt.setString(1, promo);

			rs = stmt.executeQuery();  

			while(rs.next()){
				discount = rs.getInt(1); 
			}

		} catch (SQLException e){
			throw new IOException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new IOException(e.getMessage());
		} 	

		return discount;
	}

	public static ArrayList<String> getImage() throws SQLException{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String selectSQL = "SELECT image_src FROM promo_code ";
		
		ArrayList<String> imageList = new ArrayList<String>();

		
		try{
			conn = Database.getConnection();
			stmt = conn.prepareStatement(selectSQL);  
			
			rs = stmt.executeQuery();  

			while(rs.next()){
				imageList.add(rs.getString(1)); 
			}

		} catch (SQLException e){
			throw new SQLException(e.getMessage());  
		} catch (ClassNotFoundException e){
			throw new SQLException(e.getMessage());
		} 

		return imageList;
	}
	

}
