package br.com.tqi.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookQuantityNotEnougthToReverseException extends Exception{

    public BookQuantityNotEnougthToReverseException(){super( "Book quantity is not enough to reverve the iten entry.");
    }
}
