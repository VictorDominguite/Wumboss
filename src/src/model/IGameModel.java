package src.model;

public interface IGameModel {
	/* Inicializacao */
		public void montarCaverna();
		//public void setControl(Controller c);
	
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
