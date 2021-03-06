package com.FCI.SWE.Models;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * <h1>User Entity class</h1>
 * <p>
 * This class will act as a model for user, it will holds user data
 * </p>
 *
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 */
public class UserEntity {
	public String name;
	public String email;
	private String password;
    private long id;
    private static UserEntity currentActiveUser;  
	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	
	public UserEntity(String name, String email,String passward) {
		this.name = name;
		this.email = email;
		this.password=passward;
	}

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public UserEntity(String name, String email) {
		this.name=name;
		this.email=email;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	
	public void setEmail(String Email)
	{
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}
	public String getPass() {
		return password;
	}

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static UserEntity getUser(String json) {

		JSONParser parser = new JSONParser();
		try {
			JSONObject object = (JSONObject) parser.parse(json);
			currentActiveUser =new UserEntity(object.get("name").toString(), object.get(
					"email").toString(), object.get("id").toString());
			currentActiveUser.setId(Long.parseLong(object.get("id").toString()));
			return currentActiveUser;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	public static UserEntity parseUserInfo(String json)
	{
		JSONParser parser = new JSONParser();
		try{
		JSONObject object = (JSONObject) parser.parse(json);
		UserEntity user = new UserEntity();
		user.setName(object.get("name").toString());
		user.setEmail(object.get("email").toString());
		return user;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * This static method will form UserEntity class using user name and
	 * password This method will serach for user in datastore
	 * 
	 * @param name
	 *            user name
	 * @param pass
	 *            user password
	 * @return Constructed user entity
	 */

	public static UserEntity getUser(String name, String pass) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("name").toString().equals(name)
					&& entity.getProperty("password").toString().equals(pass)) {
				UserEntity returnedUser = new UserEntity(entity.getProperty(
						"name").toString(), entity.getProperty("email")
						.toString(), entity.getProperty("password").toString());
				return returnedUser;
			}
		}
		return null;
		}
		
		
		
		public static UserEntity getuser(String name) {
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();

			Query gaeQuery = new Query("users");
			PreparedQuery pq = datastore.prepare(gaeQuery);
			for (Entity entity : pq.asIterable()) {
				System.out.println(entity.getProperty("name").toString());
				if (entity.getProperty("name").toString().equals(name)) {
					UserEntity returnedUser = new UserEntity(entity.getProperty(
							"name").toString(), entity.getProperty("email")
							.toString());
					return returnedUser;
				}
			}


		return null;
	}

	/**
	 * This method will be used to save user object in datastore
	 * 
	 * @return boolean if user is saved correctly or not
	 */
	public Boolean saveUser() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("name", this.name);
		employee.setProperty("email", this.email);
		employee.setProperty("password", this.password);
		datastore.put(employee);

		return true;

	}
	public static boolean getUsers(String email) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) 
		{
			if (entity.getProperty("email").toString().equals(email))
			{	
				return true;

					 
					 }
					 }return false;
	}
	public static Vector<UserEntity> searchUser(String uname)
	{
		DatastoreService dataStore = DatastoreServiceFactory
				.getDatastoreService();
		Query gae = new Query("users");
		PreparedQuery prepareQuery = dataStore.prepare(gae);
		Vector<UserEntity> returnedUsers = new Vector<UserEntity>();
		for(Entity entity : prepareQuery.asIterable())
		{
			entity.getKey().getId();
			String currentName = entity.getProperty("name").toString();
			if(currentName.contains(uname))
			{
				UserEntity user = new UserEntity(entity.getProperty("name").toString(),entity.getProperty("email").toString());
				returnedUsers.add(user);
			}
		}
		return returnedUsers;
	}
	
	
}


