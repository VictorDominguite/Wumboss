package src.model.entidade.estatica;

import src.utils.Direcao;

/* Indica o local da passagem entre duas salas 
 * Se for em uma parede vertical, conta de baixo para cima, comecando em 0
 * Se for em uma parede horizontal, conta da esquerda para a direita, desde 0 */
public class Passagem extends EntidadeEstatica implements IPassagem{
    private Direcao direcao;
    private int idOrigem, idDestino;
    private Passagem outroLado;
    
    public Passagem(Direcao dir, int posX, int posY, int idOrigem, int idDestino) {
    	this.direcao = dir;
    	this.posX = posX;
    	this.posY = posY;
    	this.idOrigem = idOrigem;
    	this.idDestino = idDestino;
    }
    
    public void setComplementar(Passagem outroLado) {
    	this.outroLado = outroLado;
    }
    
    public Passagem complemento() {
    	return new Passagem(Direcao.contrario(direcao), posX, posY, idDestino, idOrigem);
    }

    public Direcao getDirecao() {
        return direcao;
    }
    
    public int getDestino() {
    	return idDestino;
    }

	@Override
	public String toString() {
		return "Passagem";
	}
	
	@Override
	public boolean isPassable() {
    	return true;
    }

	public int getXFim() {
		return Direcao.newLoc(outroLado.getPosX(), outroLado.getPosY(), direcao)[0];
	}

	public int getYFim() {
		return Direcao.newLoc(outroLado.getPosX(), outroLado.getPosY(), direcao)[1];
	}
}
