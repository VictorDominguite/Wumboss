package src.utils.events;

import java.util.ArrayList;

public abstract class EventCreator implements IEventCreator{
	protected ArrayList<EventListener> listeners;
	
	protected EventCreator() {
		listeners = new ArrayList<EventListener>();
	}
	
	protected void onUpdate() {
		onUpdate(false);
	}
	
	protected void onUpdate(boolean reinscrever) {
		for(EventListener e : listeners) {
			e.onUpdate(reinscrever);
			if(reinscrever) 
				listeners.remove(e);
		}
	}
	
	public void subscribe(EventListener e) {
		listeners.add(e);
	}
	
}
