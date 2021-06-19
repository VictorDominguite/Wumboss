package src.model.entidade.itens;

public class Espada extends ItemAtaque {

    public Espada(boolean coletado, boolean equipado, int dano, int alcance) {
        super(coletado, equipado, dano, alcance);
        
        setDescricao("Uma espada");
    }
}
