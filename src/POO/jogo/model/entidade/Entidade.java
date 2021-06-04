package POO.jogo.model.entidade;

import POO.jogo.model.interacao.*;
import POO.jogo.model.*;

public abstract class Entidade {
    private static IInteracao objInteracao = new Interacao();
    protected Celula celula;

    protected void interagir(Entidade e) {
        objInteracao.interagir(this, e);
    }
}