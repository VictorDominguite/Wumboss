package src.model.entidade.dinamica;

import src.model.entidade.itens.IInventario;
import src.utils.actions.IActionExecutor;

public interface IHeroi extends IEntidadeDinamica, IActionExecutor{
    public int getVisao();
    public int getPosX();
    public int getPosY();
    
    public IInventario getInventario();
}
