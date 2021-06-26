package src.model.entidade.itens;

public class Armadura extends Item {
    private int defesa;

    public Armadura() {
        super(false, false);
        this.defesa = 4;
        
        setDescricao("<html> Um peitoral de ferro <br> <em> +4 def </em> </html>");
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
