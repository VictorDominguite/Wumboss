package src.model;

import src.controller.IController;
import src.model.entidade.dinamica.Heroi;
import src.model.factories.CaveFactory;
import src.model.factories.SalaFactory;

public class GameModel implements IGameModel{
	private IController io;
	
	private Caverna cave;
	private Heroi hero;
	private Inventario inv;
	
	public void montarCaverna() {
		CaveFactory.setIO(io);
		SalaFactory.setIO(io);
		
		CaveFactory.montar();
	}

	public void setControl(IController io) {
		this.io = io;
	}

	/* Retorna o estado da caverna relevante ao View
	 * Isso inclui a sala em que o herói está, e
	 * possivelmente as salas vizinhas. 
	 * */
	public String[][] getCaveState() {
		if(cave == null)
			System.err.println("A caverna não foi criada ainda!");
		
		return null;
	}

	/* Retorna o estado do herói relevante ao View
	 * Isso inclui a vida atual dele, seu poder de
	 * ataque e defesa, possivelmente seu nome, etc 
	 * */
	public String[][] getHeroState() {
		if(hero == null)
			System.err.println("O herói não existe ainda!");
		
		return null;
	}

	/* Retorna o inventario do heroi relevante ao View
	 * Isso inclui os itens coletados, seus nomes, 
	 * descricoes, se estao equipados, etc 
	 * */
	public String[][] getInventoryState() {
		if(inv == null)
			System.err.println("O inventário não existe ainda!");
		
		return null;
	}


}
