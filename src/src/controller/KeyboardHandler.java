package src.controller;

import java.awt.event.ActionEvent;

public class KeyboardHandler extends CallbackHandler{
	private static final long serialVersionUID = 6217557834269358091L;
	
	public static final Character[] keysToListen = {'w', 'a', 's', 'd'};
	
	public KeyboardHandler(Controller control) {
		super(control);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case("w"):
			sendMessage("hero move up");
			break;
		case("a"):
			sendMessage("hero move left");
			break;
		case("s"):
			sendMessage("hero move down");
			break;
		case("d"):
			sendMessage("hero move right");
			break;
		default:
		}
	}

}
