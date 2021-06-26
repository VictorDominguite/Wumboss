package src.model.entidade.itens;

public class Arco extends ItemAtaque {

    public Arco(boolean coletado, boolean equipado, int dano, int alcance) {
        super(coletado, equipado, dano, alcance);
        
        //setDescricao("Um arco, infelizmente sem manual de instruções");
        setDescricao("<html>Um arco, infelizmente sem manual de instruções...</html>");
    }

    public Arco() {
        this(true, false, 5, 2);
    }
}
