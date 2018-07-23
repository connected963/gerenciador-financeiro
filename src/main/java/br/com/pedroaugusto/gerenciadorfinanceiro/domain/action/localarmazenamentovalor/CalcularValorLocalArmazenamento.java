package br.com.pedroaugusto.gerenciadorfinanceiro.domain.action.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.common.enums.TipoMovimentacao;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao.Movimentacao;

public class CalcularValorLocalArmazenamento {

    public Double calcularPorMovimentacao(final Double valorAtual, final Movimentacao movimentacaoAtualizada,
                                          final Movimentacao movimentacaoOriginal) {
        final var valorAtualSemMovimentacaoOriginal = removerValorMovimentacaoOriginal(valorAtual, movimentacaoOriginal);

        return calcularNovoValor(valorAtualSemMovimentacaoOriginal, movimentacaoAtualizada);
    }

    private Double removerValorMovimentacaoOriginal(final Double valorAtual,
                                                    final Movimentacao movimentacaoOriginal) {
        if (movimentacaoOriginal == null) return valorAtual;

        if (movimentacaoOriginal.getTipo() == TipoMovimentacao.ENTRADA) {
            return valorAtual - movimentacaoOriginal.getValor();
        } else {
            return valorAtual + movimentacaoOriginal.getValor();
        }
    }

    private Double calcularNovoValor(final Double valorAtual, final Movimentacao movimentacao) {
        final var valorMovimentacao = movimentacao.getValor();

        if (movimentacao.getTipo() == TipoMovimentacao.ENTRADA) {
            return valorAtual + valorMovimentacao;
        } else {
            return valorAtual - valorMovimentacao;
        }
    }
}
