package engtelecom.std.exceptions;

public class MetodoNaoPermitidoException extends RuntimeException {
    public MetodoNaoPermitidoException() {
        super("Método não permitido para este endpoint");
    }
}
