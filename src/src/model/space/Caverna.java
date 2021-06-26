package src.model.space;

import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IPassagem;
import src.utils.Constantes;
import src.utils.Direcao;
import src.utils.exceptions.IDInvalido;
import src.utils.observer.Observer;

public class Caverna implements ICave{
    private int idAtivo;
    private ISala[] salas;

    public Caverna() {
        salas = new ISala[Constantes.NUM_SALAS_CAVERNA];
        idAtivo = 0;
    }
    
    public String[] estado(int x, int y) {
    	return getSalaAtiva().estado(x, y);
    }
    
    public void moveEntidade(int xIni, int yIni, Direcao dir) {
    	int[] locFim = Direcao.newLoc(xIni, yIni, dir);
    	int xFim = locFim[0];
    	int yFim = locFim[1];
    	
    	if(!posicoesValidas(xIni, yIni, xFim, yFim))
    		return;
    	
    	ICelula origem = getSalaAtiva().getCelula(xIni, yIni);
    	ICelula fim = getSalaAtiva().getCelula(xFim, yFim);
    	
    	if(!celulasValidas(origem, fim))
    		return;
    	
    	if (fim.getBackground().isPassagem() && origem.peekEntidade() instanceof IHeroi) {
    		moverEntidadeEntreSalas(xIni, yIni, (IPassagem) fim.getBackground());
    	} 
    	else {
    		IEntidadeDinamica e = origem.popEntidade();
    		String interacao = e.interagir(fim.peekEntidade());
    		
    		if (interacao.equals("mover") || interacao.equals("coleta")) {
    			fim.pushEntidade(e);
    		} 
    		else if (interacao.equals("ataque") || interacao.equals("parado")) {
    			origem.pushEntidade(e);
    		} 
    		else {
    			// TODO: excecao - erro na interacao
    		}
    		
    		e.processarEfeitos();
    	}
    	atualizarVisaoEInimigos();
    }
    
    public void addEntidade(int x, int y, IEntidadeDinamica e) {
    	getSalaAtiva().addEntidade(x, y, e);
    }
    
    public IEntidadeDinamica removeEntidade(int x, int y) {
    	return getSalaAtiva().removeEntidade(x, y);
    }
    
    public void subToLocal(int x, int y, Observer e) {
    	getSalaAtiva().subToLocal(x, y, e);
    }
    
    public ISala getSala(int id) {
        if (id < 0 || id >= Constantes.NUM_SALAS_CAVERNA) 
            throw new IDInvalido(id);

        ISala s = salas[id];
        if (s == null || s.getID() != id) 
        	throw new IDInvalido("Sala", s == null ? -1 : s.getID(), id);

        return s;
    }
    
    public ISala getSalaAtiva() {
    	return getSala(idAtivo);
    }
    
    public void setSala(int id, ISala s) {
    	if(s.getID() == id) {
    		salas[id] = s;
    		if(id == idAtivo) s.ativar();
    	}
    	else
    		throw new IDInvalido("Sala", s.getID(), id);
    }

    public void moverEntidadeEntreSalas(int xEnt, int yEnt, IPassagem passagem) {
    	int idInativado = getSalaAtiva().getID();
    	IEntidadeDinamica e = removeEntidade(xEnt, yEnt);
    	
        idAtivo = passagem.getDestino();
        getSala(idAtivo).ativar();
        
        int xFim = passagem.getXFim();
        int yFim = passagem.getYFim();
        
        addEntidade(xFim, yFim, e);
        
        getSala(idInativado).inativar();
        
    }
    
    private boolean posicoesValidas(int xIni, int yIni, int xFim, int yFim) {
    	return !(getSalaAtiva().outOfBounds(xIni, yIni) 
    				|| getSalaAtiva().outOfBounds(xFim, yFim));
    }
    
    private boolean celulasValidas(ICelula origem, ICelula fim) {
    	if(origem.peekEntidade() == null)
    		return false;
    	if(fim.getBackground() == null)
    		return false;
    	if(!fim.getBackground().isPassable())
    		return false;
    	
    	return true;
    }
    
    public Heroi getHeroi() {
        ISala atual = getSalaAtiva();
        for (int i = 1; i < atual.getTamX() - 1; i++) {
            for (int j = 1; j < atual.getTamY() - 1; j++) {
                if (atual.getCelula(i, j).peekEntidade() instanceof Heroi) {
                    return (Heroi) atual.getCelula(i, j).peekEntidade();
                }
            }
        }
        return null;
    }

    public void atualizarVisaoEInimigos() {
        ISala salaAtual = getSalaAtiva();
        IHeroi heroi = getHeroi();
        int heroiX = heroi.getPosX(), heroiY = heroi.getPosY();

        for (int i = 0; i < salaAtual.getTamX(); i++) {
            for (int j = 0; j < salaAtual.getTamY(); j++) {
            	ICelula cellAtual = salaAtual.getCelula(i, j);
                
                IEntidadeDinamica e = cellAtual.peekEntidade();
                
                if (e != null && e instanceof IInimigo && ((IInimigo) e).emAlerta())
                    ((IInimigo) e).moverEmDirecaoA(heroiX, heroiY);
                
                if (cellAtual.distanciaAte(heroiX, heroiY) <= heroi.getVisao()) {
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