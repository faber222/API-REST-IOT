package engtelecom.std.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import engtelecom.std.entities.prodIOT;

/**
 * PessoaService é uma classe que simula um banco de dados.
 *
 * A anotação @Component indica que a classe PessoaService é um componente do
 * Spring. Isso significa que o Spring irá gerenciar as instâncias dessa classe
 * e irá injetá-las onde for necessário.
 *
 * Classes anotadas com @Component são chamadas de beans. E são singleton por
 * padrão, ou seja, o Spring irá criar apenas uma instância dessa classe e irá
 * compartilhá-la entre todos os componentes que a utilizarem.
 *
 * https://java-design-patterns.com/patterns/singleton/
 *
 */
@Component
public class IotService {
    // criando uma lista para simular um banco de dados em memória
    private List<prodIOT> prodIOTs;
    // criando um contador para gerar ids. O contador é estático para que seja
    // compartilhado entre todas as instâncias da classe PessoaService
    // O contador é do tipo AtomicLong para que as operações de incremento e
    // decremento sejam atômicas
    private AtomicLong contador;

    // incrementa id do dispositivo
    public IotService() {
        this.prodIOTs = new ArrayList<>();
        this.contador = new AtomicLong();

    }

    // cadastra dispositivo sem grupo
    public prodIOT cadastrar(prodIOT iot) {
        iot.setId(this.contador.incrementAndGet());
        iot.setGrupoId(null);
        this.prodIOTs.add(iot);
        return iot;
    }

    // cadastra dispositivo com grupo
    public prodIOT cadastrar(prodIOT iot, Long grupoId) {
        iot.setId(this.contador.incrementAndGet());
        iot.setGrupoId(grupoId);
        this.prodIOTs.add(iot);
        return iot;
    }

    // associa iot existente a um grupo
    public boolean associarIotAoGrupo(Long iotId, Long grupoId) {
        prodIOT iot = buscarPorId(iotId);
        if (iot != null) {
            iot.setGrupoId(grupoId);
            iot.setGrupo(true);
            return true;
        }
        return false;
    }

    // desassocia iot existente de um grupo
    public boolean desassociarIotDoGrupo(Long iotId) {
        prodIOT iot = buscarPorId(iotId);
        if (iot != null) {
            iot.setGrupoId(null);
            iot.setGrupo(false);
            return true;
        }
        return false;
    }

    // busca todos iots sem grupo
    public List<prodIOT> buscarTodos() {
        return this.prodIOTs;
    }

    // busca todos iots de um grupo
    public List<prodIOT> buscarPorGrupo(Long groupId) {
        List<prodIOT> group = new ArrayList<>();
        for (prodIOT iot : this.prodIOTs) {
            if (iot.getGrupoId() == groupId) {
                group.add(iot);
            }
        }
        return group;
    }

    // busca iot por id
    public prodIOT buscarPorId(Long id) {
        for (prodIOT p : this.prodIOTs) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // atualiza um determinado iot
    public prodIOT atualizar(prodIOT iot) {
        prodIOT p = buscarPorId(iot.getId());
        if (p != null) {
            p.setNome(iot.getNome());
        }
        return p;
    }

    // atualiza status de um determinado iot
    public List<prodIOT> atualizarStatus(prodIOT iot, Long grupoId) {
        List<prodIOT> p = new ArrayList<>();
        for (prodIOT iotDoGrupo : buscarPorGrupo(grupoId)) {
            iotDoGrupo.setStatus(iot.getStatus());
            p.add(iotDoGrupo);
        }
        return p;
    }

    // exclui um determinado iot
    public boolean excluir(Long id) {
        prodIOT p = buscarPorId(id);
        if (p != null) {
            this.prodIOTs.remove(p);
            return true;
        }
        return false;
    }

    public boolean coletarIot() {
        // aguarda conexão dos IOT
        // armazena os dados de cada iot conectado
        // em this.cadastrarIOT(ajbabaiji);
        // return true;

        // se não conectar
        return false;
    }
}