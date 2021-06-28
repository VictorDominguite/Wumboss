package src.model.entidade.dinamica;

import java.awt.Color;

import src.model.entidade.estatica.PocoVenenoso;
import src.model.entidade.itens.Elixir;
import src.model.entidade.itens.IInventario;
import src.model.entidade.itens.IItemAtaque;
import src.model.entidade.itens.Inventario;
import src.model.entidade.itens.Tocha;
import src.utils.Direcao;
import src.view.IGameView;

public class Heroi extends EntidadeViva implements IHeroi {
	private IInventario inv;
	private static final int VISAO_PADRAO = 2;

    private Heroi(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        this.inv = new Inventario(10, this);
    }
    
    public Heroi() {
    	this(5, 0, 4);
    }

    public IInventario getInventario() {
    	return this.inv;
    }

	public String toString() {
		return "Heroi";
	}

	public void sendMessage(String action, String... args) {
		if(action.equalsIgnoreCase("move")) {
			if(args != null)
				mover(Direcao.fromString(args[0]));
		}
		else if(action.equalsIgnoreCase("attack")) {
			
		}
		else if(action.equalsIgnoreCase("update")) {
			onUpdate();
		}
	}

	public int getVisao() {
		int visao = VISAO_PADRAO;
		if (inv.getItem("Tocha") != null && inv.getItem("Tocha").isEquipado())
			visao += ((Tocha) inv.getItem("Tocha")).getIncrementoVisao();
		return visao;
	}
	
	@Override
	public void morrer() {
		super.morrer();
		IGameView.setFeedMessage( "<html> Sua jornada chegou ao fim... <br>"
				+ "ó Heroi, tens o que é necessário para <br>"
				+ "tentar novamente? Se sim, pressione ENTER... </html>", Color.red);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		space.refreshLocal(getPosX(), getPosY());
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
		if(estaEnvenenado()) {
    		receberDano(PocoVenenoso.getDano());
    		envenenado -= 1;
    	}
		
		Elixir elixir = (Elixir) inv.getItem("Elixir");
		
		if (elixir != null && elixir.isColetado()) {
			if(elixir.isAtivo()) 
				elixir.incrementarRodadasAtivo();
			else {
				if (elixir.emCooldown()) 
					elixir.diminuirCooldown();
				else 
					elixir.desequipar();
			}
		}
	}

	public void destroy() {
		this.disconnectAll();
		
	}
}
