package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao.MovimentacaoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.common.enums.TipoMovimentacao;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;

public class MovimentacaoFactory {

    private MovimentacaoFactory() {

    }

    public static Movimentacao criarNova(final TipoMovimentacao tipo,
                                         final Double valor,
                                         final LocalArmazenamento localArmazenamento,
                                         final Boolean repetirMensalmente) {
        return new Movimentacao(tipo, valor, localArmazenamento, repetirMensalmente);
    }

    public static Movimentacao criaPorInputModel(final MovimentacaoInputModel movimentacaoInputModel) {

        return new Movimentacao(movimentacaoInputModel.getId(),
                movimentacaoInputModel.getTipo(), movimentacaoInputModel.getValor(),
                null, movimentacaoInputModel.getRepetirMensalmente());
    }

    public static Movimentacao criaPorInputModelComLocalArmazenamento(
            final MovimentacaoInputModel movimentacaoInputModel,
            final LocalArmazenamento localArmazenamento) {

        return new Movimentacao(movimentacaoInputModel.getId(),
                movimentacaoInputModel.getTipo(), movimentacaoInputModel.getValor(),
                localArmazenamento, movimentacaoInputModel.getRepetirMensalmente());
    }
}
