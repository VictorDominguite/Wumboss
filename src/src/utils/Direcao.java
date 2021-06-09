package src.utils;

import java.util.Random;

public enum Direcao {
    NORTE, LESTE, SUL, OESTE;
	
    public static Direcao randomDir(Random r) {
    	return Direcao.values()[r.nextInt(4)];
    }
    
    public static Direcao contrario(Direcao d) {
    	switch(d) {
    	case NORTE:
    		return SUL;
    	case SUL:
    		return NORTE;
    	case LESTE:
    		return OESTE;
    	case OESTE:
    		return LESTE;
    	}
    	
    	return null;
    }
}
