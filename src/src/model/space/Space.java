package src.model.space;

import src.model.GameModel;
import src.model.IGameModel;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IPassagem;
import src.model.space.factories.CaveFactory;
import src.utils.Direcao;
import src.utils.Priority;
import src.utils.observer.Observer;

public class Space implements ISpace{
	private static ISpace instance;
	private static ICave cave;
	
	private IHeroi heroInstance;
	
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
		if(cave.getSalaAtiva().getCelula(x, y).peekEntidade().isHeroi() && cave.moveEntidade(x, y, dir)) {
			atualizarVisaoEInimigos();
			
			IGameModel.updateFeed(Priority.LOW);
			
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
        
        IInimigo[] inimigosNaSala = new IInimigo[salaAtual.numInimigos()];
        salaAtual.inimigosNaSala(inimigosNaSala);
        for(IInimigo i : inimigosNaSala) {
        	if(i.emAlerta()) {
        		i.moverEmDirecaoA(heroiX, heroiY);
        	}
        	else if(distanciaAte(i.getPosX(), i.getPosY(), heroiX, heroiY) <= heroInstance.getVisao()) {
        		i.alertar();
        	}
        	
        }

        for (int x = 0; x < salaAtual.getTamX(); x++) {
            for (int y = 0; y < salaAtual.getTamY(); y++) {
            	ICelula cellAtual = salaAtual.getCelula(x, y);
                
                if (distanciaAte(x, y, heroiX, heroiY) <= heroInstance.getVisao()) {
                	cellAtual.setVisivel(true);
                }
                else {
                	if(cellAtual.isVisivel() && !heroInstance.getInventario().getItem("Mapa").isEquipado())
                		salaAtual.getCelula(x, y).setVisivel(false);
                }
            }
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

	public void trocarDeSala(IPassagem p) {
		cave.trocarDeSala(p);
	}

	public boolean ehSalaDoBoss() {
		return cave.ehSalaBoss();
	}
}
