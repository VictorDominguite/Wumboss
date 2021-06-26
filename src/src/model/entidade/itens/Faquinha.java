package src.model.entidade.itens;

public class Faquinha extends ItemAtaque {

    public Faquinha() {
        super(true, true, 1, 1);
        
        setDescricao("<html> Nao serve nem para cortar pão... <em> +1 atk - 1 alcance </em> </html>");
    }
}
