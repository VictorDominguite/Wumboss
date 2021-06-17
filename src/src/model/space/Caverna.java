package src.model.space;

import src.model.entidade.Passagem;
import src.utils.Direcao;

public class Caverna implements ICave {
	public static final int NUM_SALAS = 8;
    private static Caverna instance;

    private int idAtivo;
    private Sala[] salas;

    private Caverna() {
        salas = new Sala[NUM_SALAS];
        idAtivo = 0;
    }
    
    /* Invocados pelo montador */
    
    public void setSala(int id, Sala s) {
    	if(s.getID() == id)
    		salas[id] = s;
    	else
    		//TODO: excecao
    		return;
    }

    public Sala getSala(int id) {
        if (id < 0 || id >= NUM_SALAS) {
            // erro
            return null;
        }

        Sala s = salas[id];
        if (s.getID() != id) {
            // erro
            return null;
        }

        return s;
    }
    
    public Sala getSalaAtiva() {
    	return getSala(idAtivo);
    }

    public static Caverna getInstance() {
        if (instance == null)
            instance = new Caverna();

        return instance;
    }

    public void moverEntidade(int x, int y, Direcao dir) {
    	getSalaAtiva().moverEntidade(x, y, dir);
    }

    public void moverEntidadeEntreSalas(int x, int y, Direcao dir) {
        Passagem p = (Passagem) getSalaAtiva().getCelula(x, y).getBackground();
        if(p.getDirecao() == dir) 
        	idAtivo = p.getDestino().getID();
        
    }

    public void removerEntidade(int x, int y) {
        getSalaAtiva().removerEntidade(x, y);
    }
    
    public String[] getState(int x, int y) {
    	return getSalaAtiva().estado(x, y);
    }

}