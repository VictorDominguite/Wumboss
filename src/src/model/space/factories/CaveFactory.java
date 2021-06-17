package src.model.space.factories;

import java.util.Random;

import src.model.GameModel;
import src.model.entidade.dinamica.Heroi;
import src.model.space.Caverna;
import src.model.space.Passagem;
import src.model.space.Sala;
import src.utils.Direcao;

public class CaveFactory {
	private static Random rand = new Random();
	
	//TODO: Fazer lazy loading das salas (i.e. carregar elas somente quando forem necessarias)
	public static Caverna montar(GameModel gm) {
		Caverna cave = Caverna.getInstance();
		
		cave.setSala(0, SalaFactory.montar(0, rand.nextInt(2) + 1));
		for(int i = 1; i < Caverna.NUM_SALAS; i++) {
			Sala atual =  SalaFactory.montar(i, rand.nextInt(2) + 1);
			
			cave.setPassagem(cave.getSala(i - 1), atual, new Passagem(Direcao.randomDir(rand), rand.nextInt(8)));
			
			cave.setSala(i, atual);	
		}
		
		Heroi h = new Heroi();
		gm.setHeroi(h);
		gm.setInventario(h.getInventario());
		
		cave.getSala(0).addEntidade(0, 0, h);
		
		return cave;
	}
}
