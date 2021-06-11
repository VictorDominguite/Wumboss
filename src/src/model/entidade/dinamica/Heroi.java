package src.model.entidade.dinamica;

public class Heroi extends EntidadeDinamica {

    private Heroi(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public Heroi() {
    	this(5, 0, 0);
    } 
}
