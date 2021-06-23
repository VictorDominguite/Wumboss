package src.utils.exceptions;

public class MensagemInvalida extends RuntimeException{
	private static final long serialVersionUID = -4234889894074745548L;

	public MensagemInvalida() {
		super();
	}
	
	public MensagemInvalida(String origem, String fim) {
		super("Originador: " + origem + ", para: " + fim);
	}
	
	public MensagemInvalida(String origem, String fim, String msg) {
		super("Originador: " + origem + ", para: " + fim + ", mensagem: " + msg);
	}
}
