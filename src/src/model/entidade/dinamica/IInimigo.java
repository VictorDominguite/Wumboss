package src.model.entidade.dinamica;

public interface IInimigo extends IEntidadeDinamica {
	public void getDrop();
	
    public boolean emAlerta();
    public void alertar();
}
