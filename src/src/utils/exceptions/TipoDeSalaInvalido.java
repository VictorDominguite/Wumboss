package src.utils.exceptions;

public class TipoDeSalaInvalido extends RuntimeException{
	private static final long serialVersionUID = 4118221558894634362L;
	
	public TipoDeSalaInvalido(int tipo) {
		super("Não existe sala do tipo " + tipo);
		
	}

}
