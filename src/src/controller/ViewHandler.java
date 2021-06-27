package src.controller;

import java.awt.event.ActionEvent;

public class ViewHandler extends CallbackHandler{
	private static final long serialVersionUID = -6348549496574860460L;

	public ViewHandler(Controller control) {
		super(control);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] action = e.getActionCommand().split(" ");
		if(action[0].equalsIgnoreCase("inventory"))
			sendMessage("inventory " + "equip " + action[1]);
		else if(action[0].equalsIgnoreCase("attack"))
			sendMessage("hero " + "attack " + action[1]);
	}

}
