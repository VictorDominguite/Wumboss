package src.model.entidade.itens;

public class Chave extends Item {

    public Chave(boolean coletado, boolean equipado) {
        super(coletado, equipado);
        
        setDescricao("Uma chave");
    }
}
