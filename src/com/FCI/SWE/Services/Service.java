package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.FriendRequestEntity;
import com.FCI.SWE.Models.UserEntity;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType .TEXT_PLAIN)
public class Service {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
		}

		return object.toString();

	}
	@POST
	@Path("/addFriendService")
	public String addFriendservice(@FormParam("email1") String email1,
			@FormParam("email2") String email2) {
		JSONObject object = new JSONObject();
		boolean  Email1 = UserEntity.getUsers(email1);
		boolean  Email2 = UserEntity.getUsers(email2);
		
		if (Email1 == false || Email2==false) {
			object.put("Status", "Failed");

		} else {
			
			FriendRequestEntity request =new FriendRequestEntity(email1,email2,"unaccepted");
			request.saveRequest();
			object.put("Status", "OK");
			
		}
		return object.toString();

	}
	@POST
	@Path("/showRequestService")
	public String showRequestService(@FormParam("email1") String email1)
	{
		JSONObject object = new JSONObject();
		FriendRequestEntity F=new FriendRequestEntity();
		String  Email2 = F.getUsers(email1);
		
		
		if (Email2=="Failed") {
			object.put("Status", "Failed");

		} else {
			
			object.put("Status", Email2);
		}
			
		return object.toJSONString();
	}
	@POST
	@Path("/acceptFriendService")
	public String acceptFriendService(@FormParam("email1") String email1,@FormParam("email2") String email2)
	{
		JSONObject object = new JSONObject();
		FriendRequestEntity F=new FriendRequestEntity();
		String  Email1 = F.getUsers2(email1);
		String  Email2 = F.getUsers2(email2);

		if (Email2=="Failed" || Email1=="Failed") {
			object.put("Status", "Failed");
			
		} else {

			F.accept(Email1, Email2);
			object.put("Status", "OK");

		}
			
		return object.toJSONString();
	}
	
	@POST
	@Path("/SearchService")
	public String SearchService(@FormParam("name") String name)
	{
		Vector<UserEntity> users = UserEntity.searchUser(name);
		JSONArray returnedJson = new JSONArray();
		for(UserEntity user : users)
		{
			JSONObject object = new JSONObject();
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			
			returnedJson.add(object);
		}
		
		return returnedJson.toString();

	
	}
	

}