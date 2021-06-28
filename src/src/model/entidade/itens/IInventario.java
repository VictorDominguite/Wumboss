package src.model.entidade.itens;

import src.utils.actions.IActionExecutor;
import src.utils.observer.Observer;

public interface IInventario extends IActionExecutor, Observer{
	public void subToItem(String item, Observer e);
	
	public IItemAtaque getArmaEquipada();
	public int getArmaduraEquipada();
	
	public int getTamanho();
	public IItem getItem(String name);
	
	public String[] getCumulativeNames();
	public String[] getCollectableNames();
}
