package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IHeroi;
import src.utils.Direcao;
import src.utils.actions.IActionExecutor;
import src.utils.observer.Observer;

public interface ISpace extends IActionExecutor{
	public void connectHero(IHeroi hero);
	public void disconnectHero();
	public void destroy();
	
	public void subToLocal(int x, int y, Observer e);
	
	public boolean moverEntidade(int x, int y, Direcao dir);
	public void addEntidade(int x, int y, IEntidadeDinamica e);
    public IEntidadeDinamica removerEntidade(int x, int y);
    
    public int distanciaAte(int xIni, int yIni, int xFim, int yFim);

	public void atualizarVisaoEInimigos();
    public String[] estadoAtual(int x, int y);
    public void refreshLocal(int x, int y);
}
