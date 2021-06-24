package src.model.entidade.dinamica;

import src.model.entidade.IEntidade;

public interface IEntidadeDinamica extends IEntidade{
    public void envenenar();
    public boolean estaEnvenenado();
    public void receberDanoVeneno();
}
