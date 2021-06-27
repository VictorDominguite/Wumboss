package src.model.entidade.dinamica;

public class Orc extends Inimigo {

    private Orc(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        this.cooldownMovimento = 2;
    }
    
    public Orc() {
    	this(4, 2, 3);
    }

	@Override
	public String toString() {
		return "orc";
	}
}
