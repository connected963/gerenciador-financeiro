package br.com.pedroaugusto.gerenciadorfinanceiro.controller;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao.MovimentacaoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.movimentacao.MovimentacaoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping("/movimentacao")
    public List<Movimentacao> buscarTodos() {
        return movimentacaoService.buscarTodos();
    }

    @GetMapping("/movimentacao/{movimentacaoId}")
    public Movimentacao buscarPorId(@PathVariable final Long movimentacaoId) {
        return movimentacaoService.buscarPorId(movimentacaoId);
    }

    @PostMapping("/movimentacao")
    public void inserir(@Valid @RequestBody final MovimentacaoInputModel movimentacao) {
        movimentacaoService.inserir(movimentacao);
    }

    @PutMapping("/movimentacao")
    public void atualizar(@Valid @RequestBody final MovimentacaoInputModel movimentacao) {
        movimentacaoService.atualizar(movimentacao);
    }

    @DeleteMapping("/movimentacao/{movimentacaoId}")
    public void deletar(@PathVariable final Long movimentacaoId) {
        movimentacaoService.remover(movimentacaoId);
    }
}
