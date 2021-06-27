package src.model.entidade.dinamica;

import src.model.entidade.itens.Elixir;
import src.model.entidade.itens.IItemAtaque;
import src.model.entidade.itens.Inventario;
import src.model.entidade.itens.Tocha;
import src.utils.Direcao;

public class Heroi extends EntidadeViva implements IHeroi {
	private Inventario inv;
	private static final int VISAO_PADRAO = 2;

    private Heroi(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        this.inv = new Inventario(10);
    }
    
    public Heroi() {
    	this(5, 0, 100);
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

	public int getVisao() {
		int visao = VISAO_PADRAO;
		if (inv.getItem("Tocha") != null && inv.getItem("Tocha").isEquipado())
			visao += ((Tocha) inv.getItem("Tocha")).getIncrementoVisao();
		return visao;
	}
	
	@Override
	public void receberDano(int dano) {
		super.receberDano(dano);
		onUpdate();
	}
	
	@Override
	public int getAlcance() {
		IItemAtaque arma_equipada = (IItemAtaque) this.getInventario().getArmaEquipada();
		if (arma_equipada != null) {
			return arma_equipada.getAlcance();
		}
		return this.alcance;
	}

	@Override
	public int getAttackDamage() {
		int ataque = 0;
		if (inv.getArmaEquipada() != null)
			ataque += inv.getArmaEquipada().getDano();
		if (inv.getItem("Elixir").isColetado() && ((Elixir) inv.getItem("Elixir")).isAtivo())
			ataque += ((Elixir) inv.getItem("Elixir")).getBonusDano();
		return ataque;
	}

	@Override
	public int getDefense() {
		return inv.getArmaduraEquipada() + this.defesa;
	}

	@Override
	public void processarEfeitos() {
		super.processarEfeitos();
		if (inv.getItem("Elixir") != null && inv.getItem("Elixir").isColetado()) {
			if (!(((Elixir) inv.getItem("Elixir")).isAtivo()) && (((Elixir) inv.getItem("Elixir")).emCooldown())) {
				((Elixir) inv.getItem("Elixir")).diminuirCooldown();;
			}
		}
	}
}
