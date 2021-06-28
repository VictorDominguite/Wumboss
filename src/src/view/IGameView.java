package src.view;

import java.awt.Color;

import src.controller.IController;
import src.model.IGameModel;
import src.view.panels.InfoPanel;

public interface IGameView {
	public void setControl(IController c);
	public void setModel(IGameModel g);
	
	public void montarView();
	public void showView();
	
	public void rebuild();
	
	public static void setFeedMessage(String message) {
		InfoPanel.setFeed(message);
	}
	
	public static void setFeedMessage(String message, Color c) {
		InfoPanel.setFeed(message, c);
	}
}
