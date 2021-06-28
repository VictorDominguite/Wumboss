package src.model.space;

import java.util.Stack;

import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.itens.Flecha;
import src.utils.Constantes;
import src.utils.observer.EventCreator;

public class Celula extends EventCreator implements ICelula{
    private Stack<IEntidadeDinamica> actors;
    private IEntidadeEstatica background;
    private boolean visivel = false;
    private int posX, posY;

    public Celula(int x, int y, IEntidadeEstatica background) {
        this.posX = x;
        this.posY = y;
        
        this.actors = new Stack<IEntidadeDinamica>();
        this.background = background;
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
            if (numFlechas == 0) return;
            ((Flecha) ent).addFlechas(numFlechas);
        } 
        
        popEntidade();

        this.actors.push(ent);
        
        ent.setPosX(posX);
        ent.setPosY(posY);
        
        if (ent instanceof IEntidadeViva)
            ((IEntidadeViva) ent).interagirComEntidadeEstatica(getBackground());
        
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
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
        
        onUpdate();
    }
    
    public String[] estado() {
    	String[] res = new String[3];
    	
    	res[0] = getBackground() != null ? getBackground().toString() : "null";
    	res[1] = peekEntidade() != null ? peekEntidade().toString() : "null";
    	res[2] = isVisivel() ? "true" : "false";
    	
    	return res;
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
}
