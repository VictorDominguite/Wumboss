package src.model.space;

import java.util.Stack;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.itens.Flecha;
import src.utils.Constantes;
import src.utils.observer.EventCreator;

public class Celula extends EventCreator implements ICelula{
    private Stack<IEntidadeDinamica> actors;
    private IEntidadeEstatica background;
    private int posX, posY;
    private int nivelLuz = 0;

    public Celula(int x, int y, IEntidadeEstatica background) {
        this.posX = x;
        this.posY = y;
        
        background.setPosX(x);
        background.setPosY(y);
        
        this.actors = new Stack<IEntidadeDinamica>();
        this.background = background;
    }

    public int getNivelLuz() {
    	return nivelLuz;
    }
    
    public void setNivelLuz(int luz) {
    	this.nivelLuz = luz;
    	onUpdate();
    }
    
    public IEntidadeEstatica getBackground() {
    	return background;
    }
    
    public void setBackground(IEntidadeEstatica e) {
    	this.background = e;
    }

    public void pushEntidade(IEntidadeDinamica ent) {
        if (ent instanceof Flecha) {
            int numFlechas = Constantes.rng.nextInt(3);
            if (numFlechas == 0) return; //evita 1 update
            ((Flecha) ent).addFlechas(numFlechas);
        } 

        this.actors.push(ent);
        
        ent.setPosX(posX);
        ent.setPosY(posY);
        
        onUpdate();
    }

    public IEntidadeDinamica popEntidade() {
    	if(this.actors.empty())
    		return null;
    	
    	IEntidadeDinamica e = this.actors.pop();
        
        onUpdate();
        return e;
    }

    public IEntidadeDinamica peekEntidade() {
    	if(this.actors.empty())
    		return null;
    	return actors.peek();
    }
    
    public boolean isVisivel() {
        return getNivelLuz() > 0;
    }
    
    public String[] estado() {
    	String[] res = new String[3];
    	
    	res[0] = getBackground() != null ? getBackground().toString() : "null";
    	res[1] = peekEntidade() != null ? peekEntidade().toString() : "null";
    	res[2] = isVisivel() ? "true" : "false";
    	
    	return res;
    }
    
    public void refresh() {
    	onUpdate();
    }
    
    public void inativar() {
        if (peekEntidade() instanceof IInimigo)
            ((IInimigo) peekEntidade()).desalertar();
    	super.onUpdate(true);
    }


	public void destroy() {
		actors.clear();
		background = null;
		super.disconnectAll();
		
		inativar();
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int compareTo(ICelula c) {
		int numThis = (this.getPosY() << 8) + this.getPosX();
		int numC = (c.getPosY() << 8) + c.getPosX();
		
		if(numThis > numC) return 1;
		if(numThis == numC) return 0;
		return -1;
	}
}
