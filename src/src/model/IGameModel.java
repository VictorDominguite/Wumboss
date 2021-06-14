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
		
		/* Retorna o estado da caverna relevante ao View
		 * Isso inclui a sala em que o herói está, e
		 * possivelmente as salas vizinhas. 
		 * */
		public String[][] getCaveState();
		
		/* Retorna o estado do herói relevante ao View
		 * Isso inclui a vida atual dele, seu poder de
		 * ataque e defesa, possivelmente seu nome, etc 
		 * */
		public String[][] getHeroState();
		
		public String[] getPossibleItems();
		
		/* Retorna o item de nome dado, na seguinte forma
		 * String[3] = {descricao, estaColetado?, estaEquipado?}
		 * */
		public String[][] getInventoryState(String itemName);
}
