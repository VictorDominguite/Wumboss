package src.model.entidade.dinamica;

import src.model.entidade.Entidade;
import src.utils.*;

public abstract class EntidadeDinamica extends Entidade {
    protected int vida, ataque, defesa, alcance;

    public EntidadeDinamica(int vida, int ataque, int defesa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        alcance = 1;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getVida() {
        return vida;
    }

    public int getAlcance() {
        return alcance;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void morrer() {
        this.caveAction.removerEntidade(posX, posY);
    }

    public void receberDano(int dano) {
        int vidaRestante = this.vida - dano;
        if (vidaRestante <= 0) {
            this.vida = 0;
            this.morrer();
        }
        else {
            this.vida = vidaRestante;
        }
    }

    public void mover(Direcao dir) {
        this.caveAction.mover(posX, posY, dir);
    }
}