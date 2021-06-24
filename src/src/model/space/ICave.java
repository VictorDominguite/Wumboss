package src.model.space;

import src.utils.events.EventListener;

public interface ICave {
	public String[] estado(int x, int y);
	public void subToLocal(int x, int y, EventListener e);
}
