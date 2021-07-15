package src.model.entidade.interacao;

import src.model.IGameModel;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.dinamica.Wumboss;
import src.model.entidade.itens.Arco;
import src.model.entidade.itens.Flecha;

public class InteracaoAtaque{
	public boolean interagir(IHeroi heroi, IInimigo inimigo) {
		int danoDado = atacar(heroi, inimigo);
		
    	if(danoDado == -1)
    		IGameModel.sendFeedToView("... leia a descricao das flechas");
    	else if(danoDado == -2)
    		IGameModel.sendFeedToView("Ainda ta meio longe demais pra voce...");
    	else if(danoDado == 0)
    		IGameModel.sendFeedToView("Voce ainda e fraco demais para dar dano nele :(");
    	else
    		IGameModel.sendFeedToView("Voce deu " + danoDado + " de dano! :o");
    	
		return true;
	}
	
	public boolean interagir(IInimigo inimigo, IHeroi heroi) {
		int danoDado = atacar(inimigo, heroi);
		
    	if(danoDado > 40)
    		IGameModel.sendFeedToView("Dica de vida: Nao confie em gatinhos fofinhos");
    	else if(danoDado > 0)
    		IGameModel.sendFeedToView("Voce foi atacado! :(  -" + danoDado + " de vida");
    	else
    		IGameModel.sendFeedToView("Voce e resiliente demais para ele");
    	
        return true;
	}
	
	private int atacar(IEntidadeViva agressor, IEntidadeViva atacado) {
        int danoCausado;
        
        // Verifica se o ataque está no alcance
        if(agressor.getAlcance() < agressor.distanciaAte(atacado.getPosX(), atacado.getPosY())) 
        	return -2;
        
        // Condição para não atacar com arco sem flechas
        if (agressor.isHeroi() && ((IHeroi) agressor).getInventario().getArmaEquipada() instanceof Arco) {
            Flecha flechas = (Flecha) ((IHeroi)  agressor).getInventario().getItem("Flecha");
            if (flechas != null && flechas.getNumFlechas() > 0) 
            	flechas.usarFlecha();
            else 
            	return -1;
        } 

        if (agressor.getAttackDamage() > atacado.getDefense()) 
            danoCausado = agressor.getAttackDamage() - atacado.getDefense();
        else
            danoCausado = 0;

        atacado.receberDano(danoCausado);
        if(atacado instanceof Wumboss) 
        	((Wumboss) atacado).revidar(agressor);
        
        return danoCausado;
    }

}
