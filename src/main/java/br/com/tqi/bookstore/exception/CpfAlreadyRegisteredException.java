package br.com.tqi.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfAlreadyRegisteredException extends Exception {
    public CpfAlreadyRegisteredException(String cpf) {
        super(String.format("CPF with number %s already registered in the system.", cpf));
    }
}

