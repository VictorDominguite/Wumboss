package src.model.entidade.itens;

public class Tocha extends Item {

    private static final int INCREMENTO_VISAO = 1;

    public Tocha() {
        super(false, false);
        
        setDescricao("<html> Pera, como que ela ta queimando a tanto tempo? </html>");
    }
    public int getIncrementoVisao() {
        return INCREMENTO_VISAO;
    }

}
