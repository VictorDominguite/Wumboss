package src.model.space;

import src.utils.*;
import src.model.entidade.*;

public class Sala {
    private Celula[][] celulas;
    private int ID, tamX, tamY;
    private static Caverna cave = Caverna.getInstance();

    public Sala(int ID, int tamX, int tamY) {
        this.ID = ID;
        this.tamX = tamX;
        this.tamY = tamY;
        this.celulas = new Celula[tamY][tamX];
    }
    
    public Celula getCelula(int x, int y) {
        return celulas[y][x];
    }

    public void setCelula(int x, int y, Celula c) {
    	celulas[y][x] = c;
    }

    public void moverEntidade(int xIni, int yIni, Direcao dir) {
        // TODO: implementar interacao
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

        Celula origem = celulas[yIni][xIni];
        Celula fim = celulas[yFim][xFim];

        // Checagem de bordas
        Entidade entFim = fim.getEntidade();
        if (!(entFim instanceof Parede) && !(fim.ehPassagem())) {
            Entidade e = celulas[yIni][xIni].removerEntidade();
            if(entFim == null) {
                fim.addEntidade(e);
            }
            else {
                String interacao = e.interagir(entFim);
                if (interacao == "coleta")
                    fim.addEntidade(e);
                else if (interacao == "ataque")
                    origem.addEntidade(e);
                else {
                    //TODO: excecao - erro na interacao
                }
            }
        }
        // Checagem de passagem entre salas
        else if (fim.ehPassagem()) {
            cave.moverEntidadeEntreSalas(celulas[yIni][xIni], ID, dir);
        }

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
}
