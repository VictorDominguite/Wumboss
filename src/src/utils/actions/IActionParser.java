package src.utils.actions;

public interface IActionParser{
	public void connect(String name, IActionExecutor agent);
	public void disconnectFromAll();
	public void sendMessage(String action, String... args);
}
