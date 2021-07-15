package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.estatica.IPassagem;
import src.utils.Direcao;
import src.utils.observer.Observer;

public interface ICave extends ICaveProperties{
	public boolean moveEntidade(int x, int y, Direcao dir);
	public void moverEntidadeEntreSalas(int x, int y, IPassagem p);
	public void addEntidade(int x, int y, IEntidadeDinamica e);
    public IEntidadeDinamica removeEntidade(int x, int y);
    
    public void atacar(IEntidadeViva e, int x, int y);
    
    public void subToLocal(int x, int y, Observer e);
    public void destroy();
}
