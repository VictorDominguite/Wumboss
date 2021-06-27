package src.model.space.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src.model.entidade.estatica.Passagem;
import src.model.space.Caverna;
import src.model.space.ISala;
import src.model.space.Sala;
import src.utils.Constantes;
import src.utils.Direcao;

public class CaveFactory {
	// TODO: Fazer lazy loading das salas (i.e. carregar elas somente quando forem
	// necessarias)
	public static Caverna montar() {
		Caverna cave = new Caverna();
		ArrayList<Integer> tiposSalas = new ArrayList<Integer>();

		for (int i = 0; i < Constantes.NUM_SALAS_CAVERNA - 1; i ++) {
			tiposSalas.add(i);
		}

		Collections.shuffle(tiposSalas);

		while (tiposSalas.get(0) > 5)
			Collections.shuffle(tiposSalas);

		cave.setSala(0, SalaFactory.montar(0, tiposSalas.get(0)));
		for (int i = 1; i < Constantes.NUM_SALAS_CAVERNA - 1; i++) {
			Sala atual = SalaFactory.montar(i, tiposSalas.get(i));

			cave.setSala(i, atual);
		}
		cave.setSala(Constantes.NUM_SALAS_CAVERNA - 1, 
					SalaFactory.montar(Constantes.NUM_SALAS_CAVERNA - 1, Constantes.NUM_SALAS_CAVERNA - 1));
		Direcao anterior = Direcao.NORTE;
		for (int i = 0; i < Constantes.NUM_SALAS_CAVERNA - 1; i++) {
			anterior = criarPassagem(cave.getSala(i), cave.getSala(i + 1), anterior);
		}

		return cave;
	}

	// TODO: Checar se ja nao existe passagem na celula
	private static Direcao criarPassagem(ISala s1, ISala s2, Direcao anterior) {
		Passagem pass = null;
		Passagem passComplemento = null;

		int x, y;
		int d = Constantes.rng.nextInt(Constantes.TAM_SALAS - 3) + 1;
		Direcao dir = Direcao.randomDir(Constantes.rng);

		while (dir == anterior)
			dir = Direcao.randomDir(Constantes.rng);

		switch (dir) {
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

		return Direcao.contrario(dir);
	}
}
