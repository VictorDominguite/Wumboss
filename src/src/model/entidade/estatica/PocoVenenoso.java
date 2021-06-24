package src.model.entidade.estatica;

import src.model.entidade.Entidade;

public class PocoVenenoso extends Entidade{
    private static final int DANO = 1;
    private static final int DURACAO_EFEITO = 2;

    public static int getDano() {
        return DANO;
    }

    public static int getDuracaoEfeito() {
        return DURACAO_EFEITO;
    }

	@Override
	public String toString() {
		return "pocoveneno";
	}
}
