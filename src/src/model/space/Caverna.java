package src.model.space;

import src.model.entidade.*;
import src.model.entidade.dinamica.EntidadeDinamica;
import src.utils.*;

public class Caverna implements ICave {
	public static final int NUM_SALAS = 8;
    private static Caverna instance;

    private int salaAtiva;

    private Sala[] salas;
    /*
     * A posicao [i][j] indica se ha passagem entre as salas i e j e onde ela se
     * encontra
     */
    private Passagem[][] conexoes;

    private Caverna() {
        salas = new Sala[NUM_SALAS];
        conexoes = new Passagem[NUM_SALAS][NUM_SALAS];
        salaAtiva = 0;
    }
    
    /* Invocados pelo montador */
    
    public void setSala(int id, Sala s) {
    	if(s.getID() == id)
    		salas[id] = s;
    	else
    		//TODO: excecao
    		return;
    }
    
    public void setPassagem(Sala s1, Sala s2, Passagem p) {
    	conexoes[s1.getID()][s2.getID()] = p;
    	conexoes[s2.getID()][s1.getID()] = p.complemento();
    }
    
    /* Presente na interface */

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

    public Passagem obterPassagemEntre(Sala s1, Sala s2) {
        return conexoes[s1.getID()][s2.getID()];
    }

    public static Caverna getInstance() {
        if (instance == null)
            instance = new Caverna();

        return instance;
    }

    public Celula getCelulaEm(int x, int y) {
        return salas[salaAtiva].getCelula(x, y);
    }

    public void moverEntidade(int x, int y, Direcao dir) {
        getCelulaEm(x, y).moverEntidade(dir);
    }

    public void moverEntidadeEntreSalas(Celula c, int salaID, Direcao dir) {
        for (int i = 0; i < NUM_SALAS; i++) {
            if (conexoes[salaID][i].getDirecao() == dir) {
                Passagem p = conexoes[salaID][i];
                Sala destino = salas[i];
                int xFim = c.getPosX(), yFim = c.getPosY();
                EntidadeDinamica e = c.removerEntidade();
                switch (p.getDirecao()){
                case NORTE:
                    yFim = destino.getTamY() - 2;
                    break;
                case SUL:
                    yFim = 1;
                    break;
                case LESTE:
                    xFim = 1;
                    break;
                case OESTE:
                    xFim = destino.getTamX() - 2;
                }
                salaAtiva = destino.getID();
                destino.getCelula(xFim, yFim).addEntidade(e);
                break;
            }
        }
    }

    public Entidade removerEntidade(int x, int y) {
        return getCelulaEm(x, y).removerEntidade();
    }
    
    public String[] getState(int x, int y) {
    	return getCelulaEm(x, y).estado();
    }

}