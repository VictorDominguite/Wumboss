package src.model;

import src.model.entidade.itens.*;

public class Inventario {
    private Item[] inventario;
    private int maxTamanho;
    private int atualTamanho;

    public Inventario(int tamanho) {
        this.maxTamanho = tamanho;
        this.atualTamanho = 0;
        inventario = new Item[tamanho];
    }
    
    public void addItem(Item i) {
    	//TODO: melhor exception
    	if(atualTamanho == maxTamanho)
    		throw new RuntimeException("acabou espaço do inventario");
    	
    	inventario[atualTamanho] = i;
    	atualTamanho += 1;
    }

    public Item getArmaEquipada() {
        for (Item i : inventario) {
            if (i instanceof ItemAtaque && ((ItemAtaque) i).isEquipado())
                return i;
        }
        return null;
    }
    
    public Item[] getItems() {
    	return inventario;
    }
    
    public int getTamanho() {
    	return atualTamanho;
    }

    public Item getArmaduraEquipada(){
    	return null;
    }

}
