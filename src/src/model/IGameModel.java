package src.model;

import src.controller.IController;
import src.utils.events.EventListener;

public interface IGameModel {
	/* Inicializacao */
		public void montarCaverna();
		public void setControl(IController c);
	
	/* Interacao com os outros componentes */
		/* Observer pattern */
		public void subToLocal(int x, int y, EventListener e);
		public void subToHeroi(EventListener e);
		public void subToInventario(EventListener e);
		
		/* Requerir informacoes */
		
		/* Retorna o estado da caverna em dada posição, na seguinte forma
		 * String[3] = {nome do background, nome do foreground, estado}
		 * */
		public String[] getCaveState(int x, int y);
		
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
