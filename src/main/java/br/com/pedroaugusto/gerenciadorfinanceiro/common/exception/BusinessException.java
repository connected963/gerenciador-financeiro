package br.com.pedroaugusto.gerenciadorfinanceiro.common.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(final String message) {
        super(message);
    }
}
