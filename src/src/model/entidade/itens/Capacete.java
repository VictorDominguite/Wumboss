package src.model.entidade.itens;

public class Capacete extends Item {
    private int defesa;

    public Capacete(boolean coletado, boolean equipado) {
        super(coletado, equipado);
    }

    public int getDefesa() {
        return defesa;
    }
}
