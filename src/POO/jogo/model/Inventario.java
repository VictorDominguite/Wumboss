package POO.jogo.model;

import POO.jogo.model.entidade.itens.*;

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
    }

    public Item getArmaduraEquipada(){

    }

    public Item getItem(class<? extends Item> classe){
        for(Item i : inventario) {
            if(i instanceof classe) return i;
        }
    }
}
