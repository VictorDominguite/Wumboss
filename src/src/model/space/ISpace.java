package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IHeroi;
import src.utils.Direcao;
import src.utils.observer.Observer;

public interface ISpace {
	public void connect(IHeroi hero);
	
	public void subToLocal(int x, int y, Observer e);

	public ICelula getCelula(int x, int y);
	
	public void moverEntidade(int x, int y, Direcao dir);
	public void addEntidade(int x, int y, IEntidadeDinamica e);
    public IEntidadeDinamica removerEntidade(int x, int y);

	public void atualizarVisaoEInimigos();
    public String[] estadoAtual(int x, int y);
}
