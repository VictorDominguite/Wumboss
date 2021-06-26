package src.model.entidade.itens;

import src.model.entidade.Entidade;

public abstract class Item extends Entidade implements IItem{
	private String descricao;
	
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
        this.space.removerEntidade(posX, posY);
        onUpdate();
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void equipar() {
        equipado = true;
        onUpdate();
    }

    public void desequipar() {
        equipado = false;
        onUpdate();
    }
    
    public String toString() {
    	return this.getClass().getSimpleName();
    }
    
    public String getNome() {
    	return toString();
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    protected void setDescricao(String descricao) {
    	this.descricao = descricao;
    }
}
