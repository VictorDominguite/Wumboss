package src.model.entidade.itens;

import src.utils.observer.IEventCreator;

public interface IItem extends IEventCreator{
	public boolean isColetado();
	public boolean isEquipado();
	
	public String getNome();
	public String getDescricao();
}
