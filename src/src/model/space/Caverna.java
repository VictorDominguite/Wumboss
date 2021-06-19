package src.model.space;

import src.model.entidade.Passagem;
import src.utils.Direcao;
import src.utils.exceptions.IDInvalido;

public class Caverna implements ICave {
	public static final int NUM_SALAS = 8;
    private static Caverna instance;

    private int idAtivo;
    private Sala[] salas;
    
    public static Caverna getInstance() {
    	if (instance == null)
    		instance = new Caverna();
    	
    	return instance;
    }

    private Caverna() {
        salas = new Sala[NUM_SALAS];
        idAtivo = 0;
    }
    
    public Sala getSala(int id) {
        if (id < 0 || id >= NUM_SALAS) 
            throw new IDInvalido(id);

        Sala s = salas[id];
        if (s.getID() != id) 
        	throw new IDInvalido("Sala", s.getID(), id);

        return s;
    }
    
    public Sala getSalaAtiva() {
    	return getSala(idAtivo);
    }
    
    public void setSala(int id, Sala s) {
    	if(s.getID() == id)
    		salas[id] = s;
    	else
    		throw new IDInvalido("Sala", s.getID(), id);
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
    
    public String[] estado(int x, int y) {
    	return getSalaAtiva().estado(x, y);
    }

}