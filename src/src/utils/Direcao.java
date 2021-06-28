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
    
    public static Direcao compare(int x1, int y1, int x2, int y2) {
    	if (Math.abs(x2 - x1) > Math.abs(y2 - y1)) 
    		return (x2 > x1) ? Direcao.LESTE : Direcao.OESTE;
    	else 
    		return (y2 > y1) ? Direcao.SUL : Direcao.NORTE;
    }
    
    public static Direcao proxima(Direcao d) {
    	switch(d) {
    	case NORTE:
    		return LESTE;
    	case LESTE:
    		return SUL;
    	case SUL:
    		return OESTE;
    	case OESTE:
    		return NORTE;
    	}
    	
    	return null;
    }
    
    public static int[] newLoc(int x, int y, Direcao dir) {
    	 switch (dir) {
         case NORTE:
             y -= 1;
             break;
         case LESTE:
             x += 1;
             break;
         case SUL:
             y += 1;
             break;
         case OESTE:
             x -= 1;
             break;
         default:

     }
    int[] res = {x, y};
    return res;
    }
}
