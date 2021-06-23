package src.model.actions;

public interface IActionExecutor extends IActionAgent{
	/* Send a message to this object */
    public void sendMessage(String action, String... args);
}
