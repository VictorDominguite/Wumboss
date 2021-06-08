package POO.jogo.model.entidade.dinamica;

import POO.jogo.model.entidade.Entidade;
import POO.jogo.utils.*;

public abstract class EntidadeDinamica extends Entidade {
    protected float vida;

    public void mover(Direcao dir) {
        this.celula.moverEntidade(dir);
    }
}