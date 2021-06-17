package src.model.entidade;

public class Parede extends Entidade{

	@Override
	public String toString() {
		return "parede";
	}
	
	@Override
	public boolean isPassable() {
    	return false;
    }
    
}
