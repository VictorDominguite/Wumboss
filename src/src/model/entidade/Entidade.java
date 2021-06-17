package src.model.entidade;

import src.model.interacao.*;
import src.model.space.ICaveAction;

public abstract class Entidade implements IRCaveAction {
    private static IInteracao objInteracao = new Interacao();
    protected int posX, posY;
    protected ICaveAction caveAction;

    public void interagir(Entidade e) {
        objInteracao.interagir(this, e);
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
    
}