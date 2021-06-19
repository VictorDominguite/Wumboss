package src.model;

import src.model.entidade.itens.IItem;
import src.utils.events.EventListener;

public interface IInventario {
	public void subToItem(String item, EventListener e);
	
	public IItem getArmaEquipada();
	public IItem getArmaduraEquipada();
	
	public int getTamanho();
	public IItem getItem(String name);
	//talvez nao seja uma boa ideia, mas vou deixar assim por enquanto
	//(talvez um Iterator seja melhor)
	public IItem[] getItems();
}
