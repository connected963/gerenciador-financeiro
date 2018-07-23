package br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamento.LocalArmazenamentoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamentoFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.localarmazenamento.LocalArmazenamentoAtualizarValidador;
import br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository.LocalArmazenamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LocalArmazenamentoService {

    private final LocalArmazenamentoRepository localArmazenamentoRepository;

    @Autowired
    public LocalArmazenamentoService(final LocalArmazenamentoRepository localArmazenamentoRepository) {
        this.localArmazenamentoRepository = localArmazenamentoRepository;
    }

    public List<LocalArmazenamento> buscarTodos() {
        return localArmazenamentoRepository.findAll();
    }

    public LocalArmazenamento buscarPorId(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser null");

        return localArmazenamentoRepository.getOne(id);
    }

    public void inserir(final LocalArmazenamentoInputModel localArmazenamentoInputModel) {
        final var localArmazenamento = LocalArmazenamentoFactory.criaPorInputModel(
                localArmazenamentoInputModel);

        localArmazenamentoRepository.save(localArmazenamento);
    }

    public void atualizar(final LocalArmazenamentoInputModel localArmazenamentoInputModel) {
        final var localArmazenamento = LocalArmazenamentoFactory.criaPorInputModel(
                localArmazenamentoInputModel);
        final var validator = new LocalArmazenamentoAtualizarValidador();

        validator.validate(localArmazenamentoInputModel);

        localArmazenamentoRepository.save(localArmazenamento);
    }

    public void remover(final Long id) {
        localArmazenamentoRepository.deleteById(id);
    }
}
