package POO.jogo.model;

import POO.jogo.utils.*;
import POO.jogo.model.entidade.*;

public class Sala {
    private Celula[][] celulas;
    private int ID;

    public Sala(int ID) {
        this.ID = ID;
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
