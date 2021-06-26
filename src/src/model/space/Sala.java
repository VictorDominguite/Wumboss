package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.estatica.IPassagem;
import src.utils.Direcao;
import src.utils.observer.Observer;

public class Sala {
    private ICelula[][] celulas;
    private int ID, tamX, tamY;
    private boolean isAtiva = false;
    private static Caverna cave = Caverna.getInstance();

    public Sala(int ID, int tamX, int tamY) {
        this.ID = ID;
        this.tamX = tamX;
        this.tamY = tamY;
        this.celulas = new Celula[tamY][tamX];
    }
    
    public Celula getCelula(int x, int y) {
    	if(outOfBounds(x, y))
    		throw new RuntimeException("Tentando acessar uma célula que não existe! Posicao: " + x + ":" + y);
        return (Celula) celulas[y][x];
    }

    public void setCelula(int x, int y, Celula c) {
    	celulas[y][x] = c;
    }

    public void moverEntidade(int xIni, int yIni, Direcao dir) {
        int[] locFim = Direcao.newLoc(xIni, yIni, dir);
        int xFim = locFim[0];
        int yFim = locFim[1];
        
        if(!posicoesValidas(xIni, yIni, xFim, yFim))
        	return;
       
        ICelula origem = getCelula(xIni, yIni);
        ICelula fim = getCelula(xFim, yFim);
        
        if(!celulasValidas(origem, fim))
        	return;
        
		if (fim.getBackground().isPassagem() && origem.peekEntidade() instanceof IHeroi) {
			cave.moverEntidadeEntreSalas(xIni, yIni, (IPassagem) fim.getBackground());
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
		cave.atualizarVisaoEInimigos();
    }
    
    private boolean posicoesValidas(int xIni, int yIni, int xFim, int yFim) {
    	return !(outOfBounds(xIni, yIni) || outOfBounds(xFim, yFim));
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

    public IEntidadeDinamica removerEntidade(int x, int y) {
    	return getCelula(x, y).popEntidade();
    }
    
    public void addEntidade(int x, int y, IEntidadeDinamica e) {
    	getCelula(x, y).pushEntidade(e);
    }
    
    public String[] estado(int x, int y) {
    	return getCelula(x, y).estado();
    }
    
    public void setInativa() {
    	isAtiva = false;
    	for(int i = 0; i < getTamX(); i++) {
    		for(int j = 0; j < getTamY(); j++) {
    			getCelula(i, j).inativar();
                getCelula(i, j).setVisivel(false);
    		}
    	}
    }
    
    public void setAtiva() {
    	isAtiva = true;
    }
    
    public int getID() {
        return ID;
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

    public boolean outOfBounds(int x, int y) {
        return (x < 0 || y < 0 || x >= tamX || y >= tamY);
    }
    
    public void subToLocal(int x, int y, Observer e) {
    	getCelula(x, y).subscribe(e);
    }
}
