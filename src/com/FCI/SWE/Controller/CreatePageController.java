package com.FCI.SWE.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@Path("/")
@Produces("text/html")

public class CreatePageController {
	
	

		@GET
		@Path("/CreatePage")
		public Response CreatePage() {


			return Response.ok(new Viewable("/jsp/CreatePage")).build();
		}
		
		
		@POST
		@Path("/view")
		//@Produces("text/html")
		public String view(@FormParam("name") String name,@FormParam("category") String category) throws ParseException {
			
			String serviceUrl = "http://localhost:8888/rest/CreatePageService";
			try {
				URL url = new URL(serviceUrl);
				String urlParameters = "name=" + name +"&category="+category;
				//String urlParameters = "uname=" + uname + "&email=" + email+ "&password=" + pass;
				HttpURLConnection connection = (HttpURLConnection) url
						.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setInstanceFollowRedirects(false);
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(60000);  //60 Seconds
				connection.setReadTimeout(60000);  //60 Seconds
				connection.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded;charset=UTF-8");
				OutputStreamWriter writer = new OutputStreamWriter(
						connection.getOutputStream());
				writer.write(urlParameters);
				writer.flush();
				String line, retJson = "";
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));

				while ((line = reader.readLine()) != null) {
					retJson += line;
				}
				writer.close();
				reader.close();
			JSONParser parser = new JSONParser();
			Object obj;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			return "Failed";
			

		}

		
		
		
		
		


}