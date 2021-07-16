package src.model.entidade.interacao;

import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.itens.IItem;
import src.model.space.ISpace;
import src.utils.exceptions.ErroDeInteracao;

public class Interacao implements IInteracao {
	private ISpace space;
	
	private InteracaoMovimento iMovimento;
	private InteracaoAtaque iAtaque;
	private InteracaoColeta iColeta;
	
	public Interacao() {
		iMovimento = new InteracaoMovimento();
		iAtaque= new InteracaoAtaque();
		iColeta = new InteracaoColeta();
	}
	
	public void connectSpace(ISpace space) {
		this.space = space;
		this.iMovimento.connectSpace(space);
	}
	
	public boolean interagir(IEntidade e1, IEntidade e2) throws ErroDeInteracao {
		boolean res = _interagir(e1, e2);
		if(e1 instanceof IEntidadeDinamica && ((IEntidadeDinamica) e1).isHeroi())
			space.atualizaSpace();
		return res;
	}

    private boolean _interagir(IEntidade e1, IEntidade e2) throws ErroDeInteracao {
    	if(e1 == null || e2 == null) 
    		throw new ErroDeInteracao("Nao pode haver interacao entre nulls");
    	
    	if(!(e1 instanceof IEntidadeViva)) return false;
    	
    	IEntidadeViva sujeito = (IEntidadeViva) e1;
    	if(!sujeito.estaVivo()) return false;
    		
    	if(e2 instanceof IEntidadeEstatica) {
    		return iMovimento.interagir(sujeito, (IEntidadeEstatica) e2);
    	}
    	
    	if(!(e2 instanceof IEntidadeDinamica)) return false;
    	
    	IEntidadeDinamica objeto = (IEntidadeDinamica) e2;
    	
    	if(sujeito.isHeroi()) {
    		if(objeto.isInimigo())
    			return iAtaque.interagir((IHeroi) sujeito, (IInimigo) objeto);
    		else if(objeto.isItem()) {
    			iColeta.interagir((IHeroi) sujeito, (IItem) objeto);
    			return iMovimento.moverParaItem((IHeroi) sujeito, (IItem) objeto);
    		}
    	}
    	else if(sujeito.isInimigo()) {
    		if(objeto.isHeroi()) {
    			iAtaque.interagir((IInimigo) sujeito, (IHeroi) objeto);
    			return true;
    		}
    		return false;	
    	}
        
        throw new ErroDeInteracao("fim da interacao");
    }
}