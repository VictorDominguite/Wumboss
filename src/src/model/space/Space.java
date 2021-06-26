package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.space.factories.CaveFactory;
import src.utils.Direcao;
import src.utils.observer.Observer;

public class Space implements ISpace{
	private static ISpace instance;
	private static ICave cave;
	
	private IHeroi heroInstance;
	
	public static ISpace getInstance() {
    	if (instance == null)
    		instance = new Space();
    	
    	return instance;
    }
	
	private Space() {
		cave = CaveFactory.montar();
	}
	
	public void connect(IHeroi hero) {
		this.heroInstance = hero;
	}

	public void moverEntidade(int x, int y, Direcao dir) {
		if(cave.moveEntidade(x, y, dir)) {
			atualizarVisaoEInimigos();
		}
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
	
	private void atualizarVisaoEInimigos() {
		if(heroInstance == null)
			return;
		
        ISala salaAtual = cave.getSalaAtiva();
        int heroiX = heroInstance.getPosX(), heroiY = heroInstance.getPosY();

        for (int i = 0; i < salaAtual.getTamX(); i++) {
            for (int j = 0; j < salaAtual.getTamY(); j++) {
            	ICelula cellAtual = salaAtual.getCelula(i, j);
                
                IEntidadeDinamica e = cellAtual.peekEntidade();
                
                if (e != null && e instanceof IInimigo && ((IInimigo) e).emAlerta())
                    ((IInimigo) e).moverEmDirecaoA(heroiX, heroiY);
                
                if (cellAtual.distanciaAte(heroiX, heroiY) <= heroInstance.getVisao()) {
                	cellAtual.setVisivel(true);
                	
                    if (e != null && e instanceof IInimigo) {
                        if (!((IInimigo) e).emAlerta())
                            ((IInimigo) e).alertar();
                    }
                }
                else {
                	//TODO: Add check do mapa
                	if(cellAtual.isVisivel())
                		salaAtual.getCelula(i, j).setVisivel(false);
                }
            }
        }
    }
}
