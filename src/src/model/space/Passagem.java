package src.model.space;

import src.utils.*;

/* Indica o local da passagem entre duas salas 
 * Se for em uma parede vertical, conta de cima para baixo, comecando em 0
 * Se for em uma parede horizontal, conta da esquerda para a direita, desde 0 */
public class Passagem {
    private int posicao;
    private Direcao direcao;
    
    public Passagem(Direcao dir, int pos) {
    	this.direcao = dir;
    	this.posicao = pos;
    }
    
    public Passagem complemento() {
    	return new Passagem(Direcao.contrario(direcao), posicao);
    }

    public int getPosicao() {
        return posicao;
    }

    public Direcao getDirecao() {
        return direcao;
    }
}
