package src.model.entidade.dinamica;

public class Goblin extends EntidadeDinamica {

    private Goblin(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Goblin() {
    	this(2, 1, 0);
    }
}
