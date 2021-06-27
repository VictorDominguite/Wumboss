package src.model.entidade;

import src.model.space.ISpace;

public interface IEntidade {
	public void connect(ISpace s);
	
	public void setPosX(int x);
	public void setPosY(int y);
	public int getPosX();
	public int getPosY();
	
	public boolean isPassable();

	public int distanciaAte(int x, int y);
}
