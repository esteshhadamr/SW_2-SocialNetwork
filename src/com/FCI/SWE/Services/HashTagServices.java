package com.FCI.SWE.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.FCI.SWE.Models.HashtagEntity;
import com.FCI.SWE.Models.PostEntity;





@Path("/")
@Produces(MediaType.TEXT_PLAIN)

public class HashTagServices {
	
	@POST
	@Path("/viewTimeline")
	public String viewTimeline(@FormParam("hashTagName") String hashTagName) {
		PostEntity post  = new PostEntity();
		Vector<String> posts = post.getPosts(hashTagName);
		
		JSONArray array = new JSONArray();
		//if(posts == null)
			//json.put("Status", "Failed");
		//else
		//{
			//json.put("Status", "OK");
			for(int i=0 ; i<posts.size() ; i++)
			{
				JSONObject json = new JSONObject();
				json.put("post", posts.get(i));
				array.add(json);
			}
		//}
			
		return array.toJSONString();
	}
	
	@POST
	@Path("/viewTopTrends")
	public String viewTopTrends() {
		HashtagEntity hashTag  = new HashtagEntity();
		List<HashtagEntity> TopTrends = hashTag.getTopTrends();
		
		JSONArray array = new JSONArray();
		//if(posts == null)
			//json.put("Status", "Failed");
		//else
		//{
			//json.put("Status", "OK");
			for(int i=0 ; i<TopTrends.size() ; i++)
			{
				JSONObject json = new JSONObject();
				json.put("name", TopTrends.get(i).getName());
				array.add(json);
			}
		//}
			
		return array.toJSONString();
	}
	

}
