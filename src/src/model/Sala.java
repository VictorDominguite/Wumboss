package src.model;

import src.utils.*;
import src.model.entidade.*;

public class Sala {
    private Celula[][] celulas;
    private int ID;

    //TODO: inicializar celula
    public Sala(int ID) {
        this.ID = ID;
    }
    
    public void setCelula(int x, int y, Celula c) {
    	celulas[y][x] = c;
    }

    public void moverEntidade(int xIni, int yIni, Direcao dir) {
        // TODO: Checar bordas
        int xFim = xIni, yFim = yIni;
        switch (dir) {
            case NORTE:
                yFim += 1;
                break;
            case LESTE:
                xFim += 1;
                break;
            case SUL:
                yFim -= 1;
                break;
            case OESTE:
                xFim -= 1;
                break;
            default:

        }

        Celula fim = celulas[yFim][xFim];

        Entidade e = celulas[yIni][xIni].removerEntidade();
        fim.addEntidade(e);
    }

    public int getID() {
        return ID;
    }
}
