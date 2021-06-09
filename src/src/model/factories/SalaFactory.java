package src.model.factories;

import src.controller.IController;
import src.model.Sala;
import src.utils.exceptions.TipoDeSalaInvalido;

public class SalaFactory {
	private static IController io;
	
	public static void setIO(IController io) {
		SalaFactory.io = io;
	}
	
	public static Sala montar(int id, int tipo) {
		if(io == null)
			//TODO: Excecao
			return null;
		
		Sala s = new Sala(id);
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
		}
		
		return s;
	}
}
