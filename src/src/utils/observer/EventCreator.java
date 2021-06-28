package src.utils.observer;

import java.util.ArrayList;

public abstract class EventCreator implements IEventCreator{
	protected ArrayList<Observer> listeners;
	
	protected EventCreator() {
		listeners = new ArrayList<Observer>();
	}
	
	public void subscribe(Observer e) {
		listeners.add(e);
	}
	
	public void disconnectAll() {
		listeners.clear();
	}
	
	protected void onUpdate() {
		onUpdate(false);
	}
	
	protected void onUpdate(boolean reinscrever) {
		for(Observer e : listeners) 
			e.onUpdate(reinscrever);
		
		if(reinscrever)
			listeners.clear();
	}
	
}
