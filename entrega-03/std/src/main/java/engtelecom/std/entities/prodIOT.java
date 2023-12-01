package engtelecom.std.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class prodIOT {
    private long id;
    private String nome;
    private String status;
    private boolean grupo;
    private Long grupoId;
    private int dadosAtual;
}
