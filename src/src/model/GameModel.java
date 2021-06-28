package src.model;

import src.AppWumboss;
import src.controller.IController;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.itens.IInventario;
import src.model.entidade.itens.IItem;
import src.model.entidade.itens.IItemAtaque;
import src.model.space.ISpace;
import src.model.space.Space;
import src.model.space.factories.SalaFactory;
import src.utils.actions.IActionParser;
import src.utils.exceptions.SemReferenciaAComponente;
import src.utils.observer.Observer;

public class GameModel implements IGameModel{
	private static GameModel instance;
	private IController io;
	private IActionParser modelAction;
	
	private ISpace space;
	private IHeroi hero;
	private IInventario inv;
	
	public static GameModel getInstance() {
    	if (instance == null) {
    		System.out.println("hi");
    		instance = new GameModel();
    	}
    	
    	return instance;
    }
	
	public static void rebuild() {
		instance.rebuildInstance();
	}
	
	public void rebuildInstance() {
		modelAction.disconnectFromAll();
		
		space.disconnectHero();
		space.destroy();
		hero.destroy();
		
		space = null;
		hero = null;
		inv = null;
		
		io.disconnect();
		
		GameModel.instance = null;
		AppWumboss.connectModel(GameModel.getInstance(), true);
	}
	
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
		
		if(modelAction != null) {
			modelAction.connect("space", space);
		}
	}
	
	public void setHeroi(Heroi h) {
		this.hero = h;
		
		if(modelAction != null)
			modelAction.connect("hero", h);
		if(space != null) {
			hero.connect(space);
			space.connectHero(hero);
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
		
		if(item.equalsIgnoreCase("state")) {
			String[] res = new String[3];
			IItemAtaque arma = hero.getInventario().getArmaEquipada();
			res[0] = arma != null ? arma.getNome() : "null";
			res[1] = hero.getInventario().getItem("armadura").isEquipado() ? "armadura" : "null";
			res[2] = hero.getInventario().getItem("capacete").isEquipado() ? "capacete" : "null";
			return res;
		}
		
		String[] res = new String[1];
		if(item.equalsIgnoreCase("vida"))
			res[0] = "" + hero.getVida();
		else if(item.equalsIgnoreCase("defense"))
			res[0] = "" + hero.getDefense();
		else if(item.equalsIgnoreCase("attack"))
			res[0] = "" + hero.getAttackDamage();
		
		return res;
	}
	
	public String[] getPossibleCollectableItems() {
		isInventoryAvailable();
		return inv.getCollectableNames();
	}
	
	public String[] getPossibleCumulativeItems() {
		isInventoryAvailable();
		return inv.getCumulativeNames();
	}

	public String[] getItemState(String itemName) {
		isInventoryAvailable();
		
		IItem item = inv.getItem(itemName);
		if(item == null)
			return null;
		
		String[] res = new String[3];
		res[0] = item.getDescricao();
		res[1] = item.estadoColetado();
		res[2] = item.estadoEquipado();
		
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
