package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.IEntidadeEstatica;

public interface ICelula {
	public IEntidadeEstatica getBackground();
	
	public void pushEntidade(IEntidadeDinamica ent);
	public IEntidadeDinamica popEntidade();
	public IEntidadeDinamica peekEntidade();
	
	public boolean isVisivel();
	public void setVisivel(boolean visivel);
	
	public int distanciaAte(int x, int y);
	
	public String[] estado();
}
