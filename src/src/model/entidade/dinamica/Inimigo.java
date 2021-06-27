package src.model.entidade.dinamica;

public abstract class Inimigo extends EntidadeDinamica implements IInimigo {
    
    private boolean emAlerta, moveuNoRound;

    public Inimigo (int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        
        this.emAlerta = false;
        this.isInimigo = true;
        this.moveuNoRound = false;
    }
    
    public void getDrop() {
    	
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

    public void setMoveuNoRound(boolean moveuNoRound) {
        this.moveuNoRound = moveuNoRound;
    }

    public boolean moveuNoRound() {
        return moveuNoRound;
    }

}
