package br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.repository;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamentoFactory;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor.LocalArmazenamentoValorFactory;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LocalArmazenamentoValorRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private LocalArmazenamentoValorRepository localArmazenamentoValorRepository;

    @Test
    public void deveEncontrarUmLocalArmazenamentoValor() {
        final var localArmazenamento = LocalArmazenamentoFactory.criarNovo("Local teste");
        final var localArmazenamentoPersistido = testEntityManager.persistAndFlush(localArmazenamento);

        final var localArmazenamentoValor = LocalArmazenamentoValorFactory.criarNovo(
                200.0, localArmazenamentoPersistido);
        final var localArmazenamentoValorPersistido = testEntityManager.persistAndFlush(localArmazenamentoValor);

        final var valores = localArmazenamentoValorRepository.buscarTodosPorLocalArmazenamentoId(
                localArmazenamentoPersistido.getId());

        final var valoresEsperados = List.of(localArmazenamentoValorPersistido);
        assertThat(valores, Matchers.is(valoresEsperados));
    }

    @Test
    public void deveEncontrarUltimoLocalArmazenamentoValorInserido() {
        final var localArmazenamento = LocalArmazenamentoFactory.criarNovo("Local teste");
        final var localArmazenamentoPersistido = testEntityManager.persistAndFlush(localArmazenamento);

        final var localArmazenamentoValor = LocalArmazenamentoValorFactory.criarNovo(
                200.0, localArmazenamentoPersistido);
        final var ultimoLocalArmazenamentoValor = LocalArmazenamentoValorFactory.criarNovo(
                543.4, localArmazenamentoPersistido);

        testEntityManager.persistAndFlush(localArmazenamentoValor);
        testEntityManager.persistAndFlush(ultimoLocalArmazenamentoValor);

        final var ultimoLocalArmazenamentoValorEncontrado = localArmazenamentoValorRepository
                .buscarUltimoValorPorLocalArmazenamentoId(localArmazenamentoPersistido.getId());

        assertEquals(543.4, ultimoLocalArmazenamentoValorEncontrado, 0.0001);
    }

}