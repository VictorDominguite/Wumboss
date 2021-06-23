package src.model.entidade.dinamica;

import src.model.Inventario;
import src.model.actions.IActionExecutor;

public class Heroi extends EntidadeDinamica implements IActionExecutor {
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

	public String toString() {
		return "heroi";
	}

	public void sendMessage(String action, String... args) {
		
	}
}
