package engtelecom.std.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import engtelecom.std.entities.GroupIOT;
import engtelecom.std.entities.ProdIOT;

@Component
public class IotService {
    // criando uma lista para simular um banco de dados em memória
    private List<ProdIOT> prodIOTs;
    private List<GroupIOT> GroupIOTs;
    // criando um contador para gerar ids. O contador é estático para que seja
    // compartilhado entre todas as instâncias da classe PessoaService
    // O contador é do tipo AtomicLong para que as operações de incremento e
    // decremento sejam atômicas
    private AtomicLong contadorGrupo;
    private AtomicLong contadorIot;

    // incrementa id do dispositivo
    public IotService() {
        this.prodIOTs = new ArrayList<>();
        this.GroupIOTs = new ArrayList<>();
        this.contadorIot = new AtomicLong();
        this.contadorGrupo = new AtomicLong();
    }

    public GroupIOT criarGrupo(GroupIOT groupIOTs) {
        groupIOTs.setGrupoId(this.contadorGrupo.incrementAndGet());
        this.GroupIOTs.add(groupIOTs);
        return groupIOTs;
    }

    // cadastra dispositivo sem grupo
    public ProdIOT cadastrarIot(ProdIOT iot) {
        iot.setId(this.contadorIot.incrementAndGet());
        this.prodIOTs.add(iot);
        return iot;
    }

    // busca todos os grupos
    public List<GroupIOT> getGroupIOTs() {
        return this.GroupIOTs;
    }

    // busca todos iots
    public List<ProdIOT> getProdIOTs() {
        return this.prodIOTs;
    }

    // busca iot por id
    public ProdIOT buscarIotPorId(Long id) {
        for (ProdIOT p : this.prodIOTs) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // busca iot por id
    public GroupIOT buscarGrupoPorId(Long long1) {
        for (GroupIOT p : this.GroupIOTs) {
            if (p.getGrupoId() == long1) {
                return p;
            }
        }
        return null;
    }

    public int buscarPosicaoIot(ProdIOT iot) {
        int x = 0;
        for (ProdIOT p : this.prodIOTs) {
            if (p.getId() == iot.getId()) {
                return x;
            }
            x++;
        }
        return -1;
    }

    public int buscarPosicaoGrupo(GroupIOT g) {
        int x = 0;
        for (GroupIOT p : this.GroupIOTs) {
            if (p.getGrupoId() == g.getGrupoId()) {
                return x;
            }
            x++;
        }
        return -1;
    }

    // atualiza um determinado iot
    public ProdIOT atualizarStatusIot(ProdIOT iot) {
        ProdIOT p = buscarIotPorId(iot.getId());
        if (p != null) {
            this.prodIOTs.set(buscarPosicaoIot(iot), iot);
            p = iot;
        }
        return p;
    }

    // atualiza status de um determinado iot
    public GroupIOT atualizarStatusGrupo(GroupIOT g) {
        GroupIOT group = buscarGrupoPorId(g.getGrupoId());
        if (group != null) {
            this.GroupIOTs.set(buscarPosicaoGrupo(g), g);
            group = g;
        }
        return group;
    }

    // exclui um determinado iot
    public boolean excluirIot(Long id) {
        ProdIOT p = buscarIotPorId(id);
        if (p != null) {
            this.prodIOTs.remove(p);
            return true;
        }
        return false;
    }

    // exclui um determinado grupo
    public boolean excluirGrupo(long groupId) {
        GroupIOT g = buscarGrupoPorId(groupId);
        if (g != null) {
            this.GroupIOTs.remove(g);
            return true;
        }
        return false;
    }
}