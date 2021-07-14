package src.model.entidade.dinamica;

import src.model.IGameModel;
import src.utils.Priority;

public class Wumboss extends Inimigo {

    private Wumboss(int vida, int ataque, int defesa) {
        super(vida, ataque, defesa);
        this.cooldownMovimento = 2;
    }
    
    public Wumboss() {
    	this(20, 8, 4);
    }

	@Override
	public String toString() {
		return "wumboss";
	}
	
	public void revidar(IEntidadeViva agressor) {
		interagir(agressor);
	}
	
	@Override
	public void morrer() {
		super.morrer();
		
		IGameModel.sendFeedToView("Ò grande guerreiro, <br>"
				+ "conquistador desta caverna.<br>"
				+ "Voce agora esta livre! <br>"
				+ "O que uma vez te pareceu uma <br>"
				+ "ideia distante, nada mais que um sonho, <br>"
				+ "agora se torna realidade. <br>"
				+ "Vá, reencontre teus amigos, tua familia, <br>"
				+ "e adote um gatinho fofo! ☻ ", Priority.HIGH);
	}
}
