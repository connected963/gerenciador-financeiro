package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.domain.common.enums.TipoMovimentacao;

public class MovimentacaoTestFactory {

    private MovimentacaoTestFactory() {

    }

    public static Movimentacao criaMovimentacaoEntradaComValor(final Double valor) {
        return MovimentacaoFactory.criarNova(TipoMovimentacao.ENTRADA, valor,
                null, false);
    }

    public static Movimentacao criaMovimentacaoSaidaComValor(final Double valor) {
        return MovimentacaoFactory.criarNova(TipoMovimentacao.SAIDA, valor,
                null, false);
    }
}
