package src.model.space;

import src.model.entidade.Passagem;

public interface ICaveProperties extends ICave {
	public Sala getSala(int id);
	public Passagem obterPassagemEntre(Sala s1, Sala s2);
	public Celula getCelulaEm(int x, int y);
}
