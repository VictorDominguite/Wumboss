package src.model.factories;

import java.io.IOException;

import src.controller.IController;
import src.model.Sala;
import src.utils.exceptions.SemControllerNaMontagem;
import src.utils.exceptions.TipoDeSalaInvalido;

public class SalaFactory {
	private static IController io;
	
	public static void setIO(IController io) {
		SalaFactory.io = io;
	}
	
	public static Sala montar(int id, int tipo) {
		if(io == null)
			throw new SemControllerNaMontagem();
		
		//TODO: tamanho das salas variavel
		Sala s = new Sala(id, 10, 10);
		int x = 0, y = 0;
		
		try {
			String[][] template = io.readSala(tipo);
			
			for(String[] linha : template) {
				for(String celula : linha) {
					s.setCelula(x, y, CelulaFactory.montar(s, x, y, celula));
					x += 1;
				}
				x = 0;
				y += 1;
			}
		} catch(TipoDeSalaInvalido e) {
			System.err.println(e.getMessage());
		} catch(IOException e) {
			System.err.println("Não foi possivel ler o arquivo da sala! ");
		}
		
		return s;
	}
}
