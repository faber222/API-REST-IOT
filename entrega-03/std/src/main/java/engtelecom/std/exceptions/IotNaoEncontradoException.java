package engtelecom.std.exceptions;

public class IotNaoEncontradoException extends RuntimeException {
    public IotNaoEncontradoException(long id) {
        super("Não foi possível encontrar pessoa com o id: " + id);
    }
}