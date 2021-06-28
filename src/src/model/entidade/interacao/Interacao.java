package src.model.entidade.interacao;

import src.model.IGameModel;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.dinamica.Wumboss;
import src.model.entidade.itens.Arco;
import src.model.entidade.itens.Armadura;
import src.model.entidade.itens.Capacete;
import src.model.entidade.itens.Chave;
import src.model.entidade.itens.Flecha;
import src.model.entidade.itens.IItem;
import src.model.entidade.itens.Mapa;
import src.model.entidade.itens.Tocha;
import src.utils.exceptions.ErroDeInteracao;

public class Interacao implements IInteracao {

    public String interagir(IEntidadeViva e1, IEntidadeDinamica e2) throws ErroDeInteracao {
    	if(e2 == null)
    		return "mover";
    	
        if (e1.isHeroi() && e2 instanceof IItem) {
            coletarItem((IHeroi) e1, (IItem) e2);
            return "coleta";
        }
        if (e1.isHeroi() && e2.isInimigo()) {
        	int danoDado = atacar((IHeroi) e1, (IInimigo) e2);
        	if(danoDado == -1)
        		IGameModel.sendFeedToView("... leia a descricao das flechas");
        	else if(danoDado == -2)
        		IGameModel.sendFeedToView("Ainda ta meio longe demais pra voce...");
        	else if(danoDado == 0)
        		IGameModel.sendFeedToView("Voce ainda e fraco demais para dar dano nele :(");
        	else
        		IGameModel.sendFeedToView("Voce deu " + danoDado + " de dano! :o");
        	
            return "ataque";
        }
        if (e1.isInimigo() && e2.isHeroi()) {
        	int danoDado = atacar((IInimigo) e1, (IHeroi) e2);
        	if(danoDado > 40)
        		IGameModel.sendFeedToView("Dica de vida: Nao confie em gatinhos fofinhos");
        	else if(danoDado > 0)
        		IGameModel.sendFeedToView("Voce foi atacado! :(  -" + danoDado + " de vida");
        	else
        		IGameModel.sendFeedToView("Voce e resiliente demais para ele");
        	
            return "ataque";
        }
        
        if (e1.isInimigo()) 
            return "parado";
        
        throw new ErroDeInteracao();
    }

    public int atacar(IEntidadeViva agressor, IEntidadeViva atacado) {
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

    public void coletarItem(IHeroi h, IItem item) {
        item.coletar();
        h.getInventario().getItem(item.getNome()).setColetado(true);
        if (item instanceof Flecha)
            ((Flecha) h.getInventario().getItem("Flecha")).addFlechas(((Flecha) item).getNumFlechas());

        //equipa automaticamente
        if (item instanceof Armadura || item instanceof Capacete || item instanceof Chave ||
            item instanceof Tocha || item instanceof Mapa) 
            h.getInventario().getItem(item.getNome()).equipar();
    }
}