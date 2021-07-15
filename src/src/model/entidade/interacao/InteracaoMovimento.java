package src.model.entidade.interacao;

import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.estatica.IPassagem;
import src.model.entidade.estatica.PocoVenenoso;
import src.model.space.ISpace;

public class InteracaoMovimento{
	private ISpace space;
	
	public void connectSpace(ISpace space) {
		this.space = space;
	}
	
	public void interagir(IEntidadeViva entidade, IEntidadeEstatica noCaminho) {
		if(!noCaminho.isPassable()) return;
		if(noCaminho instanceof PocoVenenoso && entidade.isInimigo())
			return;
		if(noCaminho.isPassagem() && entidade.isHeroi()) {
			space.moverEntidadeEntreSalas((IHeroi) entidade, (IPassagem) noCaminho);
			return;
		}
			
		space.addEntidade(noCaminho.getPosX(), noCaminho.getPosY(), entidade);
	}

}
