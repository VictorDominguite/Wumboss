package src.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class KeyboardHandler extends CallbackHandler{
	private static final long serialVersionUID = 6217557834269358091L;
	
	public static final Character[] keysToListen = {'w', 'W', 'a', 'A', 
													's', 'S', 'd', 'D', 
													KeyEvent.VK_ESCAPE,
													KeyEvent.VK_ENTER};
	
	public KeyboardHandler(Controller control) {
		super(control);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand().toLowerCase().charAt(0)) {
		case('w'):
			sendMessage("hero move up");
			break;
		case('a'):
			sendMessage("hero move left");
			break;
		case('s'):
			sendMessage("hero move down");
			break;
		case('d'):
			sendMessage("hero move right");
			break;
		case((char) KeyEvent.VK_ESCAPE):
			sendMessage("space destroy");
			break;
		case((char) KeyEvent.VK_ENTER):
			sendMessage("space rebuild");
			break;
		}
	}

}
