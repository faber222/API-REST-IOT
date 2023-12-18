package engtelecom.std.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import engtelecom.std.entities.GroupIOT;
import engtelecom.std.entities.ProdIOT;
import engtelecom.std.exceptions.GrupoNaoEncontradoException;
import engtelecom.std.exceptions.IotDeletadoException;
import engtelecom.std.exceptions.IotNaoEncontradoException;
import engtelecom.std.exceptions.MetodoNaoPermitidoException;
import engtelecom.std.service.IotService;

@RestController
public class IotController {
    private static final Logger logger = LoggerFactory.getLogger(IotService.class);

    @Autowired
    private IotService iotService;

    // retorna todos os iots
    @GetMapping
    @RequestMapping("/iot")
    @ResponseStatus(HttpStatus.OK)
    public List<ProdIOT> obterTodosIot() {
        logger.info("Obtendo todos os IOTs do controlador.");
        return this.iotService.getProdIOTs();
    }

    // atualiza um iot
    @PutMapping("/iot")
    @ResponseStatus(HttpStatus.OK)
    public ProdIOT atualizarStatusProdIOT(@RequestBody ProdIOT iot) {
        ProdIOT p = this.iotService.atualizarStatusIot(iot);
        if (p != null) {
            return p;
        }
        throw new IotNaoEncontradoException(iot.getId());
    }

    // cria um iot
    @PostMapping("/iot")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdIOT adicionarIot(@RequestBody ProdIOT p) {
        return this.iotService.cadastrarIot(p);
    }

    // retorna determinado iot
    @GetMapping("/iot/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdIOT obterIot(@PathVariable long id) {
        ProdIOT p = this.iotService.buscarIotPorId(id);
        if (p != null) {
            return p;
        }
        throw new IotNaoEncontradoException(id);
    }

    // deleta um iot
    @DeleteMapping("/iot/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirIot(@PathVariable long id) {
        if (this.iotService.excluirIot(id)) {
            throw new IotDeletadoException(id);
        } else {
            throw new IotNaoEncontradoException(id);
        }
    }

    // retorna todos os grupos
    @GetMapping("/grupo")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupIOT> obterTodosGrupos() {
        return this.iotService.getGroupIOTs();
    }

    // cria um grupo no grupo
    @PostMapping("/grupo")
    @ResponseStatus(HttpStatus.OK)
    public GroupIOT criarGrupo(@RequestBody GroupIOT g) {
        return this.iotService.criarGrupo(g);
    }

    // retorna todos iots de um grupo
    @GetMapping("/grupo/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GroupIOT obterGrupo(@PathVariable long grupoId) {
        GroupIOT group = this.iotService.buscarGrupoPorId(grupoId);
        if (group != null) {
            return group;
        }
        throw new GrupoNaoEncontradoException(grupoId);
    }

    // atualizar status de todos iot de um grupo
    @PutMapping("/grupo/{grupoId}")
    @ResponseStatus(HttpStatus.OK)
    public GroupIOT atualizarStatusDoGrupo(@RequestBody GroupIOT g) {
        GroupIOT group = this.iotService.atualizarStatusGrupo(g);
        if (group != null) {
            return group;
        }
        throw new GrupoNaoEncontradoException(g.getGrupoId());
    }

    @DeleteMapping("/grupo/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirGrupo(@PathVariable int grupoId) {
        if (this.iotService.excluirGrupo(grupoId)) {
            throw new IotDeletadoException(grupoId);
        } else {
            throw new IotNaoEncontradoException(grupoId);
        }
    }

    @ControllerAdvice
    class MetodoNaoPermitido {
        @ResponseBody
        @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
        @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
        String manipularMetodoNaoPermitido(MetodoNaoPermitidoException p) {
            return p.getMessage();
        }
    }

    @ControllerAdvice
    class IotDeletado {
        @ResponseBody
        @ExceptionHandler(IotDeletadoException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String IotDeletado(IotDeletadoException p) {
            return p.getMessage();
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