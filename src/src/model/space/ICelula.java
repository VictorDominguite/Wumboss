package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.utils.observer.Observer;

public interface ICelula {
	public IEntidadeEstatica getBackground();
	public void setBackground(IEntidadeEstatica e);
	
	public void pushEntidade(IEntidadeDinamica ent);
	public IEntidadeDinamica popEntidade();
	public IEntidadeDinamica peekEntidade();
	
	public boolean isDescoberta();
	public void descobrir();
	public boolean isVisivel();
	public void setVisivel(boolean visivel);
	public void inativar();
	
	public int distanciaAte(int x, int y);
	
	public void subscribe(Observer o);
	public String[] estado();
}
