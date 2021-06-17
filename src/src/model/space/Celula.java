package src.model.space;

import src.model.entidade.*;
import src.utils.*;

public class Celula {
    private Entidade ent;
    private boolean visivel = false;
    private int posX, posY;
    private Sala sala;

    public Celula(Sala sala, int x, int y) {
        this.sala = sala;
        this.posX = x;
        this.posY = y;
    }

    public void moverEntidade(Direcao dir) {
        sala.moverEntidade(posX, posY, dir);
    }

    public Entidade getEntidade() {
        return ent;
    }

    public Sala getSala() {
        return sala;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void addEntidade(Entidade ent) {
        removerEntidade();

        this.ent = ent;
        this.ent.setPosX(posX);
        this.ent.setPosY(posY);
    }

    public Entidade removerEntidade() {
        Entidade e = this.ent;
        this.ent = null;
        return e;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public boolean ehPassagem(){
        return sala.ehBorda(posX, posY) && !(ent instanceof Parede);
    }

}
