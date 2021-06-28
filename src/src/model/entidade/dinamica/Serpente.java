package src.model.entidade.dinamica;

public class Serpente extends Inimigo {

    private Serpente(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Serpente() {
    	this(1, 4, 0);
    }

	@Override
	public String toString() {
		return "serpente";
	}
}
