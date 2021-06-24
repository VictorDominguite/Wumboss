package src.model.entidade.estatica;

import src.model.entidade.Entidade;
import src.model.space.Sala;
import src.utils.Direcao;

/* Indica o local da passagem entre duas salas 
 * Se for em uma parede vertical, conta de baixo para cima, comecando em 0
 * Se for em uma parede horizontal, conta da esquerda para a direita, desde 0 */
public class Passagem extends Entidade{
    private int posicao;
    private Direcao direcao;
    //TODO: Interface
    private Sala origem, destino;
    
    public Passagem(Direcao dir, int pos, Sala origem, Sala destino) {
    	this.direcao = dir;
    	this.posicao = pos;
    	this.origem = origem;
    	this.destino = destino;
    }
    
    public Passagem complemento() {
    	return new Passagem(Direcao.contrario(direcao), posicao, destino, origem);
    }

    public int getPosicao() {
        return posicao;
    }

    public Direcao getDirecao() {
        return direcao;
    }
    
    public Sala getDestino() {
    	return destino;
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
