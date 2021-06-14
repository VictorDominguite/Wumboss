package src.model.entidade.dinamica;

import src.model.Inventario;

public class Heroi extends EntidadeDinamica {
	private Inventario inv;

    private Heroi(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        
        this.inv = new Inventario(10);
    }
    
    public Heroi() {
    	this(5, 0, 0);
    }
    
    public Inventario getInventario() {
    	return this.inv;
    }
}
