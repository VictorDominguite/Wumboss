package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.space.factories.CaveFactory;
import src.utils.Direcao;
import src.utils.observer.Observer;

public class Space implements ISpace{
	private static ISpace instance;
	private static ICave cave;
	
	public static ISpace getInstance() {
    	if (instance == null)
    		instance = new Space();
    	
    	return instance;
    }
	
	private Space() {
		cave = CaveFactory.montar();
	}

	public void moverEntidade(int x, int y, Direcao dir) {
		cave.moveEntidade(x, y, dir);
	}

	public void addEntidade(int x, int y, IEntidadeDinamica e) {
		cave.addEntidade(x, y, e);
	}

	public IEntidadeDinamica removerEntidade(int x, int y) {
		return cave.removeEntidade(x, y);
	}

	public String[] estadoAtual(int x, int y) {
		return cave.estado(x, y);
	}

	public void subToLocal(int x, int y, Observer e) {
		cave.subToLocal(x, y, e);
	}
}
