package src.model.entidade.dinamica;

import src.model.entidade.itens.Flecha;

public abstract class Inimigo extends EntidadeViva implements IInimigo {
    
    private boolean emAlerta;

    public Inimigo (int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        
        this.emAlerta = false;
    }
    
    @Override
    public void morrer() {
        super.morrer();
        getDrop();
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
