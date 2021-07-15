package src.model.entidade.dinamica;

import src.model.IGameModel;
import src.model.entidade.IEntidade;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.estatica.PocoVenenoso;
import src.utils.Direcao;
import src.utils.exceptions.ErroDeInteracao;

public abstract class EntidadeViva extends EntidadeDinamica implements IEntidadeViva{
    protected int vida, ataque, defesa;
    protected int alcance = 1, envenenado = 0;
    protected int cooldownMovimento = 1;

    public EntidadeViva(int vida, int ataque, int defesa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }
    
    public int getCooldownMovimento() {
    	return cooldownMovimento;
    }
    
    public boolean interagir(IEntidade e) {
        try {
            return objInteracao.interagir(this, e);
        } catch (ErroDeInteracao erro) {
            System.err.println("Houve algum erro na interacao de " +
            			this.toString() + " e " + e + "\n" + erro.getMessage());
        }
        return false;
    }
    
    public void processarEfeitos() {
        if (this instanceof Heroi) {
            ((Heroi) this).processarEfeitos();
            return;
        }
        
        if (estaEnvenenado()) {
    		receberDano(PocoVenenoso.getDano());
    		envenenado -= 1;
    	}
    }
    
    public void interagirComEntidadeEstatica(IEntidadeEstatica e) {
    	if (e == null) return;
        if (e.efeito().equals("veneno"))
    		this.envenenar();
    }
    
    public boolean estaEnvenenado() {
    	return envenenado > 0;
    }
    
    public void moverEmDirecaoA(int x, int y) {
    	Direcao d = Direcao.compare(getPosX(), getPosY(), x, y);
    	
    	Direcao dInicial = d;
    	while(!mover(d)) {
    		d = Direcao.proxima(d);
    		if(d == dInicial) break;
    	}
    }

    public int getAttackDamage() {
        return ataque;
    }

    public int getDefense() {
        return defesa;
    }
    
    public int getVida() {
    	return vida;
    }
    
    public void receberDano(int dano) {
    	this.vida -= dano;
    	if (vida <= 0) {
    		this.vida = 0;
    		this.morrer();
    	}
    }

    public int getAlcance() {
        return alcance;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    protected void morrer() {
        this.space.removerEntidade(posX, posY);
    }

    protected boolean mover(Direcao dir) {
        return this.space.moverEntidade(posX, posY, dir);
    }

    protected void envenenar() {
        envenenado = PocoVenenoso.getDuracaoEfeito();
        IGameModel.sendFeedToView("eww, voce pulou numa <br> piscina de veneno!");
    }

    protected void receberDanoVeneno() {
        receberDano(PocoVenenoso.getDano());
        envenenado -= 1;
    }

}