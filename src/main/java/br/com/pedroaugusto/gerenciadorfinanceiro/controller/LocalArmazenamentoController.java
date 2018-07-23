package br.com.pedroaugusto.gerenciadorfinanceiro.controller;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamento.LocalArmazenamentoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento.LocalArmazenamentoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LocalArmazenamentoController {

    private final LocalArmazenamentoService localArmazenamentoService;

    @Autowired
    public LocalArmazenamentoController(final LocalArmazenamentoService localArmazenamentoService) {
        this.localArmazenamentoService = localArmazenamentoService;
    }

    @GetMapping("/localarmazenamento")
    public List<LocalArmazenamento> buscarTodos() {
        return localArmazenamentoService.buscarTodos();
    }

    @GetMapping("/localarmazenamento/{localArmazenamentoId}")
    public LocalArmazenamento buscarLocalArmazenamentoPorId(@PathVariable final Long localArmazenamentoId) {
        return localArmazenamentoService.buscarPorId(localArmazenamentoId);
    }

    @PostMapping("/localarmazenamento")
    public void inserir(
            @Valid @RequestBody final LocalArmazenamentoInputModel localArmazenamento) {
        localArmazenamentoService.inserir(localArmazenamento);
    }

    @PutMapping("/localarmazenamento")
    public void atualizar(@Valid @RequestBody final LocalArmazenamentoInputModel localArmazenamento) {
        localArmazenamentoService.atualizar(localArmazenamento);
    }

    @DeleteMapping("/localarmazenamento/{localArmazenamentoId}")
    public void deletar(@PathVariable final Long localArmazenamentoId) {
        localArmazenamentoService.remover(localArmazenamentoId);
    }
}
