package src.model.entidade;

import src.model.entidade.interacao.IInteracao;
import src.model.entidade.interacao.Interacao;
import src.model.space.ISpace;
import src.utils.observer.EventCreator;

public abstract class Entidade extends EventCreator implements IEntidade {
    protected static IInteracao objInteracao = new Interacao();
    protected int posX, posY;
    protected ISpace space;

    public void connect(ISpace space) {
        this.space = space;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }
    
    public boolean isPassable() {
    	return true;
    }
    
    public int distanciaAte(int x, int y) {
        return space.distanciaAte(getPosX(), getPosY(), x, y);
    }

    @Override
    public abstract String toString();
    
}