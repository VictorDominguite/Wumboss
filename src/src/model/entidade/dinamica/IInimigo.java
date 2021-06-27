package src.model.entidade.dinamica;

public interface IInimigo extends IEntidadeViva {
	public void getDrop();
	
    public boolean emAlerta();
    public void alertar();
    public void desalertar();
}
