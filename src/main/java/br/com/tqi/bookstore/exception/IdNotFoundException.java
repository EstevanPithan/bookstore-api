package br.com.tqi.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFoundException extends Exception {

    public IdNotFoundException(String id){
        super("Item not found with Id: " + id);
    }
}
