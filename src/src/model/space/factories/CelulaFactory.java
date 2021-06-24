package src.model.space.factories;

import java.util.HashMap;

import src.model.entidade.*;
import src.model.entidade.estatica.Parede;
import src.model.entidade.estatica.PocoVenenoso;
import src.model.space.Celula;
import src.model.space.Sala;

public class CelulaFactory {
	private static HashMap<String, Class<? extends Entidade>> tabela = new HashMap<String, Class<? extends Entidade>>(40);
	static {
		tabela.put("P", Parede.class);
		tabela.put("V", PocoVenenoso.class);
		
	}
	
	private static Entidade decodeRawEntity(String repr) {
		Class<? extends Entidade> classe = tabela.get(repr);
		if(classe == null) return null;
		
		Entidade result = null;
		
		try {
			result = classe.getConstructor().newInstance();
		} catch (Exception e) {
			//TODO: Melhor exception handling
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Celula montar(Sala s, int x, int y, String repr) {
		Celula c = new Celula(s, x, y, decodeRawEntity(repr));
		
		return c;
	}
}
