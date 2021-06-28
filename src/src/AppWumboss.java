package src;

import src.controller.Controller;
import src.controller.IController;
import src.model.GameModel;
import src.model.IGameModel;
import src.view.GameView;
import src.view.IGameView;

public class AppWumboss {
	private static IController controller;
	private static IGameModel model;
	private static IGameView view;
	
	public static void main(String[] args) {
		controller = new Controller("data/", "assets/");
		model = GameModel.getInstance();
		view = new GameView();
		
		view.setControl(controller);
		connectModel(model, false);
		view.montarView();
		view.showView();
	}
	
	public static void connectModel(IGameModel g, boolean rebuilding) {
		model = g;
		view.setModel(g);
		g.setControl(controller);
		
		g.start();
		if(rebuilding)
			view.rebuild();
	}
}
