package src.model.entidade.estatica;

import src.model.entidade.Entidade;

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
