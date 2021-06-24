package src.utils.exceptions;

public class ErroDeInteracao extends RuntimeException {

    public ErroDeInteracao() {
        super();
    }

    public ErroDeInteracao(String message) {
        super(message);
    }
}
