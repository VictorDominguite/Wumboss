package src.model.entidade.itens;

public abstract class ItemAtaque extends Item {
    private int dano;
    private int alcance;

    public ItemAtaque(boolean coletado, boolean equipado, int dano, int alcance) {
        super(coletado, equipado);
        this.dano = dano;
        this.alcance = alcance;
    }

    public int getDano() {
        return dano;
    }

    public int getAlcance() {
        return alcance;
    }
}
