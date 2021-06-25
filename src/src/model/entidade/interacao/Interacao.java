package src.model.entidade.interacao;

import src.model.entidade.*;
import src.model.entidade.dinamica.EntidadeDinamica;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IInimigo;
import src.model.entidade.dinamica.Inimigo;
import src.model.entidade.itens.Item;
import src.utils.exceptions.ErroDeInteracao;

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
            atacar((Inimigo) e1, (Heroi) e2);
            return "ataque";
        }
        if (e1 instanceof Inimigo) {
            return "parado";
        }
        throw new ErroDeInteracao();
    }

    public void atacar(EntidadeDinamica agressor, EntidadeDinamica atacado) {
        int danoCausado;
        if (agressor.getAtaque() > atacado.getDefesa()) 
            danoCausado = agressor.getAtaque() - atacado.getDefesa();
        else
            danoCausado = 0;

        atacado.receberDano(danoCausado);
    }

    public void coletarItem(Heroi h, Item item) {
        h.getInventario().addItem(item);
        item.coletar();
    }
}