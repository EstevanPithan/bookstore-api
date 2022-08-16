package br.com.tqi.bookstore.controller.dto.create;

import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.Client;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItensSellCreateDTO {

    private List<Book> book = new ArrayList<>();
    private double totalPrice;
    private Client client;
}
