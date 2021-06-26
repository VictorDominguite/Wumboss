package src;

import src.controller.Controller;
import src.controller.IController;
import src.model.GameModel;
import src.model.IGameModel;
import src.view.GameView;
import src.view.IGameView;

public class AppWumboss {
	public static void main(String[] args) {
		IController controller = new Controller("data/", "assets/");
		IGameModel gm = new GameModel();
		IGameView mv = new GameView();
		
		mv.setControl(controller);
		mv.setModel(gm);
		gm.setControl(controller);
		
		gm.start();
		mv.montarView();
	}
}
