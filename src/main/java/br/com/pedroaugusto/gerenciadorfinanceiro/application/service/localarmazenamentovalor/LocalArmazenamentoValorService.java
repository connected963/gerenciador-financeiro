package br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamentovalor.LocalArmazenamentoValorInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento.LocalArmazenamentoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.action.localarmazenamentovalor.CalcularValorLocalArmazenamento;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValor;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValorFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.Movimentacao;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.localarmazenamentovalor.LocalArmazenamentoValorAtualizarValidador;
import br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository.LocalArmazenamentoValorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LocalArmazenamentoValorService {

    private final LocalArmazenamentoValorRepository localArmazenamentoValorRepository;

    private final LocalArmazenamentoService localArmazenamentoService;

    public LocalArmazenamentoValorService(
            final LocalArmazenamentoValorRepository localArmazenamentoValorRepository,
            final LocalArmazenamentoService localArmazenamentoService) {
        this.localArmazenamentoValorRepository = localArmazenamentoValorRepository;
        this.localArmazenamentoService = localArmazenamentoService;
    }

    public List<LocalArmazenamentoValor> buscarTodosPorLocalArmazenamentoId(final Long localArmazenamentoId) {
        return localArmazenamentoValorRepository.buscarTodosPorLocalArmazenamentoId(localArmazenamentoId);
    }

    public LocalArmazenamentoValor buscarPorId(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser null");

        return localArmazenamentoValorRepository.getOne(id);
    }

    public void salvar(final LocalArmazenamentoValor localArmazenamentoValor) {
        localArmazenamentoValorRepository.save(localArmazenamentoValor);
    }

    public void inserir(
            final LocalArmazenamentoValorInputModel localArmazenamentoValorInputModel) {
        salvarPorInputModel(localArmazenamentoValorInputModel);
    }

    public void atualizar(
            final LocalArmazenamentoValorInputModel localArmazenamentoValorInputModel) {
        final var validator = new LocalArmazenamentoValorAtualizarValidador();
        validator.validate(localArmazenamentoValorInputModel);

        salvarPorInputModel(localArmazenamentoValorInputModel);
    }

    private void salvarPorInputModel(final LocalArmazenamentoValorInputModel localArmazenamentoValorInputModel) {
        final var localArmazenamento = localArmazenamentoService
                .buscarPorId(localArmazenamentoValorInputModel.getLocalArmazenamentoId());
        final var localArmazenamentoValor = LocalArmazenamentoValorFactory.criaPorInputModelComLocalArmazenamento(
                localArmazenamentoValorInputModel, localArmazenamento);

        localArmazenamentoValorRepository.save(localArmazenamentoValor);
    }

    public void remover(final Long id) {
        localArmazenamentoValorRepository.deleteById(id);
    }

    public Double buscarAtualValorPorLocalArmazenamento(final Long localArmazenamentoId) {
        Objects.requireNonNull(localArmazenamentoId, "localArmazenamentoId não pode ser null");

        final var valorAtual = localArmazenamentoValorRepository
                .buscarUltimoValorPorLocalArmazenamentoId(localArmazenamentoId);

        return valorAtual != null ? valorAtual : 0.0;
    }

    public void atualizarValorPorMovimentacao(final Movimentacao movimentacaoAtualizada,
                                              final Movimentacao movimentacaoOriginal) {
        final var localArmazenamento = movimentacaoAtualizada.getLocalArmazenamento();
        final var valorAtual = buscarAtualValorPorLocalArmazenamento(
                localArmazenamento.getId());

        final var calculoNovoValor = new CalcularValorLocalArmazenamento();
        final var valorAtualizado = calculoNovoValor.calcularPorMovimentacao(valorAtual,
                movimentacaoAtualizada, movimentacaoOriginal);

        final var localArmazenamentoValor = LocalArmazenamentoValorFactory.criarNovo(valorAtualizado,
                localArmazenamento);

        salvar(localArmazenamentoValor);
    }
}
