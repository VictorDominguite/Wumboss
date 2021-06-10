package src.model.entidade.itens;

public class Arco extends ItemAtaque {

    public Arco(boolean coletado, boolean equipado, int dano, int alcance) {
        super(coletado, equipado, dano, alcance);
    }

    public Arco() {
        this(false, false, 5, 2);
    }
}
