package br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.objetivomonetario;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.objetivomonetario.ObjetivoMonetarioInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.common.Validator;

public class ObjetivoMonetarioAtualizarValidador implements Validator<ObjetivoMonetarioInputModel> {

    private static final String OBJETIVO_MONETARIO_SEM_ID = "objetivomonetario.id.null";

    @Override
    public void validate(final ObjetivoMonetarioInputModel objetivoMonetarioInputModel) {
        if (objetivoMonetarioInputModel.getId() == null) {
            throw new BusinessException(OBJETIVO_MONETARIO_SEM_ID);
        }
    }
}
