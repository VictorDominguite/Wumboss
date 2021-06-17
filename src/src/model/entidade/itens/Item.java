package src.model.entidade.itens;

import src.model.entidade.Entidade;

public abstract class Item extends Entidade {
    private boolean coletado;
    private boolean equipado;

    public Item(boolean coletado, boolean equipado) {
        this.coletado = coletado;
        this.equipado = equipado;
    }
    public boolean isColetado() {
        return coletado;
    }

    public void coletar() {
        coletado = true;
        this.caveAction.removerEntidade(posX, posY);
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void equipar() {
        equipado = true;
    }
    
    public String toString() {
    	return this.getClass().getSimpleName();
    }
}
