package com.FCI.SWE.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Conversation extends Observable{
	private String message;
	private List<MessageObserver> observers;
    private long SendId;
	
	public Conversation(long SendId)
	{
		this.observers = new ArrayList<MessageObserver>();
		this.SendId=SendId;
	}
	public void setState(String message)
	{
		this.message = message;
		notifyAllObs();
	}
	
	void notifyAllObs()
	{
		for(MessageObserver obs : observers)
		{
			obs.Update( SendId );
		}
	}
	public String getUpdate()
	{
		return message;
	}
	public void attach(MessageObserver o)
	{
		observers.add(o);
	}
	public void deAttach(MessageObserver o)
	{
		observers.remove(o);
	}
	

}

