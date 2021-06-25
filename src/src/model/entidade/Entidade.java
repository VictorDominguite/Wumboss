package src.model.entidade;

import src.model.entidade.interacao.*;
import src.model.space.ICaveAction;
import src.model.space.IRCaveAction;
import src.utils.exceptions.ErroDeInteracao;
import src.utils.observer.EventCreator;

public abstract class Entidade extends EventCreator implements IRCaveAction, IEntidade {
    private static IInteracao objInteracao = new Interacao();
    protected int posX, posY;
    protected ICaveAction caveAction;

    public String interagir(IEntidade e) {
        try {
            String interacao = objInteracao.interagir(this, e);
            return interacao;
        } catch (ErroDeInteracao erro) {
            //TODO: melhorar excecao
            System.err.println(erro.getMessage());
            return null;
        }
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
    
    public boolean isPassable() {
    	return true;
    }
    
    @Override
    public abstract String toString();
    
}