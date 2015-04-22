package com.FCI.SWE.Models;

import java.util.List;
import java.util.Vector;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PostEntity {
	
	String owner;
	long owner_id;
	String content;
	String type;
	String timeline_id;
	int likes_number;
	int seen_number;
	String privacy;
	
	
	public PostEntity(String owner,long owner_id,String content,String type,String timeline_id,int likes_number,int seen_nember,String privacy){
		this.owner=owner;
		this.owner_id=owner_id;
		this.content=content;
		this.type=type;
		this.timeline_id=timeline_id;
		this.likes_number=likes_number;
		this.seen_number=seen_nember;
		this.privacy=privacy;
	}
	
	public PostEntity() {
		// TODO Auto-generated constructor stub
	}

	public void setOwner(String owner){
		this.owner=owner;
	}
	public void setOwner_id(long owner_id){
		this.owner_id=owner_id;
	}
	public void setContent(String content){
		this.content=content;
	}
	public void setType(String type){
		this.type=type;
	}
	public void setTimeline_id(String timeline_id){
		this.timeline_id=timeline_id;
	}
	public void setLikes_number(int likes_number){
		this.likes_number=likes_number;
	}
	public void setSeen_number(int seen_number){
		this.seen_number=seen_number;
	}
	public void setPrivacy(String privacy){
		this.privacy=privacy;
	}
	public String getOwner(){
		return owner;
	}
	public long getOwner_id(){
		return owner_id;
	}
	public String getContent(){
		return content;
	}
	public String getType(){
		return type;
	}
	public String getTimeline_id(){
		return timeline_id;
	}
	public int likes_number(){
		return likes_number;
	}
	public int seen_number(){
		return seen_number;
	}
	public String getPrivacy(){
		return privacy;
	}
	
	public Boolean saveRequest() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Posts", list.size() + 1);

		employee.setProperty("owner", this.owner);
		employee.setProperty("owner_id", this.owner_id);
		employee.setProperty("content", this.content);
		employee.setProperty("type", this.type);
		employee.setProperty("timeline_id", this.timeline_id);
		employee.setProperty("likes_number", this.likes_number);
		employee.setProperty("seen_number", this.seen_number);
		employee.setProperty("privacy", this.privacy);
		datastore.put(employee);

		return true;

	}
	
	

	public Vector<String> getPosts(String timeline_id){
		 
		Vector<String>post=new Vector<String>();
		String temp = "";
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService(); 
		Query gaeQuery = new Query("Posts");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("timeline_id").equals(timeline_id))
			{   
				temp=entity.getProperty("owner").toString() + "\n" + entity.getProperty("content").toString();
				post.add(temp);
			}
		}
	
		return post;
	}
	
	
	
	
	
	

}
