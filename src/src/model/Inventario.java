package src.model;

import src.model.entidade.itens.*;

/* TODO:
 * Criar exceptions (ao invez de retornar null em falha) */

public class Inventario {
    private Item[] inventario;
    private int maxTamanho;

    public Inventario(int tamanho) {
        this.maxTamanho = tamanho;
        inventario = new Item[tamanho];
    }

    public Item getArmaEquipada() {
        for (Item i : inventario) {
            if (i instanceof ItemAtaque && ((ItemAtaque) i).isEquipado())
                return i;
        }
        return null;
    }

    public Item getArmaduraEquipada(){
    	return null;
    }

}
