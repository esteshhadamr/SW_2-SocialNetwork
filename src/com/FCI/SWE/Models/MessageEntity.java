package com.FCI.SWE.Models;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class MessageEntity {
	private String message;
	private long sendID;
	private long recID;
	
	public MessageEntity(String messsage,long sendID,long recID)
	{
		this.message = messsage;
		this.sendID = sendID;
		this.recID = recID;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public void setSendID(long sendID)
	{
		this.sendID = sendID;
	}
	
	public void setRecID(long recID)
	{
		this.recID = recID;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public long getSendID()
	{
		return sendID;
	}
	
	public long getRecID()
	{
		return recID;
	}
	
	public Boolean saveMessage() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Conversation ");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Conversation ", list.size() + 1);

		employee.setProperty("sendID", this.sendID);
		employee.setProperty("recID", this.recID);
		employee.setProperty("message", this.message);
		datastore.put(employee);

		return true;

	}

}
