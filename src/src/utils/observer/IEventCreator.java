package src.utils.observer;

public interface IEventCreator {
	public void subscribe(Observer e);
	public void disconnectAll();
}
