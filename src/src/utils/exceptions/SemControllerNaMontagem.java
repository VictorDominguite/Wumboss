package src.utils.exceptions;

public class SemControllerNaMontagem extends RuntimeException{
	private static final long serialVersionUID = 5863449264049286429L;
	
	public SemControllerNaMontagem() {
		super("Nao ha controller para ler arquivos na montagem da caverna");
	}

}
