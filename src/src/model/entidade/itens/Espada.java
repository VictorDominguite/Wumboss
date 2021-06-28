package src.model.entidade.itens;

public class Espada extends ItemAtaque {

    public Espada() {
        super(false, false, 4, 1);
        
        setDescricao("<html> Uma espada decente... <br> <em> +2 atk - 1 alcance </em> </html>");
    }
}
