package src.model.space;

import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.estatica.Passagem;
import src.model.entidade.estatica.PocoVenenoso;
import src.utils.Direcao;
import src.utils.observer.EventCreator;

public class Celula extends EventCreator implements ICelula{
    private IEntidadeDinamica foreground;
    private IEntidade background;
    private boolean visivel = false;
    private int posX, posY;
    private Sala sala;

    public Celula(Sala sala, int x, int y, IEntidade background) {
        this.sala = sala;
        this.posX = x;
        this.posY = y;
        
        this.background = background;
    }

    public IEntidadeDinamica getForeground() {
        return foreground;
    }
    
    public IEntidade getBackground() {
    	return background;
    }

    public Sala getSala() {
        return sala;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public void moverEntidade(Direcao dir) {
    	sala.moverEntidade(posX, posY, dir);
    }

    public void addEntidade(IEntidadeDinamica ent) {
        removerEntidade();

        this.foreground = ent;

        this.foreground.setPosX(posX);
        this.foreground.setPosY(posY);

        if (background instanceof PocoVenenoso && ent instanceof Heroi) {
            ent.envenenar();
        }

        onUpdate();
    }

    public IEntidadeDinamica removerEntidade() {
    	IEntidadeDinamica e = this.foreground;
        this.foreground = null;
        
        onUpdate();
        return e;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
        
        onUpdate();
    }

    public boolean ehPassagem(){
        return (background instanceof Passagem);
    }
    
    public String[] estado() {
    	String[] res = new String[3];
    	
    	res[0] = getBackground() != null ? getBackground().toString() : "null";
    	res[1] = getForeground() != null ? getForeground().toString() : "null";
    	res[2] = isVisivel() ? "true" : "false";
    	
    	return res;
    }
    
    public void setBackground(IEntidade e) {
    	this.background = e;
    }
    
    public void inativar() {
    	super.onUpdate(true);
    }

}
