package src.model;

import src.controller.IController;
import src.model.actions.IActionParser;
import src.model.actions.ModelAction;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.itens.IItem;
import src.model.space.ICave;
import src.model.space.factories.CaveFactory;
import src.model.space.factories.SalaFactory;
import src.utils.events.EventListener;
import src.utils.exceptions.SemReferenciaAComponente;

public class GameModel implements IGameModel{
	private IController io;
	private IActionParser modelAction;
	
	private ICave cave;
	private Heroi hero;
	private IInventario inv;
	
	public void montarCaverna() {
		SalaFactory.setIO(io);
		
		cave = CaveFactory.montar(this);
	}
	
	public void setHeroi(Heroi h) {
		this.hero = h;
		
		if(modelAction != null)
			modelAction.connect("hero", h);
	}
	
	public void setInventario(IInventario inv) {
		this.inv = inv;
	}

	public void setControl(IController io) {
		this.io = io;
		
		if(modelAction == null)
			modelAction = new ModelAction();
		
		this.io.connect("parser", modelAction);
	}
	
	public void subToLocal(int x, int y, EventListener e) {
		cave.subToLocal(x, y, e);
	}
	
	public void subToHeroi(EventListener e) {
		
	}
	
	public void subToItem(String item, EventListener e) {
		inv.subToItem(item, e);
	}

	public String[] getCaveState(int x, int y) {
		isCaveAvailable();
		
		return cave.estado(x, y);
	}

	public String[][] getHeroState() {
		isHeroAvailable();
		
		return null;
	}
	
	public String[] getPossibleItems() {
		isInventoryAvailable();
		
		String[] res = new String[inv.getTamanho()];
		
		IItem[] items = inv.getItems();
		for(int i = 0; i < inv.getTamanho(); i++) 
			res[i] = items[i] != null ? items[i].toString() : "null";
		
		return res;
	}

	public String[] getItemState(String itemName) {
		isInventoryAvailable();
		
		IItem item = inv.getItem(itemName);
		if(item == null)
			return null;

		String[] res = new String[3];
		res[0] = item.getDescricao();
		res[1] = item.isColetado() ? "true" : "false";
		res[2] = item.isEquipado() ? "true" : "false";
		
		return res;
	}
	
	private boolean isCaveAvailable() throws SemReferenciaAComponente {
		if(cave == null) 
			throw new SemReferenciaAComponente("Caverna", "GameModel");
		
		return true;
	}
	
	private boolean isHeroAvailable() throws SemReferenciaAComponente {
		if(hero == null) 
			throw new SemReferenciaAComponente("Heroi", "GameModel");
		
		return true;
	}
	
	private boolean isInventoryAvailable() throws SemReferenciaAComponente {
		if(inv == null) 
			throw new SemReferenciaAComponente("Inventario", "GameModel");
		
		return true;
	}

}
