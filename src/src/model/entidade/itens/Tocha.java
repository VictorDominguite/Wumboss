package src.model.entidade.itens;

public class Tocha extends Item {

    private static final int INCREMENTO_VISAO = 1;

    public Tocha(boolean coletado, boolean equipado) {
        super(coletado, equipado);
        
        setDescricao("Uma tocha");
    }
    public int getIncrementoVisao() {
        return INCREMENTO_VISAO;
    }

}
