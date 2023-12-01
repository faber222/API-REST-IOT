package engtelecom.std.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import engtelecom.std.entities.prodIOT;
import engtelecom.std.exceptions.GrupoNaoEncontradoException;
import engtelecom.std.exceptions.IotNaoEncontradoException;
import engtelecom.std.service.IotService;

@RestController
@RequestMapping("/iot")
public class IotController {
    @Autowired
    private IotService iotService;

    // retorna todos os iots sem grupo
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<prodIOT> obterTodosIot() {
        return this.iotService.buscarTodos();
    }

    // retorna determinado iot sem grupo
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public prodIOT obterIot(@PathVariable long id) {
        prodIOT p = this.iotService.buscarPorId(id);
        if (p != null) {
            return p;
        }
        throw new IotNaoEncontradoException(id);
    }

    // retorna todos iots de um grupo
    @GetMapping("/grupo/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public List<prodIOT> obterTodosIotNoGrupo(@PathVariable Long grupoId) {
        List<prodIOT> group = this.iotService.buscarPorGrupo(grupoId);
        for (prodIOT iot : group) {
            if (iot == null) {
                throw new IotNaoEncontradoException(grupoId);
            }
        }
        return group;
    }

    // adiciona iot sem grupo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public prodIOT adicionarIot(@RequestBody prodIOT p) {
        return this.iotService.cadastrar(p);
    }

    // associa iot existente no grupo
    @PutMapping("/associar/{iotId}/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public void associarIotAoGrupo(@PathVariable Long iotId, @PathVariable Long grupoId) {
        if (!this.iotService.associarIotAoGrupo(iotId, grupoId)) {
            throw new GrupoNaoEncontradoException(grupoId);
        }
    }

    // dessassocia iot do grupo
    @PutMapping("/desassociar/{iotId}")
    @ResponseStatus(HttpStatus.OK)
    public void desassociarIotDoGrupo(@PathVariable Long iotId) {
        if (!this.iotService.desassociarIotDoGrupo(iotId)) {
            throw new IotNaoEncontradoException(iotId);
        }
    }

    // atualiza dados de um iot
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public prodIOT atualizarProdIOT(@RequestBody prodIOT iot) {
        prodIOT p = this.iotService.atualizar(iot);
        if (p != null) {
            return p;
        }
        throw new IotNaoEncontradoException(p.getId());
    }

    // atualizar status de todos iot de um grupo
    @PutMapping("/grupo/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public List<prodIOT> atualizarTodosIotsDoGrupo(@RequestBody prodIOT iot, @PathVariable Long grupoId) {
        List<prodIOT> p = this.iotService.atualizarStatus(iot, grupoId);
        if (p != null) {
            return p;
        }
        throw new GrupoNaoEncontradoException(grupoId);
    }

    // deleta um iot
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirIot(@PathVariable long id) {
        if (!this.iotService.excluir(id)) {
            throw new IotNaoEncontradoException(id);
        }
    }

    @ControllerAdvice
    class IotNaoEncontrado {
        @ResponseBody
        @ExceptionHandler(IotNaoEncontradoException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String IotNaoEncontrado(IotNaoEncontradoException p) {
            return p.getMessage();
        }
    }

    @ControllerAdvice
    class GrupoNaoEncontrado {
        @ResponseBody
        @ExceptionHandler(GrupoNaoEncontradoException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String GrupoNaoEncontrado(GrupoNaoEncontradoException p) {
            return p.getMessage();
        }
    }
}