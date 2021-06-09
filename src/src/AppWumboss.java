package src;

import src.controller.Controller;
import src.controller.IController;
import src.model.GameModel;
import src.model.IGameModel;
import src.view.MainView;

public class AppWumboss {
	public static void main(String[] args) {
		//Start controller
		IController controller = new Controller("data/", "assets/");
		
		//Start model
		IGameModel gm = new GameModel();
		gm.setControl(controller);
		gm.montarCaverna();
		
		//Start view
		MainView mv = new MainView();
		
	}
}
