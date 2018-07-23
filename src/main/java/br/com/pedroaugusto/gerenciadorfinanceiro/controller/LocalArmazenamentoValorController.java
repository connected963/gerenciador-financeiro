package br.com.pedroaugusto.gerenciadorfinanceiro.controller;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamentovalor.LocalArmazenamentoValorInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamentovalor.LocalArmazenamentoValorService;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LocalArmazenamentoValorController {

    private final LocalArmazenamentoValorService localArmazenamentoValorService;

    @Autowired
    public LocalArmazenamentoValorController(final LocalArmazenamentoValorService localArmazenamentoValorService) {
        this.localArmazenamentoValorService = localArmazenamentoValorService;
    }

    @GetMapping("/localarmazenamento/{localArmazenamentoId}/valor")
    public List<LocalArmazenamentoValor> buscarTodosPorLocalArmazenamento(
            @PathVariable final Long localArmazenamentoId) {
        return localArmazenamentoValorService.buscarTodosPorLocalArmazenamentoId(localArmazenamentoId);
    }

    @GetMapping("/localarmazenamento/valor/{localArmazenamentoValorId}")
    public LocalArmazenamentoValor buscarPorId(@PathVariable final Long localArmazenamentoValorId) {
        return localArmazenamentoValorService.buscarPorId(localArmazenamentoValorId);
    }

    @PostMapping("/localarmazenamento/valor")
    public void inserir(
            @Valid @RequestBody final LocalArmazenamentoValorInputModel localArmazenamentoValor) {
        localArmazenamentoValorService.inserir(localArmazenamentoValor);
    }

    @PutMapping("/localarmazenamento/valor")
    public void atualizar(
            @Valid @RequestBody final LocalArmazenamentoValorInputModel localArmazenamentoValor) {
        localArmazenamentoValorService.atualizar(localArmazenamentoValor);
    }

    @DeleteMapping("/localarmazenamento/valor/{localArmazenamentoValorId}")
    public void deletar(@PathVariable final Long localArmazenamentoValorId) {
        localArmazenamentoValorService.remover(localArmazenamentoValorId);
    }
}
