package src.model.space;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.utils.Direcao;

public interface ICaveAction {
    public void moverEntidade(int x, int y, Direcao dir);
    public IEntidadeDinamica removerEntidade(int x, int y);
}
