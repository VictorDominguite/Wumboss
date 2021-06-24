package src.utils.events;

public interface EventListener {
	public void onUpdate();
	public void onUpdate(boolean reinscrever);
	public String[] getInfo();
}
