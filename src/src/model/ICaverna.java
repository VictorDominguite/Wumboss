package src.model;

public interface ICaverna {
	public Sala getSala(int id);
	public Passagem obterPassagemEntre(Sala s1, Sala s2);
}
