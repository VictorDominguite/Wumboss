package src.model;

import src.controller.IController;

public interface IGameModel {
	/* Inicializacao */
		public void montarCaverna();
		public void setControl(IController c);
	
	/* Interacao com os outros componentes */
		/* Observer pattern */
		//public void subToCaverna(Observer o);
		//public void subToHeroi(Observer o);
		//public void subToInventario(Observer o);
		
		/* Requerir informacoes */
		public String[][] getCaveState();
		public String[][] getHeroState();
		public String[][] getInventoryState();
}
