package src.model.space.factories;

import java.util.HashMap;

import src.model.entidade.dinamica.EntidadeDinamica;
import src.model.entidade.dinamica.GatinhoFofo;
import src.model.entidade.dinamica.Goblin;
import src.model.entidade.dinamica.Orc;
import src.model.entidade.dinamica.Serpente;
import src.model.entidade.dinamica.Wumboss;
import src.model.entidade.itens.Arco;
import src.model.entidade.itens.Armadura;
import src.model.entidade.itens.Capacete;
import src.model.entidade.itens.Chave;
import src.model.entidade.itens.Elixir;
import src.model.entidade.itens.Espada;
import src.model.entidade.itens.Flecha;
import src.model.entidade.itens.Mapa;
import src.model.entidade.itens.Tocha;

public class ForegroundFactory {
    private static HashMap<String, Class<? extends EntidadeDinamica>> tabela 
		= new HashMap<String, Class<? extends EntidadeDinamica>>(40);
	static {
		tabela.put("o", Orc.class);
		tabela.put("g", Goblin.class);
		tabela.put("c", GatinhoFofo.class);
		tabela.put("s", Serpente.class);
        tabela.put("w", Wumboss.class);

		tabela.put("a", Arco.class);
		tabela.put("f", Flecha.class);
		tabela.put("e", Espada.class);
		tabela.put("k", Chave.class);
		tabela.put("r", Armadura.class);
		tabela.put("h", Capacete.class);
		tabela.put("p", Elixir.class);
		tabela.put("m", Mapa.class);
		tabela.put("t", Tocha.class);
	}

	public static EntidadeDinamica decodeRawEntity(String repr) {
		Class<? extends EntidadeDinamica> classe = tabela.get(repr);
		if(classe == null) return null;
		
		EntidadeDinamica result = null;
		
		try {
			result = classe.getConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("Nao foi possivel decodificar uma entidade dinamica na sala: " + e.getMessage());
		}
		
		return result;
	}
}
