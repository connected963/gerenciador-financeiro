package br.com.pedroaugusto.gerenciadorfinanceiro.application.service.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao.MovimentacaoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento.LocalArmazenamentoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamentovalor.LocalArmazenamentoValorService;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.Movimentacao;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.MovimentacaoFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.movimentacao.MovimentacaoAtualizarValidador;
import br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final LocalArmazenamentoService localArmazenamentoService;

    private final LocalArmazenamentoValorService localArmazenamentoValorService;

    @Autowired
    public MovimentacaoService(final MovimentacaoRepository movimentacaoRepository,
                               final LocalArmazenamentoService localArmazenamentoService,
                               final LocalArmazenamentoValorService localArmazenamentoValorService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.localArmazenamentoService = localArmazenamentoService;
        this.localArmazenamentoValorService = localArmazenamentoValorService;
    }

    public List<Movimentacao> buscarTodos() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao buscarPorId(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser null");

        return movimentacaoRepository.getOne(id);
    }

    public void inserir(final MovimentacaoInputModel movimentacaoInputModel) {
        inserirPorInputModel(movimentacaoInputModel);
    }

    public void atualizar(final MovimentacaoInputModel movimentacaoInputModel) {
        final var validator = new MovimentacaoAtualizarValidador();
        validator.validate(movimentacaoInputModel);

        inserirPorInputModel(movimentacaoInputModel);
    }

    private void inserirPorInputModel(final MovimentacaoInputModel movimentacaoInputModel) {
        final var localArmazenamentoId = movimentacaoInputModel.getLocalArmazenamentoId();
        final Movimentacao movimentacao;

        if (localArmazenamentoId != null) {
            movimentacao = criaMovimentacaoComLocalArmazenamento(movimentacaoInputModel);
            atualizarValorLocalArmazenamento(movimentacao);
        } else {
            movimentacao = criaMovimentacao(movimentacaoInputModel);
        }

        movimentacaoRepository.save(movimentacao);
    }

    private Movimentacao criaMovimentacaoComLocalArmazenamento(
            final MovimentacaoInputModel movimentacaoInputModel) {
        final var localArmazenamentoId = movimentacaoInputModel.getLocalArmazenamentoId();
        final var localArmazenamento = buscaLocalArmazenamento(localArmazenamentoId);

        return MovimentacaoFactory.criaPorInputModelComLocalArmazenamento(
                movimentacaoInputModel, localArmazenamento);
    }

    private Movimentacao criaMovimentacao(final MovimentacaoInputModel movimentacaoInputModel) {
        return MovimentacaoFactory.criaPorInputModel(movimentacaoInputModel);
    }

    private LocalArmazenamento buscaLocalArmazenamento(final Long localArmazenamentoId) {
        return localArmazenamentoId != null ?
                localArmazenamentoService.buscarPorId(localArmazenamentoId) : null;
    }

    private void atualizarValorLocalArmazenamento(final Movimentacao movimentacaoAtualizada) {
        final var movimentacaoId = movimentacaoAtualizada.getId();
        final var movimentacaoOriginal =
                movimentacaoId != null ? buscarPorId(movimentacaoId) : null;

        localArmazenamentoValorService.atualizarValorPorMovimentacao(movimentacaoAtualizada,
                movimentacaoOriginal);
    }

    public void remover(final Long id) {
        movimentacaoRepository.deleteById(id);
    }
}
