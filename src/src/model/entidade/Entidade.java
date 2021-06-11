package src.model.entidade;

import src.model.interacao.*;
import src.model.*;

public abstract class Entidade {
    private static IInteracao objInteracao = new Interacao();
    protected Celula celula;

    public void interagir(Entidade e) {
        objInteracao.interagir(this, e);
    }
}