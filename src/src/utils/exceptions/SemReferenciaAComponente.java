package src.utils.exceptions;

public class SemReferenciaAComponente extends RuntimeException{
	private static final long serialVersionUID = -285036684587713301L;

	public SemReferenciaAComponente(String componenteFaltando, String componenteChamando) {
		super("Nao ha referencia a " + componenteFaltando + " em " + componenteChamando);
	}
	
}
