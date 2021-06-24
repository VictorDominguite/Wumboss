package src.model.entidade.interacao;

import src.model.entidade.*;
import src.model.entidade.dinamica.EntidadeDinamica;
import src.model.entidade.dinamica.Heroi;
import src.model.entidade.dinamica.IEntidadeDinamica;
import src.model.entidade.itens.Item;
import src.utils.exceptions.ErroDeInteracao;

public class Interacao implements IInteracao {

    public String interagir(IEntidade e1, IEntidade e2) throws ErroDeInteracao {
        if (e1 instanceof Heroi && e2 instanceof Item) {
            coletarItem((Heroi) e1, (Item) e2);
            return "coleta";
        }
        if (e1 instanceof Heroi && e2 instanceof IEntidadeDinamica) {
            atacar((Heroi) e1, (EntidadeDinamica) e2);
            if (((EntidadeDinamica) e2).estaVivo()) {
                atacar((EntidadeDinamica) e2, (Heroi) e1);
            }
            return "ataque";
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