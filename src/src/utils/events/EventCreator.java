package src.utils.events;

import java.util.ArrayList;

public abstract class EventCreator implements IEventCreator{
	protected ArrayList<EventListener> listeners;
	
	protected EventCreator() {
		listeners = new ArrayList<EventListener>();
	}
	
	public void onUpdate() {
		for(EventListener e : listeners) 
			e.onUpdate();
	}
	
	public void subscribe(EventListener e) {
		listeners.add(e);
	}
	
}
