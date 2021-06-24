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
    
    public static Direcao fromString(String s) {
    	if(s.equals("up") || s.equals("cima") || s.equals("norte"))
    		return Direcao.NORTE;
    	if(s.equals("down") || s.equals("baixo") || s.equals("sul"))
    		return Direcao.SUL;
    	if(s.equals("left") || s.equals("esquerda") || s.equals("oeste"))
    		return Direcao.OESTE;
    	if(s.equals("right") || s.equals("direita") || s.equals("leste"))
    		return Direcao.LESTE;
    	
    	return null;
    }
}
