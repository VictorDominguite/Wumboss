package src.model.entidade.itens;

import src.utils.observer.Observer;

public class Inventario implements IInventario{
    private IItem[] inventario;
    private int maxTamanho;
    private int atualTamanho;

    public Inventario(int tamanho) {
        this.maxTamanho = tamanho;
        this.atualTamanho = 0;
        inventario = new IItem[tamanho];
        
        addItem(new Arco());
        addItem(new Armadura(false, false, 4));
        addItem(new Capacete(false, false, 4));
        addItem(new Chave(false, false));
        addItem(new Elixir(false, false));
        addItem(new Espada(false, false, 2, 1));
        addItem(new Faquinha(true, true, 1, 1));
        addItem(new Flecha(false, false));
        addItem(new Mapa(false, false));
        addItem(new Tocha(false, false));
    }
    
    public void subToItem(String item, Observer e) {
    	getItem(item).subscribe(e);
    }
    
    public void addItem(IItem i) {
    	//TODO: melhor exception
    	if(atualTamanho == maxTamanho)
    		throw new RuntimeException("acabou espaço do inventario");
    	
    	inventario[atualTamanho] = i;
    	atualTamanho += 1;
    }

    public IItemAtaque getArmaEquipada() {
        for (IItem i : inventario) {
            if (i instanceof ItemAtaque && ((ItemAtaque) i).isEquipado())
                return (IItemAtaque) i;
        }
        return null;
    }
    
    public int getArmaduraEquipada(){
    	int armadura = 0;
        if (getItem("Armadura").isEquipado()) {
            armadura += ((Armadura) getItem("Armadura")).getDefesa();
        }
        if (getItem("Capacete").isEquipado()) {
            armadura += ((Capacete) getItem("Capacete")).getDefesa();
        }
        return armadura;
    }
    
    public IItem[] getItems() {
    	return inventario;
    }
    
    public IItem getItem(String name) {
    	for(IItem i : inventario) {
    		if(i.getNome().equals(name))
    			return i;
    	}
    	
    	return null;
    }
    
    public int getTamanho() {
    	return atualTamanho;
    }


}
