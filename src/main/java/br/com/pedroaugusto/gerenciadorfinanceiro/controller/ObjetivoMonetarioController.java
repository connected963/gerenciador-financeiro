package br.com.pedroaugusto.gerenciadorfinanceiro.controller;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.objetivomonetario.ObjetivoMonetarioInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.objetivomonetario.ObjetivoMonetarioService;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.objetivomonetario.ObjetivoMonetario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/objetivomonetario")
public class ObjetivoMonetarioController {

    private final ObjetivoMonetarioService objetivoMonetarioService;

    @Autowired
    public ObjetivoMonetarioController(ObjetivoMonetarioService objetivoMonetarioService) {
        this.objetivoMonetarioService = objetivoMonetarioService;
    }

    @GetMapping("/objetivomonetario")
    public List<ObjetivoMonetario> buscarTodos() {
        return objetivoMonetarioService.buscarTodos();
    }

    @GetMapping("/objetivomonetario/{objetivoMonetarioId}")
    public ObjetivoMonetario buscarPorId(
            @PathVariable final Long objetivoMonetarioId) {
        return objetivoMonetarioService.buscarPorId(objetivoMonetarioId)
                .orElseThrow(() -> new BusinessException(ObjetivoMonetarioService.OBJETIVO_MONETARIO_INEXISTENTE));
    }

    @PostMapping("/objetivomonetario")
    public ResponseEntity inserir(
            @Valid @RequestBody final ObjetivoMonetarioInputModel objetivoMonetario) {
        objetivoMonetarioService.inserir(objetivoMonetario);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/objetivomonetario")
    public void atualizar(@Valid @RequestBody final ObjetivoMonetarioInputModel objetivoMonetario) {
        objetivoMonetarioService.atualizar(objetivoMonetario);
    }

    @DeleteMapping("/objetivomonetario/{objetivoMonetarioId}")
    public void deletar(@PathVariable final Long objetivoMonetarioId) {
        objetivoMonetarioService.remover(objetivoMonetarioId);
    }
}
