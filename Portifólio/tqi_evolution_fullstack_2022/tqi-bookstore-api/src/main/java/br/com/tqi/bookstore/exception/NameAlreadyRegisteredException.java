package br.com.tqi.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameAlreadyRegisteredException extends Exception {
    public NameAlreadyRegisteredException(String name) {
        super(String.format("Author with name %s already registered in the system.", name));
    }
}

