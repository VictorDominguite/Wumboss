package src.model.entidade.dinamica;

import src.model.entidade.Entidade;

public abstract class EntidadeDinamica extends Entidade implements IEntidadeDinamica {
	public boolean isHeroi() {
    	return (this instanceof IHeroi);
    }
    
    public boolean isInimigo() {
    	return (this instanceof IInimigo);
    }
}
