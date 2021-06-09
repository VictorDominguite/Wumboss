package src.model.factories;

import java.util.Random;

import src.controller.IController;
import src.model.Caverna;
import src.model.Passagem;
import src.model.Sala;
import src.utils.Direcao;

public class CaveFactory {
	private static IController io;
	private static Random rand = new Random();
	
	public static void setIO(IController io) {
		CaveFactory.io = io;
	}
	
	public static void montar() {
		if(io == null)
			//TODO: Excecao
			return;
		
		Caverna cave = Caverna.getInstance();
		
		cave.setSala(0, SalaFactory.montar(0, rand.nextInt(2) + 1));
		for(int i = 1; i < Caverna.NUM_SALAS; i++) {
			Sala atual =  SalaFactory.montar(i, rand.nextInt(2) + 1);
			
			cave.setPassagem(cave.getSala(i - 1), atual, new Passagem(Direcao.randomDir(rand), rand.nextInt(8)));
			
			cave.setSala(i, atual);	
		}
	}
}
