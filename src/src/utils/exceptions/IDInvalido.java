package src.utils.exceptions;

public class IDInvalido extends RuntimeException{
	private static final long serialVersionUID = -4234889894074745547L;

	public IDInvalido() {
		super();
	}
	
	public IDInvalido(int ID) {
		super("ID invalido: " + ID);
	}
	
	public IDInvalido(int ID, int IDEsperado) {
		super("ID Esperado: " + IDEsperado + ", ID Obtido: " + ID);
	}
	
	public IDInvalido(String obj, int ID, int IDEsperado) {
		super("No objeto " + obj + ": " + "ID Esperado: " + IDEsperado + ", ID Obtido: " + ID);
	}
	
}
