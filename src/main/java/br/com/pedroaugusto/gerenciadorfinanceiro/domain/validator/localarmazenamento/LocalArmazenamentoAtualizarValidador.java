package br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.localarmazenamento;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamento.LocalArmazenamentoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.common.Validator;


public class LocalArmazenamentoAtualizarValidador implements Validator<LocalArmazenamentoInputModel> {

    private static final String LOCAL_ARMAZENAMENTO_SEM_ID = "localarmazenamento.id.null";

    @Override
    public void validate(LocalArmazenamentoInputModel localArmazenamentoInputModel) {
        if (localArmazenamentoInputModel.getId() == null) {
            throw new BusinessException(LOCAL_ARMAZENAMENTO_SEM_ID);
        }
    }
}
