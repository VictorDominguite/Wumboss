package src.model.space;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import src.model.GameModel;
import src.model.IGameModel;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IPassagem;
import src.model.space.factories.CaveFactory;
import src.utils.Direcao;
import src.utils.ImmutableVec3;
import src.utils.Priority;
import src.utils.observer.Observer;

public class Space implements ISpace{
	private static ISpace instance;
	private static ICave cave;
	
	private IHeroi heroInstance;
	private Queue<ImmutableVec3<Integer>> filaLuz = 
			new LinkedList<ImmutableVec3<Integer>>();
	
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
	
	public void atualizaSpace() {
		atualizarVisaoEInimigos();
		IGameModel.updateFeed(Priority.LOW);
	}

	public boolean moverEntidade(int x, int y, Direcao dir) {
		if(cave.getSalaAtiva().getCelula(x, y).peekEntidade().isHeroi() && cave.moveEntidade(x, y, dir)) {
			atualizaSpace();
			
			return true;
		}
		else return cave.moveEntidade(x, y, dir);
	}

	public void atualizarVisaoEInimigos() {
		if(heroInstance == null)
			return;
		
        ISala salaAtual = cave.getSalaAtiva();
        int heroiX = heroInstance.getPosX(), heroiY = heroInstance.getPosY();
        
        IInimigo[] inimigosNaSala = new IInimigo[salaAtual.numInimigos()];
        salaAtual.inimigosNaSala(inimigosNaSala);
        for(IInimigo i : inimigosNaSala) {
        	if(i.emAlerta()) 
        		i.moverEmDirecaoA(heroiX, heroiY);
        	else if(distanciaAte(i.getPosX(), i.getPosY(), heroiX, heroiY) <= heroInstance.getVisao()) 
        		i.alertar();
        }

        updateLuzEPropagar(heroiX, heroiY, heroInstance.getVisao()+1);
    }
	
	private void updateLuzEPropagar(int x, int y, int nivelLuzInicial) {
		if(!filaLuz.isEmpty())
			throw new RuntimeException("A fila de luz deveria estar vazia!");
		var percorridos = new ArrayList<ICelula>(25);
		
		percorridos.add(updateLuz(percorridos, x, y, nivelLuzInicial));
		
		while(!filaLuz.isEmpty()){
			var atual = filaLuz.poll();
			
			if(cave.getSalaAtiva().outOfBounds(atual.first(), atual.second()))
				continue;
			
			percorridos.add(
				updateLuz(percorridos, atual.first(), atual.second(), atual.third())
			);
		}
	}
	
	private ICelula updateLuz(	ArrayList<ICelula> percorridos,
								int x,
								int y,
								int nivelLuz){
		ISala salaAtiva = cave.getSalaAtiva();
		
		ICelula cell = salaAtiva.getCelula(x, y);
		if(nivelLuz == 0) {
			if(cell.isVisivel() 
					&& !percorridos.contains(cell)
					&& !heroInstance.getInventario().getItem("Mapa").isEquipado())
				cell.setNivelLuz(0);
		}
		else {
			if(nivelLuz > cell.getNivelLuz()) 
				cell.setNivelLuz(nivelLuz);
			if(!percorridos.contains(cell)) {
				filaLuz.offer(new ImmutableVec3<Integer>(x+1, y, nivelLuz-1));
				filaLuz.offer(new ImmutableVec3<Integer>(x-1, y, nivelLuz-1));
				filaLuz.offer(new ImmutableVec3<Integer>(x, y+1, nivelLuz-1));
				filaLuz.offer(new ImmutableVec3<Integer>(x, y-1, nivelLuz-1));
			}
		}
		
		return cell;
	}

	public void refreshLocal(int x, int y) {
		cave.getSalaAtiva().getCelula(x, y).refresh();
	}

	public int distanciaAte(int xIni, int yIni, int xFim, int yFim) {
		return Math.abs(xIni - xFim) + Math.abs(yIni - yFim);
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
