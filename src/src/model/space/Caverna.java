package src.model.space;

import src.model.entidade.dinamica.EntidadeDinamica;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.dinamica.Inimigo;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.estatica.IPassagem;
import src.utils.Constantes;
import src.utils.Direcao;
import src.utils.exceptions.IDInvalido;
import src.utils.observer.Observer;

public class Caverna implements ICave{
    private static Caverna instance;

    private int idAtivo;
    private Sala[] salas;
    
    public static Caverna getInstance() {
    	if (instance == null)
    		instance = new Caverna();
    	
    	return instance;
    }

    private Caverna() {
        salas = new Sala[Constantes.NUM_SALAS_CAVERNA];
        idAtivo = 0;
    }
    
    public Sala getSala(int id) {
        if (id < 0 || id >= Constantes.NUM_SALAS_CAVERNA) 
            throw new IDInvalido(id);

        Sala s = salas[id];
        if (s == null || s.getID() != id) 
        	throw new IDInvalido("Sala", s == null ? -1 : s.getID(), id);

        return s;
    }
    
    public Sala getSalaAtiva() {
    	return getSala(idAtivo);
    }
    
    public void setSala(int id, Sala s) {
    	if(s.getID() == id) {
    		salas[id] = s;
    		if(id == idAtivo) s.setAtiva();
    	}
    	else
    		throw new IDInvalido("Sala", s.getID(), id);
    }

    public void moverEntidade(int x, int y, Direcao dir) {
    	getSalaAtiva().moverEntidade(x, y, dir);
    }

    public void moverEntidadeEntreSalas(int xEnt, int yEnt, IEntidadeEstatica passagem) {
    	if(!passagem.isPassagem())
    		throw new RuntimeException("A passagem não é uma passagem!");
    	
    	int idInativado = getSalaAtiva().getID();
    	IEntidadeDinamica e = removerEntidade(xEnt, yEnt);
    	
        idAtivo = ((IPassagem) passagem).getDestino();
        getSala(idAtivo).setAtiva();
        
        int xFim = ((IPassagem) passagem).getXFim();
        int yFim = ((IPassagem) passagem).getYFim();
        
        System.out.println(xFim + ":" + yFim);
        
        
        addEntidade(xFim, yFim, e);
        
        getSala(idInativado).setInativa();
        
    }

    public IEntidadeDinamica removerEntidade(int x, int y) {
        return getSalaAtiva().removerEntidade(x, y);
    }
    
    private void addEntidade(int x, int y, IEntidadeDinamica e) {
    	getSalaAtiva().addEntidade(x, y, e);
    }
    
    public String[] estado(int x, int y) {
    	return getSalaAtiva().estado(x, y);
    }
    
    public void subToLocal(int x, int y, Observer e) {
    	getSalaAtiva().subToLocal(x, y, e);
    }

    public Heroi getHeroi() {
        Sala atual = getSalaAtiva();
        for (int i = 1; i < atual.getTamX() - 1; i++) {
            for (int j = 1; j < atual.getTamY() - 1; j++) {
                if (atual.getCelula(i, j).getEntidade() instanceof Heroi) {
                    return (Heroi) atual.getCelula(i, j).getEntidade();
                }
            }
        }
        return null;
    }

    public void atualizarVisaoEInimigos() {
        Sala atual = getSalaAtiva();
        IHeroi heroi = getHeroi();
        int heroiX = heroi.getPosX(), heroiY = heroi.getPosY();

        for (int i = 0; i < atual.getTamX(); i++) {
            for (int j = 0; j < atual.getTamY(); j++) {
                if (atual.getCelula(i, j).distanciaAte(heroiX, heroiY) <= heroi.getVisao()) {
                    atual.getCelula(i, j).setVisivel(true);
                    IEntidadeDinamica e = atual.getCelula(i, j).getEntidade();
                    if (e != null && e instanceof Inimigo) {
                        IInimigo inimigo = (IInimigo) e;
                        if (inimigo.emAlerta())
                            inimigo.moverEmDirecaoA(heroiX, heroiY);
                        else
                            inimigo.alertar();
                    }
                }
                else {
                    atual.getCelula(i, j).setVisivel(false);
                }
            }
        }
    }

}