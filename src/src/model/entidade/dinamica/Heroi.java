package src.model.entidade.dinamica;

import src.model.entidade.itens.IItemAtaque;
import src.model.entidade.itens.Inventario;
import src.utils.Direcao;
import src.utils.actions.IActionExecutor;

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
		return "Heroi";
	}

	public void sendMessage(String action, String... args) {
		if(action.equals("move")) {
			if(args != null)
				mover(Direcao.fromString(args[0]));
		}
	}

	public int getAlcance() {
		IItemAtaque arma_equipada = (IItemAtaque) this.getInventario().getArmaEquipada();
		if (arma_equipada != null) {
			return arma_equipada.getAlcance();
		}
		return this.alcance;
	}
}
