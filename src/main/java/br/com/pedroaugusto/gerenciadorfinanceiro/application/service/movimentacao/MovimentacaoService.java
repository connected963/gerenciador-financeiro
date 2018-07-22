package br.com.pedroaugusto.gerenciadorfinanceiro.application.service.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao.MovimentacaoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento.LocalArmazenamentoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.Movimentacao;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.MovimentacaoFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.movimentacao.MovimentacaoAtualizarValidador;
import br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovimentacaoService {

    public static final String MOVIMENTACAO_INEXISTENTE = "movimentacao.null";

    private final MovimentacaoRepository movimentacaoRepository;

    private final LocalArmazenamentoService localArmazenamentoService;

    @Autowired
    public MovimentacaoService(final MovimentacaoRepository movimentacaoRepository,
                               final LocalArmazenamentoService localArmazenamentoService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.localArmazenamentoService = localArmazenamentoService;
    }

    public List<Movimentacao> buscarTodos() {
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> buscarPorId(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser null");

        return Optional.of(movimentacaoRepository.getOne(id));
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
        final var localArmazenamento = localArmazenamentoService.buscarPorId(movimentacaoInputModel.getId())
                .orElseThrow(() -> new BusinessException(MovimentacaoService.MOVIMENTACAO_INEXISTENTE));
        final var movimentacao = MovimentacaoFactory.criaPorInputModelComLocalArmazenamento(
                movimentacaoInputModel, localArmazenamento);

        movimentacaoRepository.save(movimentacao);
    }

    public void remover(final Long id) {
        movimentacaoRepository.deleteById(id);
    }
}
