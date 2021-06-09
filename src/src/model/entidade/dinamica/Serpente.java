package src.model.entidade.dinamica;

public class Serpente extends EntidadeDinamica {

    private Serpente(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Serpente() {
    	this(1, 6, 0);
    }
}
