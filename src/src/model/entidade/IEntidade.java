package src.model.entidade;

public interface IEntidade {
	public String interagir(IEntidade e);
	
	public void setPosX(int x);
	public void setPosY(int y);
	public int getPosX();
	public int getPosY();
	
	public boolean isPassable();
}
