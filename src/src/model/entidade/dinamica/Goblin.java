package src.model.entidade.dinamica;

public class Goblin extends Inimigo {

    private Goblin(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Goblin() {
    	this(2, 2, 0);
    }

	@Override
	public String toString() {
		return "goblin";
	}
}
