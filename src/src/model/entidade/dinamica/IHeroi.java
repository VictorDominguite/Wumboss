package src.model.entidade.dinamica;

import src.model.entidade.itens.Inventario;

public interface IHeroi {
    public int getVisao();
    public int getPosX();
    public int getPosY();
    public Inventario getInventario();
}
