package src.model.entidade.estatica;

public class Parede extends EntidadeEstatica{

	@Override
	public String toString() {
		return "Parede";
	}
	
	@Override
	public boolean isPassable() {
    	return false;
    }
    
}
