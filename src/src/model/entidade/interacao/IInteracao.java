package src.model.entidade.interacao;

import src.model.entidade.IEntidade;
import src.model.space.ISpace;

public interface IInteracao {
    public String interagir(IEntidade e1, IEntidade e2);
    public void connectSpace(ISpace space);
}