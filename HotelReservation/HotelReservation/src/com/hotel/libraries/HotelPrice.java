package com.hotel.libraries;

import com.hotel.config.*;
import java.util.*;  

public class HotelPrice{
	private double price;
	private String name;
	private int priceId;
	private int roomCount;

	public HotelPrice(int id, double p, String n){
		price = p;
		name = n;
		priceId = id;
		roomCount = 10;
	}

	public HotelPrice(int id, double p, String n, int room){
		price = p;
		name = n;
		priceId = id;
		roomCount = room;
	}

	public int getId(){
		return priceId;
	}

	public String getName(){
		return name;
	}

	public int getRoomCount(){
		return roomCount;
	}

	public double getPrice(){
		return price;
	}
}