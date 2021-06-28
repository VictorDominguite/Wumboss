package src.model.space;

import src.model.entidade.dinamica.Heroi;
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
	
	private int globalTimer = 0;
	
	public static ISpace getInstance() {
    	if (instance == null) {
    		instance = new Space();
    		montarCaverna();
    	}
    	
    	return instance;
    }
	
	private static void montarCaverna() {
		cave = CaveFactory.montar();
	}
	
	public void connect(IHeroi hero) {
		this.heroInstance = hero;
	}

	public boolean moverEntidade(int x, int y, Direcao dir) {
		if(cave.getSalaAtiva().getCelula(x, y).peekEntidade() instanceof Heroi && cave.moveEntidade(x, y, dir)) {
			atualizarVisaoEInimigos();
			globalTimer += 1;
			return true;
		}
		else return cave.moveEntidade(x, y, dir);
		
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
	
	public ICelula getCelula(int x,int y) {
		return cave.getSalaAtiva().getCelula(x, y);
	}

	public void atualizarVisaoEInimigos() {
		if(heroInstance == null)
			return;
		
        ISala salaAtual = cave.getSalaAtiva();
        int heroiX = heroInstance.getPosX(), heroiY = heroInstance.getPosY();
		int maxInimigos = cave.getSalaAtiva().getTamX() * cave.getSalaAtiva().getTamY(); 
		IInimigo inimigosAlerta[] = new IInimigo[maxInimigos];
		int k = 0;

        for (int i = 0; i < salaAtual.getTamX(); i++) {
            for (int j = 0; j < salaAtual.getTamY(); j++) {
            	ICelula cellAtual = salaAtual.getCelula(i, j);
                
                IEntidadeDinamica e = cellAtual.peekEntidade();
                if (e != null && e instanceof IInimigo && ((IInimigo) e).emAlerta()) {
					inimigosAlerta[k] = (IInimigo) e;
					k++;
				}
                
                if (cellAtual.distanciaAte(heroiX, heroiY) <= heroInstance.getVisao()) {
                	cellAtual.setVisivel(true);
                	
                    if (e != null && e instanceof IInimigo) {
                        if (!((IInimigo) e).emAlerta())
                            ((IInimigo) e).alertar();
                    }
                }
                else {
                	if(cellAtual.isVisivel() && !heroInstance.getInventario().getItem("Mapa").isEquipado())
                		salaAtual.getCelula(i, j).setVisivel(false);
                }
            }
        }
		for (IInimigo i : inimigosAlerta) {
			if (i != null && globalTimer % i.getCooldownMovimento() == 0)
				i.moverEmDirecaoA(heroiX, heroiY);
		}
    }
}
