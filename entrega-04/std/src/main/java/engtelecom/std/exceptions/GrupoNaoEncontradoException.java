package engtelecom.std.exceptions;

public class GrupoNaoEncontradoException extends RuntimeException {
    public GrupoNaoEncontradoException(Long id) {
        super("Não foi possível encontrar o grupo com o id: " + id);
    }
}