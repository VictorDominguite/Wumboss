package src.model.entidade.dinamica;

import src.model.entidade.itens.IInventario;
import src.utils.actions.IActionExecutor;
import src.utils.observer.IEventCreator;

public interface IHeroi extends IEntidadeViva, IActionExecutor, IEventCreator{
    public int getVisao();
    public int getPosX();
    public int getPosY();
    
    public IInventario getInventario();
    public void destroy();
}
