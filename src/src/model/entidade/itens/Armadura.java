package src.model.entidade.itens;

public class Armadura extends Item {
    private int defesa;

    public Armadura(boolean coletado, boolean equipado) {
        super(coletado, equipado);
    }

    public int getDefesa() {
        return defesa;
    }
}
