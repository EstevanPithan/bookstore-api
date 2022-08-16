package br.com.tqi.bookstore.controller.dto;

import br.com.tqi.bookstore.model.Book;
import br.com.tqi.bookstore.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItensSellDTO {

    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime date;
    private double totalPrice;
    private Client client;
}
