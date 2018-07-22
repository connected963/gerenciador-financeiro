package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.objetivomonetario;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.objetivomonetario.ObjetivoMonetarioInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento.LocalArmazenamento;

public class ObjetivoMonetarioFactory {

    private ObjetivoMonetarioFactory() {

    }

    public static ObjetivoMonetario criarNovo(final Double valor,
                                              final LocalArmazenamento localArmazenamento) {
        return new ObjetivoMonetario(valor, localArmazenamento);
    }

    public static ObjetivoMonetario criaPorInputModelComLocalArmazenamento(
            final ObjetivoMonetarioInputModel objetivoMonetarioInputModel,
            final LocalArmazenamento localArmazenamento) {

        return new ObjetivoMonetario(objetivoMonetarioInputModel.getId(),
                objetivoMonetarioInputModel.getValor(),
                localArmazenamento);
    }
}
