package src.model.actions;

import java.util.HashMap;

import src.utils.exceptions.MensagemInvalida;

public class ModelAction implements IActionParser {
    private HashMap<String, IActionExecutor> mappings;
    
    public ModelAction() {
    	mappings = new HashMap<String, IActionExecutor>();
    }

	public void connect(String name, IActionAgent actor) {
		if(!mappings.containsKey(name) && actor instanceof IActionExecutor)
			mappings.put(name, (IActionExecutor) actor);
	}
	
	private void parseMessage(String message) {
		String[] splitMessage = message.split(" ");
		
		IActionExecutor actor = mappings.get(splitMessage[0]);
		if(actor == null)
			throw new MensagemInvalida("Controller", "ModelAction", message);
		
		String action = splitMessage[1];
		String[] args = new String[splitMessage.length - 2];
		
		for(int i = 2; i < splitMessage.length - 2; i++)
			args[i-2] = splitMessage[i];
		
		sendAction(actor, action, args);
	}

	public void sendMessage(String action, String... args) {
		parseMessage(action);
	}

	private void sendAction(IActionExecutor actor, String action, String... args) {
		actor.sendMessage(action, args);
	}
}
