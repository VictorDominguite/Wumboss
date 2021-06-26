package src.model.entidade.itens;

public class Capacete extends Item {
    private int defesa;

    public Capacete() {
        super(false, false);
        this.defesa = 4;
        
        setDescricao("<html> Um protetor de cabeca <br> <em> +4 def </em> </html>");
    }

    public int getDefesa() {
        return defesa;
    }
    
    @Override
    public void coletar() {
        super.coletar();
        equipar();
    }
}
