package src.model.space;

import src.utils.Direcao;

public interface ICaveAction extends ICave {
    public void moverEntidade(int x, int y, Direcao dir);
    public void removerEntidade(int x, int y);
}
