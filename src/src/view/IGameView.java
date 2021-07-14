package src.view;

import src.controller.IController;
import src.model.IGameModel;
import src.utils.Priority;
import src.view.panels.InfoPanel;

public interface IGameView {
	public void setControl(IController c);
	public void setModel(IGameModel g);
	
	public void montarView();
	public void showView();
	
	public void rebuild();
	
	public static void setFeedMessage(String message) {
		InfoPanel.addToFeed(message);
	}
	
	public static void setFeedMessage(String message, Priority p) {
		InfoPanel.addToFeed(message, p);
	}
	
	public static void updateFeed(Priority minimum) {
		InfoPanel.updateFeed(minimum);
	}
}
