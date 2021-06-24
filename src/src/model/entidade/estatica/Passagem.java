package src.model.entidade.estatica;

import src.utils.Direcao;

/* Indica o local da passagem entre duas salas 
 * Se for em uma parede vertical, conta de baixo para cima, comecando em 0
 * Se for em uma parede horizontal, conta da esquerda para a direita, desde 0 */
public class Passagem extends EntidadeEstatica implements IPassagem{
    private int posicao;
    private Direcao direcao;
    private int idOrigem, idDestino;
    
    public Passagem(Direcao dir, int pos, int idOrigem, int idDestino) {
    	this.direcao = dir;
    	this.posicao = pos;
    	this.idOrigem = idOrigem;
    	this.idDestino = idDestino;
    }
    
    public Passagem complemento() {
    	return new Passagem(Direcao.contrario(direcao), posicao, idDestino, idOrigem);
    }

    public int getPosicao() {
        return posicao;
    }

    public Direcao getDirecao() {
        return direcao;
    }
    
    public int getDestino() {
    	return idDestino;
    }

	@Override
	public String toString() {
		return "passagem";
	}
	
	@Override
	public boolean isPassable() {
    	return false;
    }
}
