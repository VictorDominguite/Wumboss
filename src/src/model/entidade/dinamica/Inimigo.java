package src.model.entidade.dinamica;

import src.model.entidade.itens.Flecha;

public abstract class Inimigo extends EntidadeViva implements IInimigo {
    
    private boolean emAlerta;
    protected int cooldownMovimento = 0;
    protected int baseCooldownMovimento = 0;

    public Inimigo (int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        
        this.emAlerta = false;
    }
    
    public int getCooldownMovimento() {
    	return cooldownMovimento;
    }
    
    @Override
    public void morrer() {
        super.morrer();
        getDrop();
    }
    
    @Override
    public void moverEmDirecaoA(int x, int y) {
    	if(this.getCooldownMovimento() == 0) {
    		super.moverEmDirecaoA(x, y);
    		this.cooldownMovimento = baseCooldownMovimento;
    	}
    	else {
    		this.cooldownMovimento -= 1;
    	}
    		
    }

    public void getDrop() {
        Flecha flecha = new Flecha();
        flecha.connect(space);
        space.addEntidade(posX, posY, flecha);
    }

    public boolean emAlerta() {
        return emAlerta;
    }

    public void alertar() {
        this.emAlerta = true;
    }
    
    public void desalertar() {
    	this.emAlerta = false;
    }

}
