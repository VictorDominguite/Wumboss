package src.model.space;

import src.model.entidade.Entidade;
import src.utils.Direcao;

public interface ICaveAction extends ICave {
    public void mover(int x, int y, Direcao dir);
    public Entidade removerEntidade(int x, int y);
}
