package src.model;

import src.controller.IController;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.itens.Item;
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
		
		cave = CaveFactory.montar(this);
	}
	
	public void setHeroi(Heroi h) {
		this.hero = h;
	}
	
	public void setInventario(Inventario inv) {
		this.inv = inv;
	}

	public void setControl(IController io) {
		this.io = io;
	}

	public String[][] getCaveState() {
		if(cave == null)
			System.err.println("A caverna não foi criada ainda!");
		
		return null;
	}

	public String[][] getHeroState() {
		if(hero == null)
			System.err.println("O herói não existe ainda!");
		
		return null;
	}
	
	public String[] getPossibleItems() {
		//o inventario _deveria_ ter todos os items
		if(inv == null) {
			System.err.println("O inventário não existe ainda!");
			return null;
		}
		
		String[] res = new String[inv.getTamanho()];
		
		Item[] items = inv.getItems();
		for(int i = 0; i < inv.getTamanho(); i++) {
			res[i] = items[i] != null ? items[i].toString() : "null";
		}
		
		return res;
	}

	public String[][] getInventoryState(String itemName) {
		if(inv == null)
			System.err.println("O inventário não existe ainda!");
		
		return null;
	}


}
