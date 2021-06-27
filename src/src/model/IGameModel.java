package src.model;

import src.controller.IController;
import src.utils.observer.Observer;

public interface IGameModel {
	/* Inicializacao */
		public void start();
		public void setControl(IController c);
	
	/* Interacao com os outros componentes */
		/* Observer pattern */
		public void subToLocal(int x, int y, Observer e);
		public void subToHeroi(String info, Observer e);
		public void subToItem(String item, Observer e);
		
		/* Requerir informacoes */
		
		/* Retorna o estado da caverna em dada posição, na seguinte forma
		 * String[3] = {nome do background, nome do foreground, estado}
		 * */
		public String[] getCaveState(int x, int y);
		
		/* Retorna o estado do herói relevante ao View
		 * O retorno eh apenas um numero em formato de String
		 * na primeira posicao do array
		 * */
		public String[] getHeroState(String item);
		
		public String[] getPossibleItems();
		
		/* Retorna o item de nome dado, na seguinte forma
		 * String[3] = {descricao, estaColetado?, estaEquipado?}
		 * */
		public String[] getItemState(String itemName);
}
