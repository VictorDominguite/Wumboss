package src.model.space;

import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.Passagem;
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
        return (Celula) celulas[y][x];
    }

    public void setCelula(int x, int y, Celula c) {
    	celulas[y][x] = c;
    }

    public void moverEntidade(int xIni, int yIni, Direcao dir) {
        // TODO: implementar interacao
        int xFim = xIni, yFim = yIni;
        switch (dir) {
        	//????????????????????????????????????????????????????????????????????????????????????????????????
            case NORTE:
                xFim -= 1;
                break;
            case LESTE:
                yFim += 1;
                break;
            case SUL:
                xFim += 1;
                break;
            case OESTE:
                yFim -= 1;
                break;
            default:

        }
        ICelula origem = getCelula(xIni, yIni);
        ICelula fim = getCelula(xFim, yFim);

        // Checagem de bordas
        IEntidade backgFim = fim.getBackground();
        IEntidadeDinamica foregFim = fim.getForeground();
        
        if (backgFim == null || backgFim.isPassable()) {
        	IEntidadeDinamica e = origem.removerEntidade();
            if(foregFim == null) {
                fim.addEntidade(e);
            } else {
                String interacao = e.interagir(foregFim);
                if (interacao == "coleta")
                    fim.addEntidade(e);
                else if (interacao == "ataque")
                    origem.addEntidade(e);
                else if (interacao == "veneno") {
                    //TODO
                }
                else {
                    //TODO: excecao - erro na interacao
                }
            }
        }
        // Checagem de passagem entre salas
        else if (backgFim instanceof Passagem) 
            cave.moverEntidadeEntreSalas(xFim, yFim, dir);
        
    }

    public void removerEntidade(int x, int y) {
    	getCelula(x, y).removerEntidade();
    }
    
    public void addEntidade(int x, int y, IEntidadeDinamica e) {
    	getCelula(x, y).addEntidade(e);
    }
    
    public String[] estado(int x, int y) {
    	return getCelula(x, y).estado();
    }
    
    public void setInativa() {
    	isAtiva = false;
    	for(int i = 0; i < getTamX(); i++) {
    		for(int j = 0; j < getTamY(); j++) {
    			getCelula(i, j).inativar();
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

    public boolean ehBorda(int x, int y) {
        if (x == 0 || y == 0 || x == tamX - 1 || y == tamY - 1)
            return true;
        return false;
    }
    
    public void subToLocal(int x, int y, Observer e) {
    	getCelula(x, y).subscribe(e);
    }
}
