package br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.localarmazenamentovalor;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.localarmazenamentovalor.LocalArmazenamentoValorInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.common.Validator;


public class LocalArmazenamentoValorAtualizarValidador
        implements Validator<LocalArmazenamentoValorInputModel> {

    private static final String LOCAL_ARMAZENAMENTO_VALOR_SEM_ID = "localarmazenamentovalor.id.null";

    @Override
    public void validate(LocalArmazenamentoValorInputModel localArmazenamentoValorInputModel) {
        if (localArmazenamentoValorInputModel.getId() == null) {
            throw new BusinessException(LOCAL_ARMAZENAMENTO_VALOR_SEM_ID);
        }
    }
}
