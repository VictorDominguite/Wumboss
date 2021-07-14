package src.model.entidade.interacao;

import src.model.IGameModel;
import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.itens.IItem;
import src.utils.Priority;
import src.utils.exceptions.ErroDeInteracao;

public class Interacao implements IInteracao {
	
	private InteracaoMovimento iMovimento;
	private InteracaoAtaque iAtaque;
	private InteracaoColeta iColeta;
	
	public Interacao() {
		iMovimento = new InteracaoMovimento();
		iAtaque= new InteracaoAtaque();
		iColeta = new InteracaoColeta();
	}
	
	public String interagir(IEntidade e1, IEntidade e2) throws ErroDeInteracao {
		_interagir(e1, e2);
		IGameModel.updateFeed(Priority.LOW);
		return "";
	}

    private void _interagir(IEntidade e1, IEntidade e2) throws ErroDeInteracao {
    	if(e1 == null || e2 == null) 
    		throw new ErroDeInteracao("Nao pode haver interacao entre nulls");
    	
    	if(!(e1 instanceof IEntidadeViva)) return;
    	
    	IEntidadeViva sujeito = (IEntidadeViva) e1;
    	if(!sujeito.estaVivo()) return;
    		
    	if(e2 instanceof IEntidadeEstatica) {
    		iMovimento.interagir(sujeito, (IEntidadeEstatica) e2);
    		return;
    	}
    	
    	if(!(e2 instanceof IEntidadeDinamica)) return;
    	
    	IEntidadeDinamica objeto = (IEntidadeDinamica) e2;
    	
    	if(sujeito.isHeroi()) {
    		if(objeto.isInimigo())
    			iAtaque.interagir((IHeroi) sujeito, (IInimigo) objeto);
    		else if(objeto.isItem())
    			iColeta.interagir((IHeroi) sujeito, (IItem) objeto);
    	}
    	else if(sujeito.isInimigo()) {
    		if(objeto.isHeroi())
    			iAtaque.interagir((IInimigo) sujeito, (IHeroi) objeto);
    		else return;	
    	}
        
        throw new ErroDeInteracao();
    }
}