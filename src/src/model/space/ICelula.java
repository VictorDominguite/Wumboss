package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.utils.observer.Observer;

public interface ICelula extends Comparable<ICelula>{
	public IEntidadeEstatica getBackground();
	public void setBackground(IEntidadeEstatica e);
	
	public void pushEntidade(IEntidadeDinamica ent);
	public IEntidadeDinamica popEntidade();
	public IEntidadeDinamica peekEntidade();
	
	public int getNivelLuz();
	public void setNivelLuz(int luz);
	public boolean isVisivel();
	public void inativar();
	public void destroy();
	
	public void refresh();
	
	public int getPosX();
	public int getPosY();
	
	public void subscribe(Observer o);
	public String[] estado();
}
