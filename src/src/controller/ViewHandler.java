package src.controller;

import java.awt.event.ActionEvent;

public class ViewHandler extends CallbackHandler{
	private static final long serialVersionUID = -6348549496574860460L;

	public ViewHandler(Controller control) {
		super(control);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sendMessage("inventory " + "equip " + e.getActionCommand());
	}

}
