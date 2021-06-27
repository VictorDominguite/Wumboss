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
        addItem(new Armadura());
        addItem(new Capacete());
        addItem(new Chave());
        addItem(new Elixir());
        addItem(new Espada());
        addItem(new Faquinha());
        addItem(new Flecha());
        addItem(new Mapa());
        addItem(new Tocha());
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
    
    public IItem getItem(String name) {
    	for(IItem i : inventario) {
    		if(i.getNome().equalsIgnoreCase(name))
    			return i;
    	}
    	
    	return null;
    }
    
    public int getTamanho() {
    	return atualTamanho;
    }

	public void sendMessage(String action, String... args) {
		if(action.equals("equip")){
            if (getItem(args[0]) instanceof Elixir) {
                ((Elixir)getItem(args[0])).consumir();
            }
            if (getArmaEquipada() != null && getItem(args[0]) instanceof ItemAtaque)
                getArmaEquipada().desequipar();
			getItem(args[0]).equipar();
        }
	}

	public String[] getCumulativeNames() {
		String[] res = {"Flecha"};
		return res;
	}

	public String[] getCollectableNames() {
		String[] res = new String[9];
		int j = 0;
		for(IItem i : inventario) {
    		if(!i.getNome().equalsIgnoreCase("flecha")) {
    			res[j] = i.getNome();
    			j += 1;
    		}
    	}
		
		return res;
	}


}
