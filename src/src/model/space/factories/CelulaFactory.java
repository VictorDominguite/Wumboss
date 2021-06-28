package src.model.space.factories;

import java.util.HashMap;

import src.model.entidade.Entidade;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.EntidadeEstatica;
import src.model.entidade.estatica.Parede;
import src.model.entidade.estatica.PocoVenenoso;
import src.model.entidade.estatica.Vazio;
import src.model.space.Celula;
import src.model.space.ICelula;
import src.model.space.Space;

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
		if(classe == null) classe = Vazio.class;
		
		EntidadeEstatica result = null;
		
		try {
			result = classe.getConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("Nao foi possivel decodificar uma entidade estativa na sala: " + e.getMessage());
		}
		
		return result;
	}
	
	public static ICelula montar(int x, int y, String repr) {
		Celula c = new Celula(x, y, decodeRawEntity(repr));
		Entidade e = ForegroundFactory.decodeRawEntity(repr);
		if (e != null) {
			e.connect(Space.getInstance());
			c.pushEntidade((IEntidadeDinamica) e);
		}
		return c;
	}
}
