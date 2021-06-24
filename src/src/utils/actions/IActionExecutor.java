package src.utils.actions;

public interface IActionExecutor{
	/* Send a message to this object */
    public void sendMessage(String action, String... args);
}
