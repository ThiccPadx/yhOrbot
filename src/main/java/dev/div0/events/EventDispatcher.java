package dev.div0.events;

import java.util.ArrayList;


public class EventDispatcher {
	
	private static EventDispatcher instance;
	private ArrayList<IEventListener> subscribers = new ArrayList<IEventListener>();
	
	private EventDispatcher(){
	}
	
	public static EventDispatcher getInstance(){
		if(instance == null){
			instance = new EventDispatcher();
		}
		return instance;
	}
	
	public void addEventListener(IEventListener subscriber){
		subscribers.add(subscriber);
	}
	public void removeEventListener(IEventListener subscriber){
		subscribers.remove(subscriber);
	}
	public void dispatchEvent(BaseEvent event)
	{
		for (IEventListener observer : subscribers)
        {
            observer.eventHandler(event);
        }
	}
}
