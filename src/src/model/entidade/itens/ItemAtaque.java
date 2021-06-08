package src.model.entidade.itens;

public abstract class ItemAtaque extends Item {
    private int dano;
    private int alcance;

    public int getDano() {
        return dano;
    }

    public int getAlcance() {
        return alcance;
    }
}
