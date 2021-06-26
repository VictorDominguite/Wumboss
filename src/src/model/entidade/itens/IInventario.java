package src.model.entidade.itens;

import src.utils.observer.Observer;

public interface IInventario {
	public void subToItem(String item, Observer e);
	
	public IItemAtaque getArmaEquipada();
	public int getArmaduraEquipada();
	
	public int getTamanho();
	public IItem getItem(String name);
	//talvez nao seja uma boa ideia, mas vou deixar assim por enquanto
	//(talvez um Iterator seja melhor)
	public IItem[] getItems();
}
