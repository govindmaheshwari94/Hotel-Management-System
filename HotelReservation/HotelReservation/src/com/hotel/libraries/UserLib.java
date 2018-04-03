package com.hotel.libraries;

import com.hotel.config.*;
import java.util.*;  

public class UserLib{
	private String mobile;
	private String role;
	private int id;
	private String email;
	private int roleId;
	private String password;

	public UserLib(int id, String email, int roleId, String mobile, String password){
		this.mobile = mobile;
		this.id = id;
		this.email = email;
		this.roleId = roleId;
		this.password = password;
		if(roleId == 1){
			role = "Admin";
		}else if(roleId == 2){
			role = "Staff";
		}else{
			role = "Customer";
		}
	}


	public int getId(){
		return id;
	}

	public String getRole(){
		return role;
	}

	public String getMobile(){
		return mobile;
	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	public int getRoleId(){
		return roleId;
	}
}