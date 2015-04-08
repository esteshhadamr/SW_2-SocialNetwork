          package com.FCI.SWE.Models;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

//import com.google.appengine.repackaged.com.google.api.services.datastore.DatastoreV1.DatastoreService;
//import com.google.appengine.repackaged.com.google.api.services.datastore.DatastoreV1.DatastoreService;
import java.util.List;

public class SendMessage {
	private String sender;
	private String receiver;
	private String text;
	private String status;
	public SendMessage(String email1, String email2,String text,String status) {
		this.sender=email1;
		this.receiver=email2;
		this.text=text;
		this.status=status;
	}
	
	
	public SendMessage() {
		// TODO Auto-generated constructor stub
	}


	public String getEmail1() {
		return sender;
	}public String getEmail2() {
		return receiver;
	}

public void setEmail1(String email1) {
		this.sender = email1;
	}public void setEmail2(String email2) {
		this.receiver = email2;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
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

		request.setProperty("email1", this.sender);
		request.setProperty("email2", this.receiver);
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
public Boolean saveMessage() {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("message");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

	Entity employee = new Entity("message", list.size() + 1);

	employee.setProperty("text", this.text);
	employee.setProperty("email1", this.sender);
	employee.setProperty("email2", this.receiver);
	datastore.put(employee);

	return true;

}

}

