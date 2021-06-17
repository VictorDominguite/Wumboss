package src.model.space.factories;

import java.util.Random;

import src.model.GameModel;
import src.model.entidade.Passagem;
import src.model.entidade.dinamica.Heroi;
import src.model.space.Caverna;
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
			
			cave.setSala(i, atual);	
		}
		
		for(int i = 0; i < Caverna.NUM_SALAS - 1; i++) {
			criarPassagem(cave.getSala(i), cave.getSala(i+1));
		}
		
		Heroi h = new Heroi();
		gm.setHeroi(h);
		gm.setInventario(h.getInventario());
		
		cave.getSala(0).addEntidade(0, 0, h);
		
		return cave;
	}
	
	//TODO: Checar se ja nao existe passagem na celula
	private static void criarPassagem(Sala s1, Sala s2) {
		int d = rand.nextInt(7) + 1;
		switch(Direcao.randomDir(rand)) {
		case NORTE:
			s1.getCelula(d, s1.getTamY() - 1).setBackground(new Passagem(Direcao.NORTE, d, s1, s2));
			s2.getCelula(d, 0).setBackground(new Passagem(Direcao.SUL, d, s2, s1));
			return;
		case SUL:
			s1.getCelula(d, 0).setBackground(new Passagem(Direcao.SUL, d, s1, s2));
			s2.getCelula(d, s2.getTamY() - 1).setBackground(new Passagem(Direcao.NORTE, d, s2, s1));
			return;
		case LESTE:
			s1.getCelula(s1.getTamX() - 1, d).setBackground(new Passagem(Direcao.LESTE, d, s1, s2));
			s2.getCelula(0, d).setBackground(new Passagem(Direcao.OESTE, d, s2, s1));
			return;
		case OESTE:
			s1.getCelula(0, d).setBackground(new Passagem(Direcao.OESTE, d, s1, s2));
			s2.getCelula(s2.getTamX() - 1, d).setBackground(new Passagem(Direcao.LESTE, d, s2, s1));
			return;
		}
	}
}
