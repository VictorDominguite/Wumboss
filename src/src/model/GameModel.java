package src.model;

import src.controller.IController;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.itens.IInventario;
import src.model.entidade.itens.IItem;
import src.model.space.ISpace;
import src.model.space.Space;
import src.model.space.factories.SalaFactory;
import src.utils.actions.IActionParser;
import src.utils.exceptions.SemReferenciaAComponente;
import src.utils.observer.Observer;

public class GameModel implements IGameModel{
	private IController io;
	private IActionParser modelAction;
	
	private ISpace space;
	private IHeroi hero;
	private IInventario inv;
	
	public void start() {
		montarCaverna();
		
		setHeroi(new Heroi());
		space.addEntidade(1, 1, hero);
		
		setInventario(hero.getInventario());
		space.atualizarVisaoEInimigos();
	}
	
	public void montarCaverna() {
		SalaFactory.setIO(io);
		space = Space.getInstance();
	}
	
	public void setHeroi(Heroi h) {
		this.hero = h;
		
		if(modelAction != null)
			modelAction.connect("hero", h);
		if(space != null) {
			hero.connect(space);
			space.connect(hero);
		}
	}
	
	public void setInventario(IInventario inv) {
		this.inv = inv;
		
		if(modelAction != null)
			modelAction.connect("inventory", inv);
	}

	public void setControl(IController io) {
		this.io = io;
		
		if(modelAction == null)
			modelAction = new ModelAction();
		
		this.io.connect(modelAction);
	}
	
	public void subToLocal(int x, int y, Observer e) {
		space.subToLocal(x, y, e);
	}
	
	public void subToHeroi(String info, Observer e) {
		hero.subscribe(e);
	}
	
	public void subToItem(String item, Observer e) {
		inv.subToItem(item, e);
	}

	public String[] getCaveState(int x, int y) {
		isSpaceAvailable();
		
		return space.estadoAtual(x, y);
	}

	public String[] getHeroState(String item) {
		isHeroAvailable();
		
		String[] res = new String[1];
		
		if(item.equalsIgnoreCase("vida"))
			res[0] = "" + hero.getVida();
		else if(item.equalsIgnoreCase("defense"))
			res[0] = "" + hero.getDefense();
		else if(item.equalsIgnoreCase("attack"))
			res[0] = "" + hero.getAttackDamage();
		
		return res;
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
	
	private boolean isSpaceAvailable() throws SemReferenciaAComponente {
		if(space == null) 
			throw new SemReferenciaAComponente("Space", "GameModel");
		
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
