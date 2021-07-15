package src.model.space;

import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.Wumboss;
import src.model.entidade.estatica.IPassagem;
import src.utils.Constantes;
import src.utils.Direcao;
import src.utils.exceptions.IDInvalido;
import src.utils.observer.Observer;

public class Caverna implements ICave{
    private int idAtivo;
    private ISala[] salas;

    public Caverna() {
        salas = new ISala[Constantes.NUM_SALAS_CAVERNA];
        idAtivo = 0;
    }
    
    public String[] estado(int x, int y) {
    	return getSalaAtiva().estado(x, y);
    }
    
    public boolean moveEntidade(int xIni, int yIni, Direcao dir) {
    	int[] locFim = Direcao.newLoc(xIni, yIni, dir);
    	int xFim = locFim[0];
    	int yFim = locFim[1];
    	
    	if(!posicoesValidas(xIni, yIni, xFim, yFim))
    		return false;
    	
    	ICelula origem = getSalaAtiva().getCelula(xIni, yIni);
    	ICelula fim = getSalaAtiva().getCelula(xFim, yFim);
    	
    	if(!celulasValidas(origem, fim))
    		return false;
			
    	IEntidadeViva sujeito = (IEntidadeViva) origem.peekEntidade();
    	IEntidade objeto = (IEntidade) fim.peekEntidade() != null 
    							? fim.peekEntidade() 
    							: fim.getBackground();
    	if(sujeito.interagir(objeto))
    		sujeito.processarEfeitos();
    	else return false;
    	return true;
    }
    
    public void addEntidade(int x, int y, IEntidadeDinamica e) {
    	getSalaAtiva().addEntidade(x, y, e);
    }
    
    public IEntidadeDinamica removeEntidade(int x, int y) {
    	return getSalaAtiva().removeEntidade(x, y);
    }
    
    public void subToLocal(int x, int y, Observer e) {
    	getSalaAtiva().subToLocal(x, y, e);
    }
    
    public ISala getSala(int id) {
        if (id < 0 || id >= Constantes.NUM_SALAS_CAVERNA) 
            throw new IDInvalido(id);

        ISala s = salas[id];
        if (s == null || s.getID() != id) 
        	throw new IDInvalido("Sala", s == null ? -1 : s.getID(), id);

        return s;
    }
    
    public ISala getSalaAtiva() {
    	return getSala(idAtivo);
    }
    
    public void setSala(int id, ISala s) {
    	if(s.getID() == id) {
    		salas[id] = s;
    	}
    	else
    		throw new IDInvalido("Sala", s.getID(), id);
    }
    
    public void atacar(IEntidadeViva e, int x, int y) {
        ICelula fim = getSalaAtiva().getCelula(x, y);
        IEntidadeDinamica entFim = fim.peekEntidade();
        
        if(entFim != null && entFim.isInimigo())
            e.interagir(fim.peekEntidade());
    }

    public void trocarDeSala(IPassagem p) {
    	int idInativado = getSalaAtiva().getID();
        idAtivo = p.getDestino();
        
        getSala(idInativado).inativar();
    }
    
    private boolean posicoesValidas(int xIni, int yIni, int xFim, int yFim) {
    	return !(getSalaAtiva().outOfBounds(xIni, yIni) 
    				|| getSalaAtiva().outOfBounds(xFim, yFim));
    }
    
    private boolean celulasValidas(ICelula origem, ICelula fim) {
    	if(origem.peekEntidade() == null)
    		return false;
    	if(fim.getBackground() == null)
    		return false;
    	if(!fim.getBackground().isPassable())
    		return false;
    	
    	return true;
    }

	public boolean ehSalaBoss() {
		for (int i = 0; i < getSalaAtiva().getTamX(); i++) {
			for (int j = 0; j < getSalaAtiva().getTamY(); j++) {
				if (getSalaAtiva().getCelula(i, j).peekEntidade() instanceof Wumboss)
					return true;
			}
		}
		return false;
	}

	public void destroy() {
		for(ISala s : salas) {
			s.destroy();
		}
		salas = null;
		
	}
}