package src.model.entidade.dinamica;

import src.model.entidade.Entidade;
import src.utils.*;

public abstract class EntidadeDinamica extends Entidade {
    protected float vida;

    public void mover(Direcao dir) {
        this.celula.moverEntidade(dir);
    }
}