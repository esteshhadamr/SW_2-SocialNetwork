package com.FCI.SWE.Models;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
//import com.google.appengine.repackaged.com.google.api.services.datastore.DatastoreV1.DatastoreService;
//import com.google.appengine.repackaged.com.google.api.services.datastore.DatastoreV1.DatastoreService;
import java.util.List;

public class FriendRequestEntity {
	private String email1;
	private String email2;
	private String status;
	public FriendRequestEntity(String email1, String email2,String status) {
		this.email1=email1;
		this.email2=email2;
		
		this.status=status;
	}
	
	
	public FriendRequestEntity() {
		// TODO Auto-generated constructor stub
	}


	public String getEmail1() {
		return email1;
	}public String getEmail2() {
		return email2;
	}

public void setEmail1(String email1) {
		this.email1 = email1;
	}public void setEmail2(String email2) {
		this.email2 = email2;
	}
public String getStatus() {
	return status;
}public void setStatus(String status) {
	this.status = status;
}
	
	public Boolean saveRequest() {
		com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List list = (List) pq.asList(FetchOptions.Builder.withDefaults());

		Entity request = new Entity("requests", list.size() + 1);

		request.setProperty("email1", this.email1);
		request.setProperty("email2", this.email2);
		request.setProperty("status",this.status);
		datastore.put(request);
			return true;

	}

	
public static String getUsers(String email2) {
		com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("requests");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("email2").toString().equals(email2))
					{
				String email1 =entity.getProperty("email1")
						.toString();

				return email1;
	}
}return "Failed";
		
}

public static String getUsers2(String email) {
	com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();

	Query gaeQuery = new Query("requests");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	for (Entity entity : pq.asIterable()) {
		if (entity.getProperty("email2").toString().equals(email)||
				entity.getProperty("email1").toString().equals(email))
				{
			
			return email;
}
}
	
	return "Failed";
	
}
public static void accept(String email1,String email2){
	com.google.appengine.api.datastore.DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("requests");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	for (Entity entity : pq.asIterable()) {
		if (entity.getProperty("email2").toString().equals(email1)
				&& entity.getProperty("email1").toString().equals(email2))
		{
			entity.setProperty("status", "Accepted");
			datastore.put(entity);
		return;
		}

			
			}
}

}
