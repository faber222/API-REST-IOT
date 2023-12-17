package engtelecom.std.exceptions;

public class GrupoNaoEncontradoException extends RuntimeException {
    public GrupoNaoEncontradoException(Long long1) {
        super("Não foi possível encontrar o grupo com o id: " + long1);
    }
}