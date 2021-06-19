package src.model.entidade.itens;

public class Faquinha extends ItemAtaque {

    public Faquinha(boolean coletado, boolean equipado, int dano, int alcance) {
        super(coletado, equipado, dano, alcance);
        
        setDescricao("Uma faquinha");
    }
}
