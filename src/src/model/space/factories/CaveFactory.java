package src.model.space.factories;

import src.model.GameModel;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.estatica.Passagem;
import src.model.space.Caverna;
import src.model.space.Sala;
import src.utils.Constantes;
import src.utils.Direcao;

public class CaveFactory {
	//TODO: Fazer lazy loading das salas (i.e. carregar elas somente quando forem necessarias)
	public static Caverna montar(GameModel gm) {
		Caverna cave = Caverna.getInstance();
		
		cave.setSala(0, SalaFactory.montar(0, Constantes.rng.nextInt(Constantes.NUM_SALAS_DISPONIVEIS) + 1));
		for(int i = 1; i < Constantes.NUM_SALAS_CAVERNA; i++) {
			Sala atual =  SalaFactory.montar(i, Constantes.rng.nextInt(Constantes.NUM_SALAS_DISPONIVEIS) + 1);
			
			cave.setSala(i, atual);	
		}
		
		for(int i = 0; i < Constantes.NUM_SALAS_CAVERNA - 1; i++) {
			criarPassagem(cave.getSala(i), cave.getSala(i+1));
		}
		
		Heroi h = new Heroi();
		h.connect(cave);
		gm.setHeroi(h);
		gm.setInventario(h.getInventario());
		
		cave.getSala(0).addEntidade(1, 1, h);
		
		return cave;
	}
	
	//TODO: Checar se ja nao existe passagem na celula
	private static void criarPassagem(Sala s1, Sala s2) {
		Passagem pass = null;
		Passagem passComplemento = null;
		
		int x, y;
		
		int d = Constantes.rng.nextInt(Constantes.TAM_SALAS - 3) + 1;
		switch(Direcao.randomDir(Constantes.rng)) {
		case NORTE:
			x = d;
			y = s2.getTamY() - 1;
			
			pass = new Passagem(Direcao.NORTE, x, 0, s1.getID(), s2.getID());
			passComplemento = new Passagem(Direcao.SUL, x, y, s2.getID(), s1.getID());
			
			s1.getCelula(x, 0).setBackground(pass);
			s2.getCelula(x, y).setBackground(passComplemento);
			
			break;
		case SUL:
			x = d;
			y = s1.getTamY() - 1;
			
			pass = new Passagem(Direcao.SUL, x, y, s1.getID(), s2.getID());
			passComplemento = new Passagem(Direcao.NORTE, x, 0, s2.getID(), s1.getID());
			
			s1.getCelula(x, y).setBackground(pass);
			s2.getCelula(x, 0).setBackground(passComplemento);
			
			break;
		case LESTE:
			x = s1.getTamX() - 1;
			y = d;
			
			pass = new Passagem(Direcao.LESTE, x, y, s1.getID(), s2.getID());
			passComplemento = new Passagem(Direcao.OESTE, 0, y, s2.getID(), s1.getID());
			
			s1.getCelula(x, y).setBackground(pass);
			s2.getCelula(0, y).setBackground(passComplemento);
			
			break;
		case OESTE:
			x = s2.getTamX() - 1;
			y = d;
			
			pass = new Passagem(Direcao.OESTE, 0, y, s1.getID(), s2.getID());
			passComplemento = new Passagem(Direcao.LESTE, x, y, s2.getID(), s1.getID());
			
			s1.getCelula(0, y).setBackground(pass);
			s2.getCelula(x, d).setBackground(passComplemento);
			
			break;
		}
		
		pass.setComplementar(passComplemento);
		passComplemento.setComplementar(pass);
	}
}
