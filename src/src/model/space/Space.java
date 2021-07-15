package src.model.space;

import src.model.GameModel;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IPassagem;
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
	
	public void connectHero(IHeroi hero) {
		this.heroInstance = hero;
	}

	public boolean moverEntidade(int x, int y, Direcao dir) {
		if(cave.getSalaAtiva().getCelula(x, y).peekEntidade().equals(heroInstance) && cave.moveEntidade(x, y, dir)) {
			atualizarVisaoEInimigos();
			
			globalTimer += 1;
			
			return true;
		}
		else return cave.moveEntidade(x, y, dir);
		
	}
	
	public void moverEntidadeEntreSalas(IHeroi h, IPassagem p) {
		cave.moverEntidadeEntreSalas(h.getPosX(), h.getPosY(), p);
	}

	public void addEntidade(int x, int y, IEntidadeDinamica e) {
		cave.addEntidade(x, y, e);
	}

	public IEntidadeDinamica removerEntidade(int x, int y) {
		return cave.removeEntidade(x, y);
	}
	
	public void atacar(IEntidadeViva e, int x, int y) {
		cave.atacar(e, x, y);
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

        for (int x = 0; x < salaAtual.getTamX(); x++) {
            for (int y = 0; y < salaAtual.getTamY(); y++) {
            	ICelula cellAtual = salaAtual.getCelula(x, y);
                
                IEntidadeDinamica e = cellAtual.peekEntidade();
                if (e != null && e instanceof IInimigo && ((IInimigo) e).emAlerta()) {
					inimigosAlerta[k] = (IInimigo) e;
					k++;
				}
                
                if (distanciaAte(x, y, heroiX, heroiY) <= heroInstance.getVisao()) {
                	cellAtual.setVisivel(true);
                	
                    if (e != null && e instanceof IInimigo) {
                        if (!((IInimigo) e).emAlerta())
                            ((IInimigo) e).alertar();
                    }
                }
                else {
                	if(cellAtual.isVisivel() && !heroInstance.getInventario().getItem("Mapa").isEquipado())
                		salaAtual.getCelula(x, y).setVisivel(false);
                }
            }
        }
		for (IInimigo i : inimigosAlerta) {
			if (i != null && globalTimer % i.getCooldownMovimento() == 0)
				i.moverEmDirecaoA(heroiX, heroiY);
		}
    }

	public void refreshLocal(int x, int y) {
		ICelula c = cave.getSalaAtiva().getCelula(x, y);
		c.setVisivel(c.isVisivel());
	}

	public int distanciaAte(int xIni, int yIni, int xFim, int yFim) {
		return Math.abs(xIni - xFim) + Math.abs(yIni - yFim);
	}

	public void sendMessage(String action, String... args) {
		if(action.equalsIgnoreCase("destroy")) {
			System.exit(0);
		}
		else if(action.equalsIgnoreCase("rebuild")) {
			if(heroInstance.getVida() <= 0)
				GameModel.rebuild();
		}
	}

	public void disconnectHero() {
		this.heroInstance = null;
	}
	
	public void destroy() {
		disconnectHero();
		cave.destroy();
		cave = null;
		Space.instance = null;
	}
}
