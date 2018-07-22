package br.com.pedroaugusto.gerenciadorfinanceiro.application.service.objetivomonetario;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.objetivomonetario.ObjetivoMonetarioInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.application.service.localarmazenamento.LocalArmazenamentoService;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.objetivomonetario.ObjetivoMonetario;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.objetivomonetario.ObjetivoMonetarioFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.objetivomonetario.ObjetivoMonetarioAtualizarValidador;
import br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository.ObjetivoMonetarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ObjetivoMonetarioService {

    public static final String OBJETIVO_MONETARIO_INEXISTENTE = "objetivomonetario.null";

    private final ObjetivoMonetarioRepository objetivoMonetarioRepository;

    private final LocalArmazenamentoService localArmazenamentoService;

    @Autowired
    public ObjetivoMonetarioService(final ObjetivoMonetarioRepository objetivoMonetarioRepository,
                                    final LocalArmazenamentoService localArmazenamentoService) {
        this.objetivoMonetarioRepository = objetivoMonetarioRepository;
        this.localArmazenamentoService = localArmazenamentoService;
    }

    public List<ObjetivoMonetario> buscarTodos() {
        return objetivoMonetarioRepository.findAll();
    }

    public Optional<ObjetivoMonetario> buscarPorId(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser null");

        return Optional.of(objetivoMonetarioRepository.getOne(id));
    }

    public void inserir(final ObjetivoMonetarioInputModel objetivoMonetarioInputModel) {
        inserirPorInputModel(objetivoMonetarioInputModel);
    }

    public void atualizar(final ObjetivoMonetarioInputModel objetivoMonetarioInputModel) {
        final var validator = new ObjetivoMonetarioAtualizarValidador();
        validator.validate(objetivoMonetarioInputModel);

        inserirPorInputModel(objetivoMonetarioInputModel);
    }

    private void inserirPorInputModel(final ObjetivoMonetarioInputModel objetivoMonetarioInputModel) {
        final var localArmazenamento = localArmazenamentoService.buscarPorId(objetivoMonetarioInputModel.getId())
                .orElseThrow(() -> new BusinessException(LocalArmazenamentoService.LOCAL_ARMAZENAMENTO_INEXISTENTE));
        final var objetivoMonetario = ObjetivoMonetarioFactory.criaPorInputModelComLocalArmazenamento(
                objetivoMonetarioInputModel, localArmazenamento);

        objetivoMonetarioRepository.save(objetivoMonetario);
    }

    public void remover(final Long id) {
        objetivoMonetarioRepository.deleteById(id);
    }
}
