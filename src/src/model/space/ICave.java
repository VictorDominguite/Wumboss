package src.model.space;

import src.utils.observer.Observer;

public interface ICave extends ICaveAction, ICaveProperties{
	public void subToLocal(int x, int y, Observer e);
}
