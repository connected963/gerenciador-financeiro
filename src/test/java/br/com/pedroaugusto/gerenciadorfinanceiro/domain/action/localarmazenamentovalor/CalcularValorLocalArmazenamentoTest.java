package br.com.pedroaugusto.gerenciadorfinanceiro.domain.action.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.MovimentacaoTestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class CalcularValorLocalArmazenamentoTest {

    @Test
    public void calcularValorMovimentacaoEntradaSemMovimentacaoOriginal() {
        final var calculoMovimentacao = new CalcularValorLocalArmazenamento();
        final var movimentacaoAtualizada = MovimentacaoTestFactory.criaMovimentacaoEntradaComValor(200.0);
        final var novoValorMovimentacao = calculoMovimentacao.calcularPorMovimentacao(
                200.0, movimentacaoAtualizada, null);

        assertEquals(400, novoValorMovimentacao, 0.0001);
    }

    @Test
    public void calcularValorMovimentacaoSaidaSemMovimentacaoOriginal() {
        final var calculoMovimentacao = new CalcularValorLocalArmazenamento();
        final var movimentacaoAtualizada = MovimentacaoTestFactory.criaMovimentacaoSaidaComValor(200.0);
        final var novoValorMovimentacao = calculoMovimentacao.calcularPorMovimentacao(
                200.0, movimentacaoAtualizada, null);

        assertEquals(0.0, novoValorMovimentacao, 0.0001);
    }

    @Test
    public void calcularValorMovimentacaoEntradaComMovimentacaoOriginalEntrada() {
        final var calculoMovimentacao = new CalcularValorLocalArmazenamento();
        final var movimentacaoOriginal = MovimentacaoTestFactory.criaMovimentacaoEntradaComValor(300.0);
        final var movimentacaoAtualizada = MovimentacaoTestFactory.criaMovimentacaoEntradaComValor(200.0);
        final var novoValorMovimentacao = calculoMovimentacao.calcularPorMovimentacao(
                400.0, movimentacaoAtualizada, movimentacaoOriginal);

        assertEquals(300, novoValorMovimentacao, 0.0001);
    }

    @Test
    public void calcularValorMovimentacaoEntradaComMovimentacaoOriginalSaida() {
        final var calculoMovimentacao = new CalcularValorLocalArmazenamento();
        final var movimentacaoOriginal = MovimentacaoTestFactory.criaMovimentacaoSaidaComValor(300.0);
        final var movimentacaoAtualizada = MovimentacaoTestFactory.criaMovimentacaoEntradaComValor(200.0);
        final var novoValorMovimentacao = calculoMovimentacao.calcularPorMovimentacao(
                200.0, movimentacaoAtualizada, movimentacaoOriginal);

        assertEquals(700.0, novoValorMovimentacao, 0.0001);
    }

    @Test
    public void calcularValorMovimentacaoSaidaComMovimentacaoOriginalEntrada() {
        final var calculoMovimentacao = new CalcularValorLocalArmazenamento();
        final var movimentacaoOriginal = MovimentacaoTestFactory.criaMovimentacaoEntradaComValor(300.0);
        final var movimentacaoAtualizada = MovimentacaoTestFactory.criaMovimentacaoSaidaComValor(200.0);
        final var novoValorMovimentacao = calculoMovimentacao.calcularPorMovimentacao(
                500.0, movimentacaoAtualizada, movimentacaoOriginal);

        assertEquals(0.0, novoValorMovimentacao, 0.0001);
    }

    @Test
    public void calcularValorMovimentacaoSaidaComMovimentacaoOriginalSaida() {
        final var calculoMovimentacao = new CalcularValorLocalArmazenamento();
        final var movimentacaoOriginal = MovimentacaoTestFactory.criaMovimentacaoSaidaComValor(300.0);
        final var movimentacaoAtualizada = MovimentacaoTestFactory.criaMovimentacaoSaidaComValor(600.0);
        final var novoValorMovimentacao = calculoMovimentacao.calcularPorMovimentacao(
                300.0, movimentacaoAtualizada, movimentacaoOriginal);

        assertEquals(0.0, novoValorMovimentacao, 0.0001);
    }
}