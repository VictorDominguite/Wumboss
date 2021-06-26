package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.utils.Direcao;

public interface ICelula {
	public IEntidadeDinamica getEntidade();
	public IEntidadeEstatica getBackground();
	public Sala getSala();
	public int getPosX();
	public int getPosY();
	
	public void moverEntidade(Direcao dir);
	public void addEntidade(IEntidadeDinamica ent);
	public IEntidadeDinamica removerEntidade();
	
	public boolean isVisivel();
	public void setVisivel(boolean visivel);
	
	public int distanciaAte(int x, int y);
	
	public boolean ehPassagem();
	
	public String[] estado();
}
