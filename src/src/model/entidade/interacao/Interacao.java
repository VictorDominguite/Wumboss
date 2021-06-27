package src.model.entidade.interacao;

import src.model.entidade.IEntidade;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IEntidadeViva;
import src.model.entidade.dinamica.Inimigo;
import src.model.entidade.itens.Arco;
import src.model.entidade.itens.Flecha;
import src.model.entidade.itens.Item;
import src.utils.exceptions.ErroDeInteracao;
import src.view.IGameView;

public class Interacao implements IInteracao {

    public String interagir(IEntidade e1, IEntidade e2) throws ErroDeInteracao {
    	if(e2 == null)
    		return "mover";
        if (e1 instanceof Heroi && e2 instanceof Item) {
            coletarItem((Heroi) e1, (Item) e2);

            return "coleta";
        }
        if (e1 instanceof Heroi && e2 instanceof Inimigo) {
            atacar((Heroi) e1, (Inimigo) e2);
            return "ataque";
        }
        if (e1 instanceof Inimigo && e2 instanceof Heroi) {
            IGameView.setFeedMessage("Voce foi atacado! :(  -" + atacar((Inimigo) e1, (Heroi) e2) + " de vida");
            return "ataque";
        }
        if (e1 instanceof Inimigo) {
            return "parado";
        }
        throw new ErroDeInteracao();
    }

    public int atacar(IEntidadeViva agressor, IEntidadeViva atacado) {
        int danoCausado;
        // Condição para não atacar com arco sem flechas
        if (agressor instanceof Heroi && ((Heroi)agressor).getInventario().getArmaEquipada() instanceof Arco) {
            Flecha flechas = (Flecha) ((Heroi)agressor).getInventario().getItem("Flecha");
            if (flechas != null && flechas.getNumFlechas() > 0) flechas.usarFlecha();
            else return 0;
        } 

        // Verifica se o ataque está no alcance
        if(!(agressor.getAlcance() >= agressor.distanciaAte(atacado.getPosX(), atacado.getPosY()))) return 0;

        if (agressor.getAttackDamage() > atacado.getDefense()) 
            danoCausado = agressor.getAttackDamage() - atacado.getDefense();
        else
            danoCausado = 0;

        atacado.receberDano(danoCausado);
        return danoCausado;
    }

    public void coletarItem(Heroi h, Item item) {
        item.coletar();
        h.getInventario().getItem(item.getNome()).setColetado(true);
        if (item instanceof Flecha) {
            ((Flecha) h.getInventario().getItem("Flecha")).addFlechas(((Flecha)item).getNumFlechas());
        }
    }
}