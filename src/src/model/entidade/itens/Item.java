package src.model.entidade.itens;

public abstract class Item {
    private boolean coletado;
    private boolean equipado;

    public Item(boolean coletado, boolean equipado) {
        this.coletado = coletado;
        this.equipado = equipado;
    }
    public boolean isColetado() {
        return coletado;
    }

    public void coletar() {
        coletado = true;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void equipar() {
        equipado = true;
    }
}
