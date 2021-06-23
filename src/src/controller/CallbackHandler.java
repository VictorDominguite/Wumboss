package src.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public abstract class CallbackHandler extends AbstractAction{
	private static final long serialVersionUID = -5234752745524153566L;
	
	private Controller control;
	
	public CallbackHandler(Controller control) {
		this.control = control;
	}
	
	protected void sendMessage(String callback) {
		control.sendMessage(callback);
	}
	
	public void actionPerformed(ActionEvent e) {}
}
