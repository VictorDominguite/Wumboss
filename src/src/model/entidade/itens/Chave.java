package src.model.entidade.itens;

public class Chave extends Item {

    public Chave() {
        super(false, false);
        
        setDescricao("<html> Uma chave bonita que abre uma porta bonita </html>");
    }
    
    @Override
    public void coletar() {
        super.coletar();
        equipar();
    }
}
