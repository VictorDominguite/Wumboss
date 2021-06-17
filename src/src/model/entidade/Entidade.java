package src.model.entidade;

import src.model.interacao.*;
import src.model.space.ICaveAction;
import java.lang.Math;

public abstract class Entidade implements IRCaveAction, IEntidade {
    private static IInteracao objInteracao = new Interacao();
    protected int posX, posY;
    protected ICaveAction caveAction;

    public String interagir(IEntidade e) {
        return objInteracao.interagir(this, e);
    }

    public void connect(ICaveAction caveAction) {
        this.caveAction = caveAction;
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

    public int distanciaAte(int x, int y) {
        return Math.abs(posX - x) + Math.abs(posY - y);
    }
    
    public boolean isPassable() {
    	return true;
    }
    
    @Override
    public abstract String toString();
    
}