package br.com.pedroaugusto.gerenciadorfinanceiro.infrastructure.handler;

import br.com.pedroaugusto.gerenciadorfinanceiro.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class BusinessExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public BusinessExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity businessError(final RuntimeException exception) {
        final var message = messageSource.getMessage(exception.getMessage(),
                null, Locale.getDefault());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }
}
