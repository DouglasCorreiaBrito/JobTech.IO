package br.com.jobtechIO.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção de aplicação. Indica que existe algum erro de requisiição por parte do cliente. Ao ser
 * lançada na camada controller, causa retorno de erro 400 (Bad Request), conforme
 * definido pela anotação @ResponseStatus.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class GenericBadRequestException extends RuntimeException {

    public GenericBadRequestException(String message) {
        super(message);
    }

    public GenericBadRequestException(String message, Throwable e) {
        super(message, e);
    }
}
