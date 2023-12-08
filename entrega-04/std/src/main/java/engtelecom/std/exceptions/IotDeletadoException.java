package engtelecom.std.exceptions;

public class IotDeletadoException extends RuntimeException{
    public IotDeletadoException(long id) {
        super("Iot: " + id + ", deletado com sucesso!");
    }
}
