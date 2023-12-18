package engtelecom.std.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupIOT {
    private Long grupoId;
    private String nome;
    private List<ProdIOT> produtos;
}
