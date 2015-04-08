package com.FCI.SWE.Controller;
import java.util.Observable;
import java.util.Observer;

//import com.FCI.SWE.Models.User;
//import com.FCI.SWE.ServicesModels.Mesageentity;
import com.FCI.SWE.Models.MessageEntity;

public class MessageObserver implements Observer{
	private long ID;
	Conversation conv;
	public MessageObserver (long ID)
	{
		this.ID=ID;
	}
	public void setConv(Conversation conv)
	{
		this.conv = conv;
		conv.attach(this);
	}

	public void Update(long SendId) {
		String message = conv.getUpdate();
	//	long sendID = User.getCurrentActiveUser().getId();
		MessageEntity msg = new MessageEntity(message,SendId,ID);
		msg.saveMessage();
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}


