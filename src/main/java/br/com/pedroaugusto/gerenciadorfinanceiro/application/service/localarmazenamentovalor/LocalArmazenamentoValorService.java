package br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamentovalor.LocalArmazenamentoValorInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento.LocalArmazenamentoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValor;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValorFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.localarmazenamentovalor.LocalArmazenamentoValorAtualizarValidador;
import br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository.LocalArmazenamentoValorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocalArmazenamentoValorService {

    public static final String LOCAL_ARMAZENAMENTO_VALOR_INEXISTENTE = "localarmazenamentovalor.null";

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

    public Optional<LocalArmazenamentoValor> buscarPorId(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser null");

        return Optional.of(localArmazenamentoValorRepository.getOne(id));
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
                .buscarPorId(localArmazenamentoValorInputModel.getLocalArmazenamentoId())
                .orElseThrow(() -> new BusinessException(LocalArmazenamentoService.LOCAL_ARMAZENAMENTO_INEXISTENTE));
        final var localArmazenamentoValor = LocalArmazenamentoValorFactory.criaPorInputModelComLocalArmazenamento(
                localArmazenamentoValorInputModel, localArmazenamento);

        localArmazenamentoValorRepository.save(localArmazenamentoValor);
    }

    public void remover(final Long id) {
        localArmazenamentoValorRepository.deleteById(id);
    }
}
