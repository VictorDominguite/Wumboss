package src.model.entidade.dinamica;

import src.model.entidade.Entidade;
import src.utils.*;

public abstract class EntidadeDinamica extends Entidade {
    protected int vida, ataque, defesa;

    public EntidadeDinamica(int vida, int ataque, int defesa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public void mover(Direcao dir) {
        this.celula.moverEntidade(dir);
    }
}