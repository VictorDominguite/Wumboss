package src.model.entidade.dinamica;

public class Wumboss extends Inimigo {

    private Wumboss(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Wumboss() {
    	this(20, 8, 4);
    }

	@Override
	public String toString() {
		return "wumboss";
	}
}
