package src.model.entidade.dinamica;

import src.model.IActionModel;
import src.model.Inventario;

public class Heroi extends EntidadeDinamica implements IRActionModel {
	private Inventario inv;
    private IActionModel actionModel;

    private Heroi(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        
        this.inv = new Inventario(10);
    }
    
    public Heroi() {
    	this(5, 0, 0);
    }
    
    public void connect(IActionModel actionModel) {
        this.actionModel = actionModel;
    }

    public Inventario getInventario() {
    	return this.inv;
    }
}
