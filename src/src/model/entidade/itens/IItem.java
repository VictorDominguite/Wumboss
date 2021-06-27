package src.model.entidade.itens;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.utils.observer.IEventCreator;

public interface IItem extends IEventCreator, IEntidadeDinamica {
	public boolean isColetado();
	public boolean isEquipado();
	
	public String getNome();
	public String getDescricao();
	
	public void equipar();
	public void desequipar();

	public void coletar();
}
