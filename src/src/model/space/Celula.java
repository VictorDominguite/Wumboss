package src.model.space;

import src.model.entidade.Entidade;
import src.model.entidade.Parede;
import src.model.entidade.dinamica.EntidadeDinamica;
import src.utils.Direcao;
import src.utils.events.EventCreator;

public class Celula extends EventCreator{
    private EntidadeDinamica foreground;
    private Entidade background;
    private boolean visivel = false;
    private int posX, posY;
    private Sala sala;

    public Celula(Sala sala, int x, int y, Entidade background) {
        this.sala = sala;
        this.posX = x;
        this.posY = y;
        
        this.background = background;
    }

    public void moverEntidade(Direcao dir) {
        sala.moverEntidade(posX, posY, dir);
    }

    public EntidadeDinamica getForeground() {
        return foreground;
    }
    
    public Entidade getBackground() {
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

    public void addEntidade(EntidadeDinamica ent) {
        removerEntidade();

        this.foreground = ent;

        this.foreground.setPosX(posX);
        this.foreground.setPosY(posY);

        onUpdate();
    }

    public EntidadeDinamica removerEntidade() {
        EntidadeDinamica e = this.foreground;
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
        return sala.ehBorda(posX, posY) && !(background instanceof Parede);
    }
    
    public String[] estado() {
    	String[] res = new String[3];
    	
    	res[0] = getBackground() != null ? getBackground().toString() : "null";
    	res[1] = getForeground() != null ? getForeground().toString() : "null";
    	res[2] = isVisivel() ? "true" : "false";
    	
    	return res;
    }

}
