package src.model.entidade.dinamica;

public class GatinhoFofo extends EntidadeDinamica {

    private GatinhoFofo(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
    }
    
    public GatinhoFofo() {
    	this(1, 100, 0);
    }
}
