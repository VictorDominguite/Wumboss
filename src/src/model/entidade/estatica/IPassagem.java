package src.model.entidade.estatica;

public interface IPassagem extends IEntidadeEstatica{
	public int getDestino();
	
	public IPassagem complemento();
	
	public int getXFim();
	public int getYFim();
}
