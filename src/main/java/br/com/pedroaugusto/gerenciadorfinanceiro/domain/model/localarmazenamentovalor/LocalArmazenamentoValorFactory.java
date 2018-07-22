package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamentovalor.LocalArmazenamentoValorInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;

public class LocalArmazenamentoValorFactory {

    private LocalArmazenamentoValorFactory() {

    }

    public static LocalArmazenamentoValor criarNovo(final Double valor,
                                                    final LocalArmazenamento localArmazenamento) {
        return new LocalArmazenamentoValor(valor, localArmazenamento);
    }

    public static LocalArmazenamentoValor criaPorInputModelComLocalArmazenamento(
            final LocalArmazenamentoValorInputModel localArmazenamentoValorInputModel,
            final LocalArmazenamento localArmazenamento) {

        return new LocalArmazenamentoValor(localArmazenamentoValorInputModel.getId(),
                localArmazenamentoValorInputModel.getValor(),
                localArmazenamentoValorInputModel.getData(),
                localArmazenamento);
    }

}
