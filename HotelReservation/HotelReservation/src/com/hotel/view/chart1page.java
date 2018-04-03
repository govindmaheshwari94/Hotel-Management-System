package com.hotel.view;
import com.hotel.config.*;
import java.util.*;  
import com.hotel.libraries.*;
import com.hotel.model.*;
import java.sql.*;
import java.io.*;

public class chart1page{
	private int userId;

	public chart1page(int usrId) throws IOException{
		userId = usrId;
	}

	private String getBody(){
	
	chart1model obj= new chart1model();
		HashMap<String, Integer> prodd =new HashMap<String, Integer>();
        prodd=obj.chart1();
		String name[]=new String[prodd.size()];
    	int num[]=new int[prodd.size()];
    	
    	int index=0;
    	for (Map.Entry<String, Integer> entry : prodd.entrySet()) 
    	{
    	name[index] = entry.getKey();
    	num[index]=entry.getValue();
    	index++;
    	}
		
		String content = "<div class='container'>\n"+
                "        <div class='container'>\n"+
                "        <div class='col-sm-12'>\n"+
                "                <h3 class='text-uppercase'><em>Chart 1 - HOTEL VS ROOM COUNT</em></h3></div><div class='col-sm-12'>\n"+
				"<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>\n"+
				"<div id='chart_div' style='width: 100%; height: 1000px;'></div>\n"+
				"<script type = 'text/javascript'>\n"+
				"google.charts.load('current', {packages: ['corechart', 'bar']});\n"+
				"google.charts.setOnLoadCallback(drawBasic);\n"+

				"function drawBasic() {\n"+				

				" var data = google.visualization.arrayToDataTable([\n"+
				"['Hotel Name', 'Room Count',],\n"+
				for (int i=0;i<prodd.size(); i++)
                 {
				"['"+name[i]+"',"+num[i]+"],\n"+
					
			  }
				"]);\n"+

				" var options = {\n"+
				"   title: 'Room count of each Hotel',\n"+
				" chartArea: {width: '50%'},\n"+
				"  hAxis: {\n"+
				"    title: 'Total Room Count',\n"+
				"    minValue: 0\n"+
				"  },\n"+
				"vAxis: {\n"+
				"  title: 'Hotel Name'\n"+
				"  }\n"+
				"  };\n"+

				"var chart = new google.visualization.BarChart(document.getElementById('chart_div'));\n"+

				"  chart.draw(data, options);\n"+
				"  }\n"+		
				" </script>\n"+

                "</div>\n"+
                "</div>\n"+
                "</ hr>\n"+
                "</div>\n";

		return content;
	}


	public String getContent() throws IOException{
		String content = Constants.pageHeader()+Constants.pageNav(userId)+getBody()+Constants.pageFooter();
		return content;
	}
}
