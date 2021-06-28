package src.utils.exceptions;

public class ErroDeInteracao extends RuntimeException {
	private static final long serialVersionUID = -1331793923387006828L;

	public ErroDeInteracao() {
        super();
    }

    public ErroDeInteracao(String message) {
        super(message);
    }
}
