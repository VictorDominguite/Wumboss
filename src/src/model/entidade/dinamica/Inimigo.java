package src.model.entidade.dinamica;

public abstract class Inimigo extends EntidadeDinamica implements IInimigo {
    
    private boolean emAlerta;

    public Inimigo (int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        
        this.emAlerta = false;
        this.isInimigo = true;
    }
    
    public void getDrop() {
    	
    }

    public boolean emAlerta() {
        return emAlerta;
    }

    public void alertar() {
        emAlerta = true;
    }

}
