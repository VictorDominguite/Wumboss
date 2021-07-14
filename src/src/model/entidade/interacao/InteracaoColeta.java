package src.model.entidade.interacao;

import src.model.entidade.dinamica.IHeroi;
import src.model.entidade.itens.Armadura;
import src.model.entidade.itens.Capacete;
import src.model.entidade.itens.Chave;
import src.model.entidade.itens.Flecha;
import src.model.entidade.itens.IItem;
import src.model.entidade.itens.Mapa;
import src.model.entidade.itens.Tocha;

public class InteracaoColeta{
	public String interagir(IHeroi h, IItem i) {
		coletarItem(h, i);
		return "coleta";
	}
	
	private void coletarItem(IHeroi h, IItem item) {
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
