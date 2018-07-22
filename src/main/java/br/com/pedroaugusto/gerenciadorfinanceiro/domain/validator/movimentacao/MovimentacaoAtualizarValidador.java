package br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.movimentacao;

import br.com.pedroaugusto.gerenciadorfinanceiro.application.inputmodels.movimentacao.MovimentacaoInputModel;
import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.common.Validator;

public class MovimentacaoAtualizarValidador implements Validator<MovimentacaoInputModel> {

    private static final String MOVIMENTACAO_SEM_ID = "movimentacao.id.null";

    @Override
    public void validate(final MovimentacaoInputModel movimentacaoInputModel) {
        if (movimentacaoInputModel.getId() == null) {
            throw new BusinessException(MOVIMENTACAO_SEM_ID);
        }
    }
}
