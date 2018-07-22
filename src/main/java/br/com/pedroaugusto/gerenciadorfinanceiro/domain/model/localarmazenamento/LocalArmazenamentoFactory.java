package br.com.pedroaugusto.gerenciadorfinanceiro.domain.model.localarmazenamento;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamento.LocalArmazenamentoInputModel;

public class LocalArmazenamentoFactory {

    private LocalArmazenamentoFactory() {

    }

    public static LocalArmazenamento criarNovo(final String nome) {
        return new LocalArmazenamento(nome);
    }

    public static LocalArmazenamento criaPorInputModel(
            final LocalArmazenamentoInputModel localArmazenamentoInputModel) {

        return new LocalArmazenamento(localArmazenamentoInputModel.getId(),
                localArmazenamentoInputModel.getNome());
    }
}
