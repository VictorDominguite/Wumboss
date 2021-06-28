package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.utils.observer.Observer;

public interface ISala {
	public int getTamX();
	public int getTamY();
	public int getID();
	public String[] estado(int x, int y);
	
	public void inativar();
	
	public void addEntidade(int x, int y, IEntidadeDinamica e);
	public IEntidadeDinamica removeEntidade(int x, int y);
	
	public boolean outOfBounds(int x, int y);
	public ICelula getCelula(int x, int y);
	
	public void subToLocal(int x, int y, Observer e);
	public void destroy();

}
