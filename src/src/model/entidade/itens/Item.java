package src.model.entidade.itens;

import src.model.IGameModel;
import src.model.entidade.dinamica.EntidadeDinamica;

public abstract class Item extends EntidadeDinamica implements IItem{
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

    public void setColetado(boolean coletado) {
        this.coletado = coletado;
        onUpdate();
    }

    public void coletar() {
        
        coletado = true;
        this.space.removerEntidade(posX, posY);
        
        IGameModel.sendFeedToView("Voce coletou um(a) " + getNome().toLowerCase());
        
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
    
    public String estadoColetado() {
    	if(isColetado()) return "true";
    	return "false";
    }
    
    public String estadoEquipado() {
    	if(isEquipado())
    		return "true";
    	return "false";
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
