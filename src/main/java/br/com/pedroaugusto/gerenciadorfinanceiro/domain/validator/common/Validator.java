package br.com.pedroaugusto.gerenciadorfinanceiro.domain.validator.common;

public interface Validator<T> {

    void validate(T t);
}
