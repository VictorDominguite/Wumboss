package src.model.entidade.itens;

public class Armadura extends Item {
    private int defesa;

    public Armadura(boolean coletado, boolean equipado, int defesa) {
        super(coletado, equipado);
        this.defesa = defesa;
        
        setDescricao("Uma armadura");
    }

    public int getDefesa() {
        return defesa;
    }
}
