package src.view;

import src.controller.IController;
import src.model.IGameModel;
import src.view.panels.InfoPanel;

public interface IGameView {
	public void setControl(IController c);
	public void setModel(IGameModel g);
	
	public void montarView();
	
	public static void setFeedMessage(String message) {
		InfoPanel.setFeed(message);
	}
}
