package src;

import src.model.GameModel;
import src.model.IGameModel;
import src.view.MainView;

public class AppWumboss {
	public static void main(String[] args) {
		//Start controller

		
		//Start model
		IGameModel gm = new GameModel();
		gm.montarCaverna();
		
		//Start view
		MainView mv = new MainView();
		
	}
}
