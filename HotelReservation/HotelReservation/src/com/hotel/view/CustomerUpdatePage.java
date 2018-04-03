package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import java.io.*;
import com.hotel.libraries.*;
import com.hotel.model.*;

public class CustomerUpdatePage{
	private String error;
	private int userId;
	private String staffId;
	private int staff;

	public CustomerUpdatePage(int usrId, String stfId){

		if(usrId > 0){
			userId = usrId;
		}else{
			userId = 0;
		}
		staffId = stfId;
		staff = 0;

		error = "";

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
		"                       	<h1>User Info</h1>    \n"+
		"                </div>\n"+
		"		<hr />\n"+
		"		<div class='col-sm-12'>"+
		"			{additional_info}	\n"+
		"		</div>"+
		"	</div>\n"+
		"       </div>\n"+
		"    </div>\n";

		content = content.replace("{admin_panel}", Constants.getAdminPanel(userId));
		content = content.replace("{additional_info}", getCustomerForm());		

		if(!error.isEmpty()){
			content = content.replace("{error}", error);
		}else{
			content = content.replace("{error}", "");
		}

		return content;
	}

	private String getCustomerForm() throws IOException{
			UserLib usr = UserModel.getUser(Integer.parseInt(staffId));
			int roleId = usr.getRoleId();
			String content =
			"	<div class='col-sm-12'>\n"+
			"<form class='form-horizontal' action='ManageCustomer' method='POST'>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"  				<input type='hidden' id='id' name='id' value='"+usr.getId()+"'>"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <input type='hidden' class='form-control' id='operation' name='operation' value='update'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"	  <label for='comment'>Email:</label>\n"+
			"          <input type='text' class='form-control' name='email' id='email' value ='"+usr.getEmail()+"'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <label for='comment'>Password:</label>\n"+
			"          <input type='text' class='form-control' name='password' id='password' value ='"+usr.getPassword()+"'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <label for='comment'>Mobile:</label>\n"+
			"          <input type='text' class='form-control' name='mobile' id='mobile'  value ='"+usr.getMobile()+"'>\n"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"          <label for='comment'>Role:</label>\n"+
			" 			<label class='radio-inline'><input type='radio' name='role' value='1'";
			
			if(roleId == 1){
				content += "checked";
			}
			
			content += ">Admin</label><label class='radio-inline'><input type='radio' name='role' value='2'";

			if(roleId == 2){
				content += "checked";
			}

			content += " 			>Staff</label><label class='radio-inline'><input type='radio' name='role' value='3'";

			if(roleId == 3){
				content += "checked";
			}

			content += " 			>Customer</label>"+
			"        </div>\n"+
			"    </div>\n"+
			"    <div class='form-group'>\n"+
			"        <div class='col-sm-6'>\n"+
			"		 <button type='submit' class='btn btn-primary btn-block'>Update Staff</button>\n"+
			"      	</div>\n"+
			"    </div>\n"+
			"  </form>\n"+
			"    </div></div>\n";

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

