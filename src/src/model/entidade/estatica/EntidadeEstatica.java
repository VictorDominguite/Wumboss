package src.model.entidade.estatica;

import src.model.entidade.Entidade;

public abstract class EntidadeEstatica extends Entidade implements IEntidadeEstatica{

	public boolean isPassagem() {
		return (this instanceof Passagem);
	}
}
