package src.model.entidade.interacao;

import src.model.IGameModel;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.estatica.IPassagem;
import src.model.entidade.estatica.PocoVenenoso;
import src.model.entidade.itens.IItem;
import src.model.space.ISpace;
import src.utils.Priority;

public class InteracaoMovimento{
	private ISpace space;
	
	public void connectSpace(ISpace space) {
		this.space = space;
	}
	
	public boolean moverParaItem(IHeroi heroi, IItem item) {
		space.removerEntidade(heroi.getPosX(), heroi.getPosY());
		space.addEntidade(item.getPosX(), item.getPosY(), heroi);
		return true;
	}
	
	public boolean interagir(IEntidadeViva entidade, IEntidadeEstatica noCaminho) {
		char res = decodeMove(entidade, noCaminho);
		if(res == 'm') {
			space.removerEntidade(entidade.getPosX(), entidade.getPosY());
			space.addEntidade(noCaminho.getPosX(), noCaminho.getPosY(), entidade);
			entidade.interagirComEntidadeEstatica(noCaminho);
			return true;
		}
		else if(res == 'n'){
			return false;
		}
		else {
			moveEntreSalas((IHeroi) entidade, (IPassagem) noCaminho);
			return true;
		}
	}
	
	private void moveEntreSalas(IHeroi heroi, IPassagem p) {
		space.removerEntidade(heroi.getPosX(), heroi.getPosY());
		space.trocarDeSala(p);
        
		if (space.ehSalaDoBoss()) {
			if (!(heroi.getInventario().getItem("Chave").isColetado())) {
				space.trocarDeSala(p.complemento());
				space.addEntidade(heroi.getPosX(), heroi.getPosY(), heroi);
				IGameModel.sendFeedToView("Voce precisa de uma chave para acessar essa sala!");
				return;
			}
			else {
				IGameModel.sendFeedToView("<html> Voce se arrepia. Uma brisa forte bate <br>"
										+ "na sua cara... Voce tem um mal pressentimento </html>", Priority.MEDIUM);
			}
		}

        int xFim = p.getXFim();
        int yFim = p.getYFim();
        
        space.addEntidade(xFim, yFim, heroi);
	}
	
	private char decodeMove(IEntidadeViva entidade, IEntidadeEstatica noCaminho) {
		if(!noCaminho.isPassable()) 
			return 'n';
		if(noCaminho instanceof PocoVenenoso && entidade.isInimigo())
			return 'n';
		if(noCaminho.isPassagem() && entidade.isHeroi()) 
			return 'k';
			
		return 'm';
	}

}
