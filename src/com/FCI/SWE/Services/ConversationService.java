package com.FCI.SWE.Services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.FCI.SWE.Controller.Conversation;
import com.FCI.SWE.Controller.MessageObserver;
import com.FCI.SWE.Models.UserEntity;
//import com.FCI.SWE.ServicesModels.RequestEntity;
//import com.FCI.SWE.ServicesModels.UserEntity;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class ConversationService {
	@POST
	@Path("/ConversationService")
	public String conversation(@FormParam("sender") String Sender,@FormParam("recName1") String rec_name1,@FormParam("recName2") String rec_name2
	,@FormParam("recName3") String rec_name3,@FormParam("Mesg") String Message) {
		
        JSONObject json = new JSONObject();
       
        UserEntity userEntity = UserEntity.getUser(Sender);
        UserEntity userEntity1 = UserEntity.getUser(rec_name1);
        UserEntity userEntity2 = UserEntity.getUser(rec_name2);
        UserEntity userEntity3 = UserEntity.getUser(rec_name3);
        
        if (  userEntity==null&&(userEntity1==null||userEntity2==null||userEntity3==null)) {
			json.put("Status", "Failed");
		}
		else {
			json.put("Status", "OK");
			MessageObserver obj1= new MessageObserver(userEntity1.getId());
			MessageObserver obj2= new MessageObserver(userEntity2.getId());
			MessageObserver obj3= new MessageObserver(userEntity3.getId());
			Conversation con=new Conversation(userEntity.getId());
			obj1.setConv(con);
			obj2.setConv(con);
			obj3.setConv(con);
			con.setState(Message);
			
			
		}
		
			return json.toString();
	
	
	}
		

}
