package src.model.space;

import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.utils.Direcao;

public interface ICelula {
	public IEntidadeDinamica getForeground();
	public IEntidade getBackground();
	public Sala getSala();
	public int getPosX();
	public int getPosY();
	
	public void moverEntidade(Direcao dir);
	public void addEntidade(IEntidadeDinamica ent);
	public IEntidadeDinamica removerEntidade();
	
	public boolean isVisivel();
	public void setVisivel(boolean visivel);
	
	public boolean ehPassagem();
	
	public String[] estado();
}
