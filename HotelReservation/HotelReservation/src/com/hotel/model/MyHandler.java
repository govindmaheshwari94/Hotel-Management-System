import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class MyHandler extends DefaultHandler {

    private HashMap<String,Hotel> hotelist = null;
    
    private Hotel hotel = null;


    public HashMap<String,Hotel> getEmpList() {
        return hotelist;
    }

    boolean bName = false;
    boolean bdescription = false;
    boolean bavg_rating = false;
    boolean bprice = false;
    boolean baddress = false;
    boolean bcity=false;
    boolean bimage=false;
    boolean bcheckin=false;
	 boolean bcheckout=false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("watch" )) {
           
            String id = attributes.getValue("id");
            
            hotel = new Hotel();
            hotel.setId(id);
   
            if (hotellist == null)
                hotellist = new HashMap<String,Hotel>();
        } 
        
  
        else if (qName.equalsIgnoreCase("Name")) {
            
            bName = true;
        } else if (qName.equalsIgnoreCase("description")) {
            bdescription = true
        } else if (qName.equalsIgnoreCase("avg_rating")) {
            bavg_rating = true;
        }else if (qName.equalsIgnoreCase("price")) {
            bprice = true;
        } else if (qName.equalsIgnoreCase("address")) {
            baddress = true;
        }  else if (qName.equalsIgnoreCase("city")) {
            bcity = true;
        }  else if (qName.equalsIgnoreCase("image")) {
            bimage = true;
        } 
          else if (qName.equalsIgnoreCase("checkin")) {
            bcheckin = true;
        } 
		
		else if (qName.equalsIgnoreCase("checkout")) {
            bcheckout = true;
        }
         }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("hotel"))
        		{
          
            hotellist.put(hotel.getId(),hotel);
        }
  
        }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bimage) {
           
            hotel.setImage(new String(ch, start, length));
            bImage = false;
        } else if (bName) {
            hotel.setName(new String(ch, start, length));
            bName = false;
        } else if (bdescription) {
            hotel.setDescription(new String(ch, start, length));
            bdescription = false;
        } else if (bprice) {
            hotel.setPrice(Double.parseDouble(new String(ch, start, length)));
            bprice = false;
        }
        else if (bcity) {
			
			hotel.setCity(new String(ch, start, length));
           
            bcity = false;
        }
         else if (bcheckin) {
        
              hotel.setCheckin(Integer.parseInt(new String(ch, start, length)));
            bcheckin = false;
        }
         else if (bcheckout) {
        
             hotel.setCheckout(Integer.parseInt(new String(ch, start, length)));
            bcheckout = false;
        }
		
		else if (bavg_rating) {
        
             hotel.setAvg_rating(Integer.parseInt(new String(ch, start, length)));
            bavg_rating = false;
        }
		
		 else if (baddress) {
        
             hotel.setAddress(new String(ch, start, length));
            baddress = false;
        }
		
		
    }

}
