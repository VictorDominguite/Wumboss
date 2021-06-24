package src.utils.observer;

public interface Observer {
	public void onUpdate();
	public void onUpdate(boolean reinscrever);
	public String[] getInfo();
}
