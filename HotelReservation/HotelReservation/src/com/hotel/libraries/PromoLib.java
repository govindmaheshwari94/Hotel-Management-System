package com.hotel.libraries;

import com.hotel.config.*;
import java.util.*;  

public class PromoLib{
	private int discount;
	private String code;
	private int id;
	private String imgSrc;

	public PromoLib(int idd, int disc, String cod, String img){
		discount = disc;
		id = idd;
		code = cod;
		imgSrc = img;
	}

	public int getId(){
		return id;
	}

	public void setId(int i){
		id = i;
		imgSrc = "img/promo/"+String.valueOf(id)+"-promo.jpg";
	}

	public String getCode(){
		return code;
	}

	public int getDiscount(){
		return discount;
	}

	public String getImage(){
		return imgSrc;
	}
}
