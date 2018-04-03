

import java.util.ArrayList;
import java.util.List;

public class Hotels {
	private Int id;
    private String name;
    private String description;
    private int avg_rating;
    private Double price;
    private String address;
    private String city;
    private String image;
    private String checkin;
	private String checkout;


 public Hotels(int id, String name, String description, int avg_rating, double price, String address, String city, String image, String checkin, String checkout)
    this.id=id;
	this.name=name;
    this.description=description;
    this.avg_rating=avg_rating;
    this.price=price;
    this.address=address;
    this.city=city;
    this.image=image;
	    this.checkin=checkin;
		    this.checkout=checkout;
    }
    

    public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}

    
    
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	 public int getAvg_rating() {
		return avg_rating;
	}

	public void setAvg_rating(int avg_rating) {
		this.avg_rating = avg_rating;
	}
	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = addres;
	}
	
	
		public String getCheckin() {
		return checkin;
	}


	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	
	
		public String getCheckout() {
		return checkout;
	}


	public void setCheckout(String checkout) {
		this.checkout = checkout;
	
	
		public String getCity() {
		return city;
	}

S
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
}