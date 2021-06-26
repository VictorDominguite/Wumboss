package src.model.space.factories;

import java.util.HashMap;

import src.model.entidade.estatica.EntidadeEstatica;
import src.model.entidade.estatica.Parede;
import src.model.entidade.estatica.PocoVenenoso;
import src.model.entidade.estatica.Vazio;
import src.model.space.Celula;
import src.model.space.ICelula;

public class CelulaFactory {
	private static HashMap<String, Class<? extends EntidadeEstatica>> tabela 
		= new HashMap<String, Class<? extends EntidadeEstatica>>(40);
	static {
		tabela.put("P", Parede.class);
		tabela.put("V", PocoVenenoso.class);
		tabela.put("_", Vazio.class);
		
	}
	
	private static EntidadeEstatica decodeRawEntity(String repr) {
		Class<? extends EntidadeEstatica> classe = tabela.get(repr);
		if(classe == null) return null;
		
		EntidadeEstatica result = null;
		
		try {
			result = classe.getConstructor().newInstance();
		} catch (Exception e) {
			//TODO: Melhor exception handling
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static ICelula montar(int x, int y, String repr) {
		Celula c = new Celula(x, y, decodeRawEntity(repr));
		
		return c;
	}
}
