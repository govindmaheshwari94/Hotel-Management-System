package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import java.io.*;
import com.hotel.libraries.*;
import com.hotel.model.*;

public class CustomerView{
	private String error;
	private int userId;
	private String operation;
	private int staff;

	public CustomerView(int usrId){
		staff = 0;
		if(usrId > 0){
			userId = usrId;
		}else{
			userId = 0;
		}

		error = "";

	}

	public void setOperation(String op){
		operation = op;
	}

	public void setStaffOnly(){
		staff = 1;
	}


	private String getBody() throws IOException{
		String content = "<div class='container'>\n"+
		"	<div class='col-sm-2 bg-info' style='border:1px solid #cecece;'>\n"+
		"	<div class='col-sm-12'>\n"+
		"	{admin_panel}\n"+
		"	</div></div>\n"+
		"	<div class='col-sm-10 bg-dark text-white'><div class='col-sm-12'>{error}</div>\n"+
		"	<div class='col-sm-12'>\n"+
		"		<div class='well well-lg'>\n"+
		"                       	<h1>User List</h1>    \n"+
		"                </div>\n"+
		"		<hr />\n"+
		"		<div class='col-sm-12'>"+
		"			<h2>Add User</h2> <br><hr/>{additional_info}	\n"+
		"		</div><hr/>"+
		"		<div class='col-sm-12'>"+
		"	<table class='table table-striped'><thead><tr><th>Email</th><th>Role</th><th>Mobile</th><th>Action</th></tr></thead><tbody>"+
		"			{inner_page}	</tbody></table>\n"+
		"		</div>"+
		"	</div>\n"+
		"       </div>\n"+
		"    </div>\n";

		content = content.replace("{admin_panel}", Constants.getAdminPanel(userId));
		content = content.replace("{inner_page}", getCustomerList());
		content = content.replace("{additional_info}", getCustomerForm());

		if(!error.isEmpty()){
			content = content.replace("{error}", error);
		}else{
			content = content.replace("{error}", "");
		}

		return content;
	}


	private String getCustomerForm() throws IOException{
			String content =
			"	<div class='col-sm-12'>\n"+
			"<form class='form-horizontal' action='ManageCustomer' method='POST'>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <input type='hidden' class='form-control' id='operation' name='operation' value='add'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"	  <label for='comment'>Email:</label>\n"+
			"          <input type='text' class='form-control' name='email' id='email'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <label for='comment'>Password:</label>\n"+
			"          <input type='text' class='form-control' name='password' id='password'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <label for='comment'>Mobile:</label>\n"+
			"          <input type='text' class='form-control' name='mobile' id='mobile'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <label for='comment'>Role:</label>\n"+
			" 			<label class='radio-inline'><input type='radio' name='role' value='1' >Admin</label><label class='radio-inline'><input type='radio' name='role' value='2'"+
			">Staff</label><label class='radio-inline'><input type='radio' name='role' value='3'"+
			" checked>Customer</label>"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"		 <button type='submit' class='btn btn-primary btn-block'>Add Member</button>\n"+
			"      	</div>\n"+
			"    </div>\n"+
			"  </form>\n"+
			"    </div></div>\n";

		return content;
	}


	private String getCustomerList(){
		String content = "";
		String eachDiv =
			" "+         	
  			"	<tr><td>{email}</td><td>{role}</td><td>{mobile}</td><td><a href='ManageCustomer?operation=delete&id={id}&staff={staff}' class='btn btn-info' role='button'>Delete</a><a href='ManageCustomer?operation=update&id={id}&staff={stafff}' class='btn btn-info' role='button'>Update</a></td></tr>";

		try{
			ArrayList<UserLib> allUsers = UserModel.getAllUsers(staff);

			if(allUsers.isEmpty()){
				setError("No customers available");
			}

			for(UserLib usr : allUsers){
					String tempVal = null;
	    			tempVal = eachDiv.replace("{hid}", String.valueOf(usr.getId())).replace("{id}", String.valueOf(usr.getId())).replace("{role}", usr.getRole()).replace("{email}", usr.getEmail()).replace("{mobile}", usr.getMobile());
	    			tempVal = tempVal.replace("{staff}", String.valueOf(staff));
	    			tempVal = tempVal.replace("{stafff}", String.valueOf(staff));
	 		 		content += tempVal;
	 		}
 		}catch (Exception e){
 			setError(e.getMessage());
 		}

		return content;
	}

	
	public void setError(String err){
		error = "<div class='alert alert-danger'>"+err+"</div>";
	}


	public String getContent() throws IOException{
		String content = Constants.pageHeader()+Constants.pageNav(userId)+getBody()+Constants.pageFooter();
		
		return content;
	}

	
}

