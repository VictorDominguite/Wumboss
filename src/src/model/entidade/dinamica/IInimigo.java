package src.model.entidade.dinamica;

public interface IInimigo extends IEntidadeDinamica {
	public void getDrop();
	
    public boolean emAlerta();
    public void alertar();
    public void desalertar();
    public void setMoveuNoRound(boolean moveuNoRound);
    public boolean moveuNoRound();
}
