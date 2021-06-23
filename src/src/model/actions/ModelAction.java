package src.model.actions;

public class ModelAction implements IActionParser {
    private String action;
    private IActionExecutor actor;

	public void connect(IActionExecutor actor) {
		this.actor = actor;
	}

	public void sendMessage(String message) {
		if(actor != null)
			actor.receiveMessage(message);
	}

	public void receiveMessage(String message) {
		this.action = message;
	}
}
