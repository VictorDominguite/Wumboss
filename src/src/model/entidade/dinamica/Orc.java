package src.model.entidade.dinamica;

public class Orc extends EntidadeDinamica {

    private Orc(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Orc() {
    	this(4, 2, 3);
    }
}
