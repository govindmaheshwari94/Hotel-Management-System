package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import java.io.*;
import com.hotel.libraries.*;
import com.hotel.model.*;

public class PromoAdminView{
	private String error;
	private int userId;

	public PromoAdminView(int usrId){

		if(usrId > 0){
			userId = usrId;
		}else{
			userId = 0;
		}

		error = "";

	}

	
	private String getBody() throws IOException{
		String content = "<div class='container'>\n"+
		"	<div class='col-sm-2 bg-info' style='border:1px solid #cecece;'>\n"+
		"	<div class='col-sm-12'>\n"+
		"	{admin_panel}\n"+
		"	</div></div>\n"+
		"	<div class='col-sm-10 bg-dark text-white'><div class='col-sm-12'>{existing_image}</div>\n"+
		"<form class='form-horizontal' action='ManagePromo' method='POST'  enctype='multipart/form-data'>\n"+
		"    <div class='form-group'>\n"+
		"        <div class='col-sm-6'>\n"+
		"			<label>Promo Image</label><br><label class='custom-file'>\n"+
		"  				<input type='file' id='imgfile' name='imgfile' class='custom-file-input'>\n"+
		"  				<span class='custom-file-control'></span>\n"+
		"			</label>\n"+
		"        </div>\n"+
		"        <div class='col-sm-6'>\n"+
		"          <input type='hidden' class='form-control' name='operation' id='operation' value='add'>\n"+
		"        </div>\n"+
		"    </div>\n"+
		"    <div class='form-group'>\n"+
		"        <div class='col-sm-6'>\n"+
		"	 	 <label for='comment'>Promo Code:</label>\n"+
		"          <input type='text' class='form-control' name='code' id='code' placeholder='Promo Code'>\n"+
		"        </div>\n"+
		"    </div>\n"+
		"    <div class='form-group'>\n"+
		"        <div class='col-sm-6'>\n"+
		"          <label for='comment'>Discount Amount:</label>\n"+
		"          <input type='number' class='form-control' name='discount' id='discount' placeholder='summary'>\n"+
		"        </div>\n"+
		"    </div>\n"+
		"    <div class='form-group'>\n"+
		"        <div class='col-sm-6'>\n"+
		"		 <button type='submit' class='btn btn-primary btn-block'>Add Promo</button>\n"+
		"      	</div>\n"+
		"    </div>\n"+
		"  </form>\n"+
		"    </div></div>\n";

		content = content.replace("{admin_panel}", Constants.getAdminPanel(userId));

		if(!error.isEmpty()){
			content = content.replace("{error}", error);
		}else{
			content = content.replace("{error}", "");
		}

		content = content.replace("{existing_image}", getExisingImage());
		return content;
	}

	private String getExisingImage() throws IOException{ 
		ArrayList<PromoLib> promo= PromoModel.getPromo();
		String content = "<div class='row'>";

		if(promo.isEmpty()){
			return content + "</div>";
		}

		for (int i = 0; i < promo.size(); i++) {
			content += "<div class='col-md-4' id='"+promo.get(i).getId()+"''> <div class='thumbnail'> <img src='"+promo.get(i).getImage()+"' style='width:100%'> <div class='caption'>";
         	content += "<form action='ManagePromo' method='post'>"+
                                	"<input type='hidden'  name='id' value='"+promo.get(i).getId()+"'>"+
 	                               "<input type='hidden' id='operation' name='operation' value='delete'>"+
 	                               "<input type='number' readonly id='discount' name='discount' value='"+promo.get(i).getDiscount()+"'>"+
                 					"<button type='submit' class='btn btn-primary btn-block'>Delete Image</button>"+
    					"</form></div></div></div>";
		}
		content += "</div>";
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

