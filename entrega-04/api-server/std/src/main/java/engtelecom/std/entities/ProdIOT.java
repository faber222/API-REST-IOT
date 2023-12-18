package engtelecom.std.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdIOT {
    private long id;
    private String nome;
    private String status;
    private int dadosAtual;
    private List<String> metodos;
}
