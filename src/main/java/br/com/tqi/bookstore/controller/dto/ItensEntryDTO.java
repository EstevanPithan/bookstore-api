package br.com.tqi.bookstore.controller.dto;

import br.com.tqi.bookstore.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItensEntryDTO {

    private String id;
    private Book book;
    private int quantity;
    private double price;
    private Date date;
    private String observation;
}
