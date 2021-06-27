package src.model.entidade.dinamica;

import src.model.entidade.Entidade;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.estatica.PocoVenenoso;
import src.utils.Direcao;

public abstract class EntidadeDinamica extends Entidade implements IEntidadeDinamica{
    protected int vida, ataque, defesa;
    protected int alcance = 1, envenenado = 0;
    protected boolean isInimigo = false;

    public EntidadeDinamica(int vida, int ataque, int defesa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }
    
    public void processarEfeitos() {
    	if(estaEnvenenado()) {
    		receberDano(PocoVenenoso.getDano());
    		envenenado -= 1;
    	}
    }
    
    public void interagirComEntidadeEstatica(IEntidadeEstatica e) {
    	if (e == null) return;
        if(e.efeito().equals("veneno"))
    		this.envenenar();
    }
    
    public boolean isInimigo() {
    	return isInimigo;
    }
    
    public boolean estaEnvenenado() {
    	return envenenado > 0;
    }
    
    public void moverEmDirecaoA(int x, int y) {
    	int deltaX = Math.abs(x - posX);
    	int deltaY = Math.abs(y - posY);
    	
    	if (deltaX > deltaY) {
    		if (x > posX) 
    			mover(Direcao.LESTE);
    		else
    			mover(Direcao.OESTE);
    	}
    	else {
    		if (y > posY)
    			mover(Direcao.SUL);
    		else
    			mover(Direcao.NORTE);
    	}
    }

    public int getAttackDamage() {
        return ataque;
    }

    public int getDefense() {
        return defesa;
    }
    public void receberDano(int dano) {
    	int vidaRestante = this.vida - dano;
    	if (vidaRestante <= 0) {
    		this.vida = 0;
    		this.morrer();
    	}
    	else {
    		this.vida = vidaRestante;
    	}
    }

    protected int getVida() {
        return vida;
    }

    protected int getAlcance() {
        return alcance;
    }

    protected boolean estaVivo() {
        return vida > 0;
    }

    protected void morrer() {
        this.space.removerEntidade(posX, posY);
        this.dropItems();
    }
    
    protected void dropItems() {
    	
    }

    protected void mover(Direcao dir) {
        this.space.moverEntidade(posX, posY, dir);
    }

    protected void envenenar() {
        envenenado = PocoVenenoso.getDuracaoEfeito();
    }

    protected void receberDanoVeneno() {
        receberDano(PocoVenenoso.getDano());
        envenenado -= 1;
    }

}